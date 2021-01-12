package com.fante.project.api.appView.service;

import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.project.api.appView.domain.CouponHistoryDetail;
import com.fante.project.api.appView.domain.SmsMemberCouponDetail;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCoupon.domain.SmsCouponDTO;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.smsCoupon.service.impl.SmsCouponServiceImpl;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponHistory.service.ISmsCouponHistoryService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.impl.UmsMemberServiceImpl;
import com.fante.project.tool.gen.util.GenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:56
 * @Author: wz
 * @Description: 前端推荐商品相关处理服务
 */
@Service
public class PmsCouponService {

    private static Logger log = LoggerFactory.getLogger(PmsCouponService.class);
    /**
     * 优惠券
     */
    @Autowired
    private ISmsCouponService iSmsCouponService;
    /**
     * 会员Service业务层处理
     */
    @Autowired
    private UmsMemberServiceImpl umsMemberServiceImpl;
    /**
     * 优惠券领取记录
     */
    @Autowired
    private ISmsCouponHistoryService iSmsCouponHistoryService;

    /**
     * (app)根据商品id查询商品到可用优惠券
     * @param id
     * @param memberId
     * @return
     */
    public List<SmsCoupon> getUsableCouponsByproductId(Long id,Long memberId) {
        return iSmsCouponService.getUsableCouponsByProductId(id,memberId);
    }
    /**
     * (app)根据店铺id查询商品到可用优惠券
     * @param id
     * @param memberId
     * @return
     */
    public List<SmsCoupon> getUsableCouponsByShopId(Long id,Long memberId) {
        return iSmsCouponService.getUsableCouponsByShopId(id,memberId);
    }

    /**
     * 优惠券详情
     * @param couponId
     * @return
     */
    public SmsCouponDTO detail(Long couponId) {
        return iSmsCouponService.selectSmsCouponAllById(couponId);
    }

    /**
     * 用户领取优惠券
     * @param coupon
     * @param memberId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int giveCoupon(SmsCoupon coupon, Long memberId) {
        //组装数据插入领取历史
        SmsCouponHistory history = new SmsCouponHistory();
        history.setCouponId(coupon.getId());
        history.setUserId(memberId);
        //历史记录码唯一
        history.setCouponCode(String.valueOf(IdGenerator.nextId()));
        history.setGetType(coupon.getType());
        int insert = iSmsCouponHistoryService.insertSmsCouponHistory(history);
        Checker.check(insert !=1,"优惠券领取异常");
        SmsCouponHistory countHistory = new SmsCouponHistory();
        countHistory.setCouponId(coupon.getId());
        countHistory.setUserId(memberId);
        int giveCount = iSmsCouponHistoryService.countSmsCouponHistory(countHistory);
        Checker.check(giveCount>1,"优惠券已领取");
        //领取优惠券 减库存 加领取数量
        int i = iSmsCouponService.memeberGetCoupon(coupon.getId());
        Checker.check(i!=1,"优惠券库存异常");
        return i;
    }

    /**
     * 获取用户可用优惠券
     * @param tokenUserId
     * @return
     */
    public  List<SmsMemberCouponDetail> memberCouponList(Long tokenUserId,String useStatus) {
        return iSmsCouponService.getMemberEnableCoupon(tokenUserId,useStatus);
    }
    /**
     * 核销优惠券
     * @param memberId
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int cancelCoupon(long memberId, String code) {
        UmsMember member = umsMemberServiceImpl.selectUmsMemberById(memberId);
        Checker.check(ObjectUtils.isEmpty(member),"登录已失效,请重新登录");
        String verifier = member.getVerifier();
        Checker.check(StringUtils.equals(UmsMemberConst.IsVerifier.NOT.getType(),verifier),"您还没有该权限");
        //根据code查询优惠券领取历史信息
        CouponHistoryDetail detail = iSmsCouponHistoryService.selectSmsCouponHistoryByCode(code);
        Checker.check(ObjectUtils.isEmpty(detail),"优惠券不存在或已失效");
        Long couponId = detail.getCouponId();
        SmsCoupon smsCoupon = iSmsCouponService.selectSmsCouponById(couponId);
        //判断是否本店优惠券
        Checker.checkOp(Objects.equals(detail.getShopId(),member.getShopId()),"该优惠券不是本店铺优惠券");
        //判断日期
        Checker.check(ObjectUtils.isEmpty(smsCoupon),"优惠券已失效");
        Date endTime = smsCoupon.getEndTime();
        Checker.check(endTime.before(new Date()),"优惠券已过期");
        //修改状态
        SmsCouponHistory updateHistory = new SmsCouponHistory();
        updateHistory.setUserId(detail.getMemberId());
        updateHistory.setCouponId(detail.getCouponId());
        updateHistory.setUpdateBy(String.valueOf(member.getId()));
        //添加优惠券使用量
        int getCount = iSmsCouponService.memeberUseCoupon(couponId);
        Checker.check(getCount!=1,"优惠券核销失败！");
        return iSmsCouponHistoryService.updateForUseCoupon(updateHistory);
    }
    
    /**
     * 根据code查询优惠券使用状态
     * @param code
     * @return
     */
    public Map<String, String> queryCouponUseStatusByCode(String code){
        Map<String, String> map = new HashMap<>();
        String status = iSmsCouponHistoryService.queryCouponUseStatusByCode(code);
        //判断是否已使用
        if("1".equals(status)){
            map.put("status", "1");
            map.put("msg", "已使用");
        }else{
            map.put("status", "0");
            map.put("msg", "未使用");
        }
        return map;
    }
}
