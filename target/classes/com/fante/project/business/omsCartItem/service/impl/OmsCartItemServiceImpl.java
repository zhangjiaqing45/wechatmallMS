package com.fante.project.business.omsCartItem.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.framework.jwt.utils.JwtUtils;
import com.fante.project.api.appView.domain.ShopOfProductDatas;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;
import com.fante.project.business.omsCartItem.domain.OmsCartItemResult;
import com.fante.project.business.pmsProduct.domain.PmsProductResult;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.omsCartItem.mapper.OmsCartItemMapper;
import com.fante.project.business.omsCartItem.domain.OmsCartItem;
import com.fante.project.business.omsCartItem.service.IOmsCartItemService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * 购物车Service业务层处理
 *
 * @author fante
 * @date 2020-03-28
 */
@Service
public class OmsCartItemServiceImpl implements IOmsCartItemService {

    private static Logger log = LoggerFactory.getLogger(OmsCartItemServiceImpl.class);

    @Autowired
    private OmsCartItemMapper omsCartItemMapper;

    /**
     * 查询购物车
     *
     * @param id 购物车ID
     * @return 购物车
     */
    @Override
    public OmsCartItem selectOmsCartItemById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsCartItemMapper.selectOmsCartItemById(id);
    }

    /**
     * 查询购物车列表
     *
     * @param omsCartItem 购物车
     * @return 购物车集合
     */
    @Override
    public List<OmsCartItem> selectOmsCartItemList(OmsCartItem omsCartItem) {
        return omsCartItemMapper.selectOmsCartItemList(omsCartItem);
    }

    /**
     * 查询购物车数量
     *
     * @param omsCartItem 购物车
     * @return 结果
     */
    @Override
    public int countOmsCartItem(OmsCartItem omsCartItem){
        return omsCartItemMapper.countOmsCartItem(omsCartItem);
    }

    /**
     * 唯一校验
     *
     * @param omsCartItem 购物车
     * @return 结果
     */
    @Override
    public String checkOmsCartItemUnique(OmsCartItem omsCartItem) {
        return omsCartItemMapper.checkOmsCartItemUnique(omsCartItem) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增购物车
     *
     * @param omsCartItem 购物车
     * @return 新增购物车数量
     */
    @Override
    public int insertOmsCartItem(OmsCartItem omsCartItem) {
        omsCartItem.setCreateTime(DateUtils.getNowDate());
        return omsCartItemMapper.insertOmsCartItem(omsCartItem);
    }

    /**
     * 修改购物车
     *
     * @param omsCartItem 购物车
     * @return 修改购物车数量
     */
    @Override
    public int updateOmsCartItem(OmsCartItem omsCartItem) {
        omsCartItem.setUpdateTime(DateUtils.getNowDate());
        return omsCartItemMapper.updateOmsCartItem(omsCartItem);
    }

    /**
     * 删除购物车对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除购物车数量
     */
    @Override
    public int deleteOmsCartItemByIds(String ids,Long memberId) {
        return omsCartItemMapper.deleteMemberCartByIds(Convert.toStrArray(ids),memberId);
    }

    /**
     * 删除购物车信息
     *
     * @param id 购物车ID
     * @return 删除购物车数量
     */
    @Override
    public int deleteOmsCartItemById(Long id) {
        return omsCartItemMapper.deleteOmsCartItemById(id);
    }
    /**
     * 查询当前登录用户的购物车
     * note: 只显示cartType=1的购物车
     * @return
     */
    @Override
    public List<OmsCartItemResult> selectOmsCartItemListOfMember(Long memberId) {
        OmsCartItem param = new OmsCartItem();
        //设置显示当前用户且购物车类型为添加购物车的购物车商品
        param.setMemberId(memberId);
        param.setCartType(OrderConst.CartType.CART.getType());
        return omsCartItemMapper.selectOmsCartItemListOfMember(param);
    }
    /**
     * 根据ids查询下单所需数据
     * @param ids
     * @return
     */
    @Override
    public List<OmsCartItemResult> getCartDataForCreateOrder(String ids,Long memberId) {
        Checker.check(StringUtils.isEmpty(ids),"至少选择一件商品下单.");
        OmsCartItem param = new OmsCartItem();
        //设置显示当前用户且购物车类型为添加购物车的购物车商品
        param.setMemberId(memberId);
        param.setCartType(OrderConst.CartType.CART.getType());
        param.setParams(new HashMap<String, Object>(){{put("ids",Convert.toStrArray(ids));}});
        return omsCartItemMapper.selectOmsCartItemListForOrder(param);
    }

    /**
     * 添加购物车
     * 1.秒杀商品
     * 2.普通商品
     * 说明: a.加入购物车不判断库存,下单时判断库存
     *      b.方法结束后 参数 cartItemParam 会携带插入或者 更新的购物车id
     * @param cartItemParam 调用此方法后cartItem的id 将会放入其中可直接取出使用,相当于返回值
     * @return 添加成功数量
     */
    @Override
    public int add(OmsCartItemParam cartItemParam) {
        int count;
        //必须参数: skuId
        Checker.checkOp(cartItemParam.getQuantity()>0,"请填写正确的购买数量！");
        //验证购物车类型
        String cartType = cartItemParam.getCartType();
        OrderConst.CartType type = OrderConst.CartType.get(cartType);
        Checker.check(ObjectUtils.isEmpty(type),"参数错误！");
        //通过productSkuId查询出购物车中需要的信息 (没有用户信息哦！)
        OmsCartItem omsCartItem = omsCartItemMapper.selectCartMsgBySkuId(cartItemParam.getProductSkuId());
        Checker.check(ObjectUtils.isEmpty(omsCartItem),"该商品已下架！");
        //设置用户信息
        omsCartItem.setMemberId(cartItemParam.getMemberId());
        //设置购物车类型
        omsCartItem.setCartType(cartType);

        //判断购物车中是否已存在该商品
        OmsCartItem existCartItem = getCartItem(omsCartItem);

        //不存在:直接存入个人购物车
        if (ObjectUtils.isEmpty(existCartItem)) {
            //设置创建时间
            omsCartItem.setCreateTime(DateUtils.getNowDate());
            //设置购买数量
            omsCartItem.setQuantity(cartItemParam.getQuantity());
            count = omsCartItemMapper.insertOmsCartItem(omsCartItem);
            //将id返回到参数中
            cartItemParam.setCartId(omsCartItem.getId());
        } else {
            //存在:更新该商品数量+n
            OmsCartItem update = new OmsCartItem();
            //设置更新时间 id 和 累加的数量.
            update.setUpdateTime(DateUtils.getNowDate());
            update.setId(existCartItem.getId());
            update.setQuantity(existCartItem.getQuantity() + cartItemParam.getQuantity());
            count = omsCartItemMapper.updateOmsCartItem(update);
            //将id返回到参数中
            cartItemParam.setCartId(existCartItem.getId());
        }
        return count;
    }

    /**
     * 根据 会员id 商品skuid 和 店铺id 和 购物车类型 获取购物车中商品
     * 说明:根据商品类型对应不同的处理方式:
     * 1.普通商品 查询到了直接 返回
     * 2.团购商品 查询到了直接删除 返回null
     * 3.普通商品 查询到了直接删除 返回null
     * 4.秒杀商品 查询到了直接删除 返回null
     * 5:积分订单 查询到了直接删除 返回null
     * @param cartItem
     * @return OmsCartItem
     */
    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        //获取购物车类型
        String cartType = cartItem.getCartType();
        //通过用户id 商品skuid 店铺id 购物车类型 查询购物车信息
        OmsCartItem search = new OmsCartItem();
        search.setMemberId(cartItem.getMemberId());
        search.setShopId(cartItem.getShopId());
        search.setProductSkuId(cartItem.getProductSkuId());
        search.setCartType(cartType);
        List<OmsCartItem> cartItemList = omsCartItemMapper.selectOmsCartItemList(search);
        if (CollectionUtils.isEmpty(cartItemList)) {
            return null;
        }
        //普通商品 查询到了返回本cartItem
        if(StringUtils.equals(cartType,OrderConst.CartType.CART.getType())){
            return cartItemList.get(0);
        }else{
            //其他的需要删除购物车 返回null(订单生成时也会删除购物车,这里防止漏网之鱼)
            omsCartItemMapper.deleteOmsCartItemById(cartItemList.get(0).getId());
        }
        return null;
    }
    /**
     * 修改购物车数量
     * @return
     */
    @Override
    public int changeQuantity(OmsCartItemParam cartParam) {
        Long cartId = cartParam.getCartId();
        OmsCartItem oldCart = omsCartItemMapper.selectOmsCartItemById(cartId);
        Checker.check(ObjectUtils.isEmpty(oldCart),"此商品不存在！");
        Checker.checkOp(Objects.equals(oldCart.getCartType(), OrderConst.CartType.CART.getType()),"只能修改购物车中的商品数量！");
        Checker.checkOp(Optional.ofNullable(cartParam.getQuantity()).orElse(-1L)>0,"购买数量不可小于1！");
        //更新该商品数量+n
        OmsCartItem update = new OmsCartItem();
        //设置更新时间 id 和 累加的数量.
        update.setUpdateTime(DateUtils.getNowDate());
        update.setId(oldCart.getId());
        update.setQuantity(cartParam.getQuantity());
        update.setMemberId(cartParam.getMemberId());
        return omsCartItemMapper.changeQuantity(update);
    }
    /**
     * 根据购物车获取相应到店铺对应商品的数据
     * @param cartIds
     * @return
     */
    @Override
    public List<ShopOfProductDatas> getShopOfProudctsData(String cartIds, Long memberId) {
        return omsCartItemMapper.getShopOfProudctsData(Convert.toStrArray(cartIds),memberId);
    }
}
