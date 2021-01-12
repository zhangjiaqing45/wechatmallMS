package com.fante.project.business.smsCouponHistory.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.project.api.appView.domain.CouponHistoryDetail;
import com.fante.project.api.omsOrderProcess.domain.SmsCouponHistoryDetail;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistoryDTO;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsCouponHistory.mapper.SmsCouponHistoryMapper;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponHistory.service.ISmsCouponHistoryService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 优惠券使用、领取历史Service业务层处理
 *
 * @author fante
 * @date 2020-03-20
 */
@Service
public class SmsCouponHistoryServiceImpl implements ISmsCouponHistoryService {

    private static Logger log = LoggerFactory.getLogger(SmsCouponHistoryServiceImpl.class);

    @Autowired
    private SmsCouponHistoryMapper smsCouponHistoryMapper;

    /**
     * 查询优惠券使用、领取历史
     *
     * @param id 优惠券使用、领取历史ID
     * @return 优惠券使用、领取历史
     */
    @Override
    public SmsCouponHistory selectSmsCouponHistoryById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsCouponHistoryMapper.selectSmsCouponHistoryById(id);
    }

    /**
     * 查询优惠券使用、领取历史列表
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 优惠券使用、领取历史集合
     */
    @Override
    public List<SmsCouponHistory> selectSmsCouponHistoryList(SmsCouponHistory smsCouponHistory) {
        return smsCouponHistoryMapper.selectSmsCouponHistoryList(smsCouponHistory);
    }

    /**
     * 查询优惠券使用、领取历史列表DTO
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 优惠券使用、领取历史集合
     */
    @Override
    public List<SmsCouponHistoryDTO> selectSmsCouponHistoryListDTO(SmsCouponHistoryDTO smsCouponHistory) {
        return smsCouponHistoryMapper.selectSmsCouponHistoryListDTO(smsCouponHistory);
    }

    /**
     * 查询优惠券使用、领取历史数量
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 结果
     */
    @Override
    public int countSmsCouponHistory(SmsCouponHistory smsCouponHistory){
        return smsCouponHistoryMapper.countSmsCouponHistory(smsCouponHistory);
    }

    /**
     * 唯一校验
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 结果
     */
    @Override
    public String checkSmsCouponHistoryUnique(SmsCouponHistory smsCouponHistory) {
        return smsCouponHistoryMapper.checkSmsCouponHistoryUnique(smsCouponHistory) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增优惠券使用、领取历史
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 新增优惠券使用、领取历史数量
     */
    @Override
    public int insertSmsCouponHistory(SmsCouponHistory smsCouponHistory) {
        smsCouponHistory.setCreateTime(DateUtils.getNowDate());
        return smsCouponHistoryMapper.insertSmsCouponHistory(smsCouponHistory);
    }

    /**
     * 修改优惠券使用、领取历史
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 修改优惠券使用、领取历史数量
     */
    @Override
    public int updateSmsCouponHistory(SmsCouponHistory smsCouponHistory) {
        return smsCouponHistoryMapper.updateSmsCouponHistory(smsCouponHistory);
    }

    /**
     * 删除优惠券使用、领取历史对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除优惠券使用、领取历史数量
     */
    @Override
    public int deleteSmsCouponHistoryByIds(String ids) {
        return smsCouponHistoryMapper.deleteSmsCouponHistoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除优惠券使用、领取历史信息
     *
     * @param id 优惠券使用、领取历史ID
     * @return 删除优惠券使用、领取历史数量
     */
    @Override
    public int deleteSmsCouponHistoryById(Long id) {
        return smsCouponHistoryMapper.deleteSmsCouponHistoryById(id);
    }
    /**
     * 获取用户 可用 优惠券
     * 1.获取用户优惠券领取历史
     * 2.根据历史联合查询优惠券中未使用的,并且 优惠券的过期时间未到 和 优惠券上架的状态
     * @param memberId
     * @return
     */
    @Override
    public List<SmsCouponHistoryDetail> getMemberCouponUsable(Long memberId,Long shopId) {
        return smsCouponHistoryMapper.getMemberCouponUsable(memberId,shopId);
    }
    /**
     * 使用优惠券 修改使用状态
     * @param couponHistory
     * @return
     */
    @Override
    public int updateForUseCoupon(SmsCouponHistory couponHistory) {
        return smsCouponHistoryMapper.updateForUseCoupon(couponHistory);
    }
    /**
     * 商家批量发放优惠券到历史记录中
     * @param coupons
     * @param memberId
     * @return
     */
    @Override
    public int batchInsertCouponHistory(List<SmsCoupon> coupons, Long memberId) {
        return smsCouponHistoryMapper.batchInsertCouponHistory(coupons,memberId);
    }
    /**
     * 根据code查询优惠券领取历史信息
     * @param code
     * @return
     */
    @Override
    public CouponHistoryDetail selectSmsCouponHistoryByCode(String code) {
        return smsCouponHistoryMapper.selectSmsCouponHistoryByCode(code);
    }
    
    /**
     * 根据code查询优惠券使用状态
     * @param code
     * @return
     */
    public String queryCouponUseStatusByCode(String code){
        return smsCouponHistoryMapper.queryCouponUseStatusByCode(code);
    }

    public Integer updateUserState(){
       List<SmsCouponHistory> list= smsCouponHistoryMapper.selectSmsCouponHistoryUserState();
       int i=0;
       if(list.size()>0){
           for(SmsCouponHistory history : list){
               history.setUseStatus("2");
               i+=smsCouponHistoryMapper.updateSmsCouponHistory(history);
           }
       }
        return i;
    };

}
