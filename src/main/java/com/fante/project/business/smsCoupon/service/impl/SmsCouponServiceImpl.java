package com.fante.project.business.smsCoupon.service.impl;

import com.fante.common.business.enums.SmsCouponConst;
import com.fante.common.constant.Constants;
import com.fante.common.exception.BusinessException;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.MemberDataRsp;
import com.fante.project.api.appView.domain.SmsMemberCouponDetail;
import com.fante.project.api.appView.service.PmsCouponService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.bizShopInfo.utils.ShopRedis;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCoupon.domain.SmsCouponDTO;
import com.fante.project.business.smsCoupon.domain.SmsCouponListDTO;
import com.fante.project.business.smsCoupon.mapper.SmsCouponMapper;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponHistory.service.ISmsCouponHistoryService;
import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelationDTO;
import com.fante.project.business.smsCouponProductCateRelation.service.ISmsCouponProductCateRelationService;
import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelationDTO;
import com.fante.project.business.smsCouponProductRelation.service.ISmsCouponProductRelationService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 优惠券Service业务层处理
 *
 * @author fante
 * @date 2020-03-19
 */
@Service
public class SmsCouponServiceImpl implements ISmsCouponService {

    private static Logger log = LoggerFactory.getLogger(SmsCouponServiceImpl.class);

    @Autowired
    private SmsCouponMapper smsCouponMapper;
    /**
     * 前端推荐商品相关处理服务
     */
    @Autowired
    private PmsCouponService pmsCouponService;
    /**
     * 优惠券使用、领取历史Service接口
     */
    @Autowired
    private ISmsCouponHistoryService iSmsCouponHistoryService;
    /**
     * 店铺Redis工具类
     */
    @Autowired
    ShopRedis shopRedis;
    /**
     * 优惠券和产品分类关系Service接口
     */
    @Autowired
    private ISmsCouponProductCateRelationService smsCouponProductCateRelationService;
    /**
     * 优惠券和产品的关系Service接口
     */
    @Autowired
    private ISmsCouponProductRelationService smsCouponProductRelationService;
    /**
     * 会员Service接口
     */
    @Autowired
    private IUmsMemberService iUmsMemberService;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;
    /**
     * 店铺
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;

    @Autowired
    RestTemplate restTemplate;
    /**
     * 给粉丝发送优惠劵
     */
    private final String url = "http://www.henangaiyin.com/sunshinecredit/RealNameCertification/sendCoupon";
    /**
     * 查询优惠券
     *
     * @param id 优惠券ID
     * @return 优惠券
     */
    @Override
    public SmsCoupon selectSmsCouponById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsCouponMapper.selectSmsCouponById(id);
    }

    /**
     * 查询优惠券
     * @param id
     * @return
     */
    @Override
    public SmsCouponDTO selectSmsCouponAllById(Long id) {
        SmsCoupon smsCoupon = selectSmsCouponById(id);
        if (ObjectUtils.isEmpty(smsCoupon)) {
            return null;
        }
        smsCoupon.validate();
        SmsCouponConst.useTypeEnum useTypeEnum =  SmsCouponConst.useTypeEnum.get(smsCoupon.getUseType());
        Checker.check(ObjectUtils.isEmpty(useTypeEnum), "优惠券使用范围异常");
        SmsCouponDTO smsCouponDTO = new SmsCouponDTO();
        BeanUtils.copyBeanProp(smsCouponDTO, smsCoupon);

        switch (useTypeEnum) {
            case ALLUSE:
                log.info("查找店铺全场通用");
                break;
            case CLASSIFYUSE:
                log.info("查找优惠券店铺指定分类");
                SmsCouponProductCateRelationDTO cateRelationDTO = new SmsCouponProductCateRelationDTO();
                cateRelationDTO.setCouponId(smsCouponDTO.getId());
                smsCouponDTO.setCateRelations(smsCouponProductCateRelationService.selectJoinList(cateRelationDTO));
                break;
            case PRODUCTUSE:
                log.info("查找优惠券店铺指定商品");
                SmsCouponProductRelationDTO productRelationDTO = new SmsCouponProductRelationDTO();
                productRelationDTO.setCouponId(smsCouponDTO.getId());
                smsCouponDTO.setProductRelations(smsCouponProductRelationService.selectJoinList(productRelationDTO));
                break;
            default:
                log.error("使用范围异常");
                break;
        }
        return smsCouponDTO;
    }

    /**
     * 查询优惠券列表
     *
     * @param smsCoupon 优惠券
     * @return 优惠券集合
     */
    @Override
    public List<SmsMemberCouponDetail> selectSmsCouponList(SmsCoupon smsCoupon) {
        return smsCouponMapper.selectSmsCouponDetailList(smsCoupon);
    }

    /**
     * 查询优惠券列表
     *
     * @param smsCoupon 优惠券
     * @return 优惠券集合
     */
    @Override
    public List<SmsCouponListDTO> selectSmsCouponListDTOList(SmsCoupon smsCoupon) {
        return smsCouponMapper.selectSmsCouponListDTOList(smsCoupon);
    }

    /**
     * 查询优惠券数量
     *
     * @param smsCoupon 优惠券
     * @return 结果
     */
    @Override
    public int countSmsCoupon(SmsCoupon smsCoupon){
        return smsCouponMapper.countSmsCoupon(smsCoupon);
    }

    /**
     * 唯一校验
     *
     * @param smsCoupon 优惠券
     * @return 结果
     */
    @Override
    public String checkSmsCouponUnique(SmsCoupon smsCoupon) {
        return smsCouponMapper.checkSmsCouponUnique(smsCoupon) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增优惠券
     *
     * @param smsCoupon 优惠券
     * @return 新增优惠券数量
     */
    @Override
    public int insertSmsCoupon(SmsCoupon smsCoupon) {
        if (StringUtils.isBlank(smsCoupon.getCreateBy())) {
            smsCoupon.setCreateBy(ShiroUtils.getLoginName());
        }
        smsCoupon.setStatus(SmsCouponConst.StatusEnum.AWAIT.getType());
        smsCoupon.setCreateTime(DateUtils.getNowDate());
        smsCoupon.setCode(String.valueOf(IdGenerator.nextId()));
        return smsCouponMapper.insertSmsCoupon(smsCoupon);
    }

    /**
     * 优惠券新增处理
     * @param smsCoupon
     * @param selectIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertProcess(SmsCoupon smsCoupon, String selectIds) {
        SmsCouponConst.useTypeEnum useTypeEnum =  SmsCouponConst.useTypeEnum.get(smsCoupon.getUseType());
        Checker.check(ObjectUtils.isEmpty(useTypeEnum), "优惠券使用范围异常");
        if (ObjectUtils.isEmpty(smsCoupon.getEnableTime())) {
            smsCoupon.setEnableTime(smsCoupon.getEndTime());
        }
        insertSmsCoupon(smsCoupon);
        Long couponId = smsCoupon.getId();
        log.info("保存优惠券信息，优惠券ID: {}", couponId);

        craeteCouponRelation(useTypeEnum, couponId, selectIds);
    }

    /**
     * 修改优惠券
     *
     * @param smsCoupon 优惠券
     * @return 修改优惠券数量
     */
    @Override
    public int updateSmsCoupon(SmsCoupon smsCoupon) {
        if (StringUtils.isBlank(smsCoupon.getUpdateBy())) {
            smsCoupon.setUpdateBy(ShiroUtils.getLoginName());
        }
        smsCoupon.setUpdateTime(DateUtils.getNowDate());
        return smsCouponMapper.updateSmsCoupon(smsCoupon);
    }

    /**
     * 优惠券修改处理
     * @param smsCoupon
     * @param selectIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProcess(SmsCoupon smsCoupon, String selectIds) {
        operateValidate(smsCoupon.getId(), SmsCouponConst.operateBtnEnum.BTN_EIDT);

        SmsCouponConst.useTypeEnum useTypeEnum =  SmsCouponConst.useTypeEnum.get(smsCoupon.getUseType());
        Checker.check(ObjectUtils.isEmpty(useTypeEnum), "优惠券使用范围异常");

        if (ObjectUtils.isEmpty(smsCoupon.getEnableTime())) {
            smsCoupon.setEnableTime(smsCoupon.getEndTime());
        }
        updateSmsCoupon(smsCoupon);
        Long couponId = smsCoupon.getId();
        log.info("修改优惠券信息，优惠券ID: {}", couponId);

        clearCouponRelation(couponId);

        craeteCouponRelation(useTypeEnum, couponId, selectIds);
    }

    /**
     * 建立优惠券与指定分类或商品的管理
     * @param useTypeEnum
     * @param couponId
     * @param selectIds 指定的分类或商品ID
     */
    private void craeteCouponRelation(SmsCouponConst.useTypeEnum useTypeEnum, Long couponId, String selectIds) {
        log.info("根据使用范围，维护分类或商品关系表");
        switch (useTypeEnum) {
            case ALLUSE:
                log.info("店铺全场通用");
                break;
            case CLASSIFYUSE:
                log.info("店铺指定分类");
                smsCouponProductCateRelationService.batchInsertCateForCoupon(couponId, selectIds);
                break;
            case PRODUCTUSE:
                log.info("店铺指定商品");
                smsCouponProductRelationService.batchInsertProductForCoupon(couponId, selectIds);
                break;
            default:
                log.error("使用范围异常");
                break;
        }
    }

    /**
     * 清除优惠券指定的分类和商品
     * @param couponId
     */
    private void clearCouponRelation(Long couponId) {
        smsCouponProductCateRelationService.deleteByCouponId(couponId);
        log.info("清除优惠券指定分类");
        smsCouponProductRelationService.deleteByCouponId(couponId);
        log.info("清除优惠券指定商品");
    }

    /**
     * 校验是否满足操作执行需要的状态
     * @param couponId
     * @param btnEnum
     */
    @Override
    public void operateValidate(Long couponId, SmsCouponConst.operateBtnEnum btnEnum) {
        SmsCoupon smsCoupon = selectSmsCouponById(couponId);
        operateValidate(smsCoupon, btnEnum);
    }

    @Override
    public void operateValidate(SmsCoupon smsCoupon, SmsCouponConst.operateBtnEnum btnEnum) {
        log.info("校验是否满足操作执行需要的状态");
        Checker.check(ObjectUtils.isEmpty(smsCoupon), "优惠券数据异常");
        Checker.checkOp(Arrays.asList(btnEnum.getStates()).contains(smsCoupon.getStatus()),"操作异常，请刷新后重试！");
    }

    /**
     * 删除优惠券对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除优惠券数量
     */
    @Override
    public int deleteSmsCouponByIds(String ids) {
        return smsCouponMapper.deleteSmsCouponByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除优惠券信息
     *
     * @param id 优惠券ID
     * @return 删除优惠券数量
     */
    @Override
    public int deleteSmsCouponById(Long id) {
        operateValidate(id, SmsCouponConst.operateBtnEnum.BTN_DELETE);
        return smsCouponMapper.deleteSmsCouponById(id);
    }

    /**
     * 优惠券上架
     * @param id
     * @return
     */
    @Override
    public int putawayCoupon(Long id) {
        operateValidate(id, SmsCouponConst.operateBtnEnum.BTN_PUTAWAY);
        SmsCoupon smsCoupon = new SmsCoupon();
        smsCoupon.setId(id);
        smsCoupon.setStatus(SmsCouponConst.StatusEnum.PUTAWAY.getType());
        return updateSmsCoupon(smsCoupon);
    }

    /**
     * 优惠券下架
     * @param id
     * @return
     */
    @Override
    public int soldoutCoupon(Long id) {
        operateValidate(id, SmsCouponConst.operateBtnEnum.BTN_SOLDOUT);
        SmsCoupon smsCoupon = new SmsCoupon();
        smsCoupon.setId(id);
        smsCoupon.setStatus(SmsCouponConst.StatusEnum.SOLDOUT.getType());
        return updateSmsCoupon(smsCoupon);
    }

    /**
     * (app)根据商品id查询商品的可用优惠券
     * @return
     */
    @Override
    public List<SmsCoupon> getUsableCouponsByProductId(Long productId,Long memberId) {
        PmsProduct product = iPmsProductService.selectPmsProductById(productId);
        Checker.check(ObjectUtils.isEmpty(product), "商品已下架/不存在！");
        return smsCouponMapper.getUsableCouponsByProductId(productId,product.getShopId(),memberId);
    }
    /**
     * (app)根据店铺id查询商品的可用优惠券
     * @return
     */
    @Override
    public List<SmsCoupon> getUsableCouponsByShopId(Long shopId,Long memberId) {
        return smsCouponMapper.getUsableCouponsByShopId(shopId,memberId);
    }
    /**
     * (app)获取优惠券并验证是否可用
     * @return
     */
    @Override
    public SmsCoupon getUsableCouponsById(Long couponId, Long memberId) {
        return smsCouponMapper.getUsableCouponsById(couponId,memberId);
    }
    /**
     * 领取优惠券 减库存 加领取数量
     * @param couponId
     * @return
     */
    @Override
    public int memeberGetCoupon(Long couponId) {
        return smsCouponMapper.memeberGetCoupon(couponId);
    }
    /**
     * 添加使用量
     * @param couponId
     * @return
     */
    @Override
    public int memeberUseCoupon(Long couponId) {
        return smsCouponMapper.memeberUseCoupon(couponId);
    }

    /**
     * 获取用户可用优惠券
     * @param memberId
     * @return
     */
    @Override
    public List<SmsMemberCouponDetail> getMemberEnableCoupon(Long memberId,String useStatus) {
        return smsCouponMapper.getMemberEnableCoupon(memberId,useStatus);
    }
    /**
     * 主动发放优惠券
     * @param id 用户id
     * @param shopId 上级用户的店铺id
     */
    @Override
    public int offerCoupons(Long id, Long shopId) {
        //查询上级所在店铺主动方法的优惠券
        List<SmsCoupon>  coupons = smsCouponMapper.getOfferCouponsByShopId(shopId, SmsCouponConst.getTypeEnum.GIVE.getType());
        //redis 放入提示语标记
        shopRedis.setOfferCoupons(String.valueOf(id),coupons);
        //商家批量发放优惠券到历史记录中
        return iSmsCouponHistoryService.batchInsertCouponHistory(coupons,id);
    }

    /**
     * 批量发放优惠券
     * @param memberIds
     * @param couponId
     * @return
     */
    @Override
    public String beachGiveCoupon(String memberIds, Long couponId) {
        //验证参数
        Long[] ids = Convert.toLongArray(memberIds);
        List<UmsMember> umsMembers = iUmsMemberService.selectUmsMemberByIds(ids);
        Checker.check(ObjectUtils.isEmpty(ids),"会员参数缺失");
        SmsCoupon coupon = smsCouponMapper.getUsableCouponsById(couponId,null);
        Checker.check(ObjectUtils.isEmpty(coupon),"优惠券领取结束");
        Checker.check(coupon.getCount()<=coupon.getReceiveCount(),"优惠券库存不足");
        AtomicInteger successNum = new AtomicInteger( 0 );
        AtomicInteger failureNum = new AtomicInteger( 0 );
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        //设置优惠劵发送的openid缓冲流
        StringBuilder openidstr = new StringBuilder();
        //优惠劵发送模板消息状态
        String sendcouponstatus="";
        //发放优惠券接口
        umsMembers.stream().forEach( member -> {
            try {
                // 插入新数据
                int count = pmsCouponService.giveCoupon(coupon,member.getId());
                Checker.check( count != 1, "发放失败！");
                successMsg.append( "<br/>" + successNum.incrementAndGet() + "、用户 " + member.getNickname() + " 发放成功！" );
                openidstr.append(member.getOpenid()+",");
            } catch (Exception e) {
                String msg = "<br/>" + failureNum.incrementAndGet() + "、用户 " + member.getNickname() + " 发放失败：";
                failureMsg.append( msg + e.getMessage() );
                log.error( msg, e );
            }
        } );

        try{
            BizShopInfo bizShopInfo= iBizShopInfoService.selectBizShopInfoById(coupon.getShopId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(StringUtils.isNotEmpty(openidstr)) {
                String url_1 = url + "?url=https://gzh1.zmdnsyhxt.com&openid=" + openidstr
                        + "&shopname=" + bizShopInfo.getCompanyName() + "&couponanme=" + coupon.getName() +
                        "&startTime=" + sdf.format(coupon.getStartTime()) + "&endTime=" + sdf.format(coupon.getEndTime())
                        +"&shopId="+bizShopInfo.getId();
                MemberDataRsp rsp = restTemplate.getForObject(url_1, MemberDataRsp.class);
                if (rsp.getState()) {
                    sendcouponstatus = ",优惠劵模板消息发送成功";
                } else {
                    sendcouponstatus = ",优惠劵模板消息发送失败";
                }
            }
        }catch (Exception e){
            sendcouponstatus=",优惠劵模板消息发送失败";
        }


        //错误消息0条empAttendance
        if (failureNum.get() == 0) {
            successMsg.insert( 0, "恭喜您，优惠券全部发放成功！共 " + successNum.get() + " 张，数据如下：" );
            //return successMsg.toString();
            return "恭喜您，优惠券全部发放成功！共 " + successNum.get() + " 张"+sendcouponstatus;
        } else if(successNum.get() == 0){
            //全部是错误消息
            failureMsg.insert( 0, "很抱歉，优惠券发放失败！共 " + failureNum.get() + " 张发放失败，错误如下：" );
            throw new BusinessException( AjaxResult.Type.ERROR.value(), failureMsg.toString() );
        }else{
            //错误消息 和 正确消息都有
            successMsg.insert( 0, "很抱歉，优惠券未能全部发放成功！"+"<br/>"
                    +"发放成功 " + successNum.get() + " 张！" );
            failureMsg.insert( 0, "发放失败,共 " + failureNum.get() + " 张，错误如下：" );
            throw new BusinessException( AjaxResult.Type.WARN.value(),  successMsg.toString() + "<br/>" +failureMsg.toString() );
        }
    }
}
