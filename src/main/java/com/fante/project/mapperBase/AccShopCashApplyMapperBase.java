package com.fante.project.mapperBase;

import com.fante.project.business.accShopCashApply.domain.AccShopCashApply;
import java.util.List;

/**
 * 店铺提现记录Mapper基础接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface AccShopCashApplyMapperBase {
    /**
     * 查询店铺提现记录
     *
     * @param id 店铺提现记录ID
     * @return 店铺提现记录
     */
    public AccShopCashApply selectAccShopCashApplyById(Long id);

    /**
     * 查询店铺提现记录列表
     *
     * @param accShopCashApply 店铺提现记录
     * @return 店铺提现记录集合
     */
    public List<AccShopCashApply> selectAccShopCashApplyList(AccShopCashApply accShopCashApply);

    /**
     * 查询店铺提现记录数量
     *
     * @param accShopCashApply 店铺提现记录
     * @return 结果
     */
    public int countAccShopCashApply(AccShopCashApply accShopCashApply);

    /**
     * 唯一校验
     *
     * @param accShopCashApply 店铺提现记录
     * @return 结果
     */
    public int checkAccShopCashApplyUnique(AccShopCashApply accShopCashApply);

    /**
     * 新增店铺提现记录
     *
     * @param accShopCashApply 店铺提现记录
     * @return 结果
     */
    public int insertAccShopCashApply(AccShopCashApply accShopCashApply);

    /**
     * 修改店铺提现记录
     *
     * @param accShopCashApply 店铺提现记录
     * @return 结果
     */
    public int updateAccShopCashApply(AccShopCashApply accShopCashApply);

    /**
     * 删除店铺提现记录
     *
     * @param id 店铺提现记录ID
     * @return 结果
     */
    public int deleteAccShopCashApplyById(Long id);

    /**
     * 批量删除店铺提现记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccShopCashApplyByIds(String[] ids);

}
