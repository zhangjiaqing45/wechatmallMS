package com.fante.project.business.omsCartItem.service;

import com.fante.project.api.appView.domain.ShopOfProductDatas;
import com.fante.project.business.omsCartItem.domain.OmsCartItem;
import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;
import com.fante.project.business.omsCartItem.domain.OmsCartItemResult;

import java.util.HashMap;
import java.util.List;

/**
 * 购物车Service接口
 *
 * @author fante
 * @date 2020-03-28
 */
public interface IOmsCartItemService {
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
    public String checkOmsCartItemUnique(OmsCartItem omsCartItem);

    /**
     * 新增购物车
     *
     * @param omsCartItem 购物车
     * @return 新增购物车数量
     */
    public int insertOmsCartItem(OmsCartItem omsCartItem);

    /**
     * 修改购物车
     *
     * @param omsCartItem 购物车
     * @return 修改购物车数量
     */
    public int updateOmsCartItem(OmsCartItem omsCartItem);

    /**
     * 批量删除购物车
     *
     * @param ids 需要删除的数据ID
     * @return 删除购物车数量
     */
    public int deleteOmsCartItemByIds(String ids,Long memberId);

    /**
     * 删除购物车信息
     *
     * @param id 购物车ID
     * @return 删除购物车数量
     */
    public int deleteOmsCartItemById(Long id);

    /**
     * 查询当前登录用户的购物车
     * note: 只显示cartType=1的购物车
     * @return
     */
    public List<OmsCartItemResult> selectOmsCartItemListOfMember(Long memberId);
    /**
     * 添加购物车
     * 1.秒杀商品
     * 2.普通商品
     * 说明: a.加入购物车不判断库存,下单时判断库存
     *      b.方法结束后 参数 cartItemParam 会携带插入或者 更新的购物车id
     * @param cartItemParam
     * @return
     */
    public int add(OmsCartItemParam cartItemParam);
    /**
     * 修改购物车数量
     * @return
     */
    public int changeQuantity(OmsCartItemParam cartParam);

    /**
     * 根据ids查询下单所需数据
     * @param ids
     * @return
     */
    List<OmsCartItemResult> getCartDataForCreateOrder(String ids,Long memberId);

    /**
     * 根据购物车获取相应到店铺对应商品的数据
     * @param cartIds
     * @return
     */
    List<ShopOfProductDatas> getShopOfProudctsData(String cartIds, Long memberId);


}
