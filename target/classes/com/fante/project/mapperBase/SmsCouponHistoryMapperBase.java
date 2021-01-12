package com.fante.project.mapperBase;

import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import java.util.List;

/**
 * 优惠券使用、领取历史Mapper基础接口
 *
 * @author fante
 * @date 2020-03-20
 */
public interface SmsCouponHistoryMapperBase {
    /**
     * 查询优惠券使用、领取历史
     *
     * @param id 优惠券使用、领取历史ID
     * @return 优惠券使用、领取历史
     */
    public SmsCouponHistory selectSmsCouponHistoryById(Long id);

    /**
     * 查询优惠券使用、领取历史列表
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 优惠券使用、领取历史集合
     */
    public List<SmsCouponHistory> selectSmsCouponHistoryList(SmsCouponHistory smsCouponHistory);

    /**
     * 查询优惠券使用、领取历史数量
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 结果
     */
    public int countSmsCouponHistory(SmsCouponHistory smsCouponHistory);

    /**
     * 唯一校验
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 结果
     */
    public int checkSmsCouponHistoryUnique(SmsCouponHistory smsCouponHistory);

    /**
     * 新增优惠券使用、领取历史
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 结果
     */
    public int insertSmsCouponHistory(SmsCouponHistory smsCouponHistory);

    /**
     * 修改优惠券使用、领取历史
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 结果
     */
    public int updateSmsCouponHistory(SmsCouponHistory smsCouponHistory);

    /**
     * 删除优惠券使用、领取历史
     *
     * @param id 优惠券使用、领取历史ID
     * @return 结果
     */
    public int deleteSmsCouponHistoryById(Long id);

    /**
     * 批量删除优惠券使用、领取历史
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsCouponHistoryByIds(String[] ids);

    public List<SmsCouponHistory> selectSmsCouponHistoryUserState();

}
