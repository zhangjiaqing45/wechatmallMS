package com.fante.project.mapperBase;

import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import java.util.List;

/**
 * 优惠券Mapper基础接口
 *
 * @author fante
 * @date 2020-03-19
 */
public interface SmsCouponMapperBase {
    /**
     * 查询优惠券
     *
     * @param id 优惠券ID
     * @return 优惠券
     */
    public SmsCoupon selectSmsCouponById(Long id);

    /**
     * 查询优惠券列表
     *
     * @param smsCoupon 优惠券
     * @return 优惠券集合
     */
    public List<SmsCoupon> selectSmsCouponList(SmsCoupon smsCoupon);

    /**
     * 查询优惠券数量
     *
     * @param smsCoupon 优惠券
     * @return 结果
     */
    public int countSmsCoupon(SmsCoupon smsCoupon);

    /**
     * 唯一校验
     *
     * @param smsCoupon 优惠券
     * @return 结果
     */
    public int checkSmsCouponUnique(SmsCoupon smsCoupon);

    /**
     * 新增优惠券
     *
     * @param smsCoupon 优惠券
     * @return 结果
     */
    public int insertSmsCoupon(SmsCoupon smsCoupon);

    /**
     * 修改优惠券
     *
     * @param smsCoupon 优惠券
     * @return 结果
     */
    public int updateSmsCoupon(SmsCoupon smsCoupon);

    /**
     * 删除优惠券
     *
     * @param id 优惠券ID
     * @return 结果
     */
    public int deleteSmsCouponById(Long id);

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsCouponByIds(String[] ids);

}
