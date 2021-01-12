package com.fante.project.mapperBase;

import com.fante.project.business.omsCartItem.domain.OmsCartItem;
import java.util.List;

/**
 * 购物车Mapper基础接口
 *
 * @author fante
 * @date 2020-04-08
 */
public interface OmsCartItemMapperBase {
    /**
     * 查询购物车
     *
     * @param id 购物车ID
     * @return 购物车
     */
    public OmsCartItem selectOmsCartItemById(Long id);

    /**
     * 查询购物车列表
     *
     * @param omsCartItem 购物车
     * @return 购物车集合
     */
    public List<OmsCartItem> selectOmsCartItemList(OmsCartItem omsCartItem);

    /**
     * 查询购物车数量
     *
     * @param omsCartItem 购物车
     * @return 结果
     */
    public int countOmsCartItem(OmsCartItem omsCartItem);

    /**
     * 唯一校验
     *
     * @param omsCartItem 购物车
     * @return 结果
     */
    public int checkOmsCartItemUnique(OmsCartItem omsCartItem);

    /**
     * 新增购物车
     *
     * @param omsCartItem 购物车
     * @return 结果
     */
    public int insertOmsCartItem(OmsCartItem omsCartItem);

    /**
     * 修改购物车
     *
     * @param omsCartItem 购物车
     * @return 结果
     */
    public int updateOmsCartItem(OmsCartItem omsCartItem);

    /**
     * 删除购物车
     *
     * @param id 购物车ID
     * @return 结果
     */
    public int deleteOmsCartItemById(Long id);

    /**
     * 批量删除购物车
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsCartItemByIds(String[] ids);

}
