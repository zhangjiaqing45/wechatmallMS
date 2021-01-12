package com.fante.project.api.omsOrderProcess.service.impl;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.SmsFlashConst;
import com.fante.common.business.enums.UmsAddressConst;
import com.fante.common.exception.BusinessException;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.text.Convert;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.ShopOfProductDatas;
import com.fante.project.api.appView.service.UmsAddressService;
import com.fante.project.api.omsOrderProcess.domain.*;
import com.fante.project.api.omsOrderProcess.service.IOmsPreviewOrderService;
import com.fante.project.api.omsOrderProcess.service.ISmsMemberCouponService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;
import com.fante.project.business.omsCartItem.domain.OmsCartItemResult;
import com.fante.project.business.omsCartItem.service.IOmsCartItemService;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.pmsFeightTemplate.service.IPmsFeightTemplateService;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsFlashPromotion.domain.SmsFlashPromotion;
import com.fante.project.business.smsFlashPromotion.service.ISmsFlashPromotionService;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProduct;
import com.fante.project.business.smsFlashPromotionProduct.service.ISmsFlashPromotionProductService;
import com.fante.project.business.smsFlashPromotionSession.service.ISmsFlashPromotionSessionService;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import com.fante.project.business.smsGroupGameSku.service.ISmsGroupGameSkuService;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.fante.project.business.umsMemberReceiveAddress.service.IUmsMemberReceiveAddressService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 各种下单的服务
 *
 * @author wz
 */
@Service
public class OmsPreviewOrderServiceImpl implements IOmsPreviewOrderService {
    private static Logger log = LoggerFactory.getLogger(OmsPreviewOrderServiceImpl.class);

    /**
     * 订单
     */
    @Autowired
    private IOmsOrderService iOmsOrderService;
    /**
     * 店铺
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;
    /**
     * 购物车
     */
    @Autowired
    private IOmsCartItemService iOmsCartItemService;
    /**
     * 收货地址
     */
    @Autowired
    private IUmsMemberReceiveAddressService iUmsMemberReceiveAddressService;
    /**
     * 收货地址
     */
    @Autowired
    private UmsAddressService umsAddressService;
    /**
     * 运费
     */
    @Autowired
    private IPmsFeightTemplateService iPmsFeightTemplateService;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;
    /**
     * 商品sku
     */
    @Autowired
    private IPmsSkuStockService iPmsSkuStockService;
    /**
     * 处理优惠券
     */
    @Autowired
    private ISmsMemberCouponService iSmsMemberCouponService;
    /**
     * 团购查询
     */
    @Autowired
    private ISmsGroupGameSkuService iSmsGroupGameSkuService;
    /**
     * 秒杀sku关系表查询
     */
    @Autowired
    private ISmsFlashPromotionSkuService iSmsFlashPromotionSkuService;
    /**
     * 秒杀商品关系表查询
     */
    @Autowired
    private ISmsFlashPromotionProductService iSmsFlashPromotionProductService;
    /**
     * 秒杀活动
     */
    @Autowired
    private ISmsFlashPromotionService iSmsFlashPromotionService;
    /**
     * 秒杀时间段
     */
    @Autowired
    private ISmsFlashPromotionSessionService iSmsFlashPromotionSessionService;

    /**
     * ###########从购物车中下单###########
     * 1.根据购物车 把各个店铺的订单 分开。
     * 2.依次 计算 各个订单 的总金额、活动优惠、应付金额。
     * 3.根据购物车对象的列表集合生成确认订单信息
     *
     * @param ids 购物车ids
     * @return
     */
    @Override
    public List<OmsOrderShow> createPreviewByCart(String ids, Long memberId, Long addressId) {
        //声明返回的确认订单数据
        List<OmsOrderShow> returnData = new ArrayList<>();
        //根据订单数组 分单操作
        //1.根据id查询出数据,格式为 一个店铺 对应多个 购物车列表
        List<OmsCartItemResult> cartDatas = iOmsCartItemService.getCartDataForCreateOrder(ids, memberId);
        Integer count = cartDatas.stream().map(x -> x.getCartList().size()).reduce((A, B) -> A + B).orElse(0);
        Checker.check(count != Convert.toStrArray(ids).length, "购物车信息已过期.");
        //cartDatas中有 几个结果 生成几个订单
        //遍历各个店铺依次处理各个订单 搜集 展示信息
        cartDatas.stream().forEach(data -> {
            //单个订单
            //从购物车中过滤出所有购物车id拼接成字符串
            String cartIds = data.getCartList().stream()
                    .map(cart -> String.valueOf(cart.getId()))
                    .reduce((cartId, next) -> cartId + "," + next)
                    .get();
            //2.根据cartIds查询所需的sku信息分类id 购买数量封装到CartSkuDto集合中
            //注意：当商品下架或者 删除 这里就查不到了
            List<CartSkuDto> cartSkus = iPmsSkuStockService.selectPmsSkuStockByCartIds(cartIds);
            //3.根据购物车对象的列表集合生成确认订单信息
            OmsOrderShow orderShow = generatePreviewOrder(cartSkus, false, memberId, addressId);
            //生成信息回显
            returnData.add(orderShow);
        });
        return returnData;
    }

    /**
     * ###########立即购买###########
     * 2.根据购物车Id获取购物车数据对象
     * 3.根据购物车对象的列表集合生成确认订单信息
     *
     * @return
     */
    @Override
    public List<OmsOrderShow> createPreviewByNow(String id, Long tokenUserId, Long addressId) {
        //2.根据购物车Id获取购物车数据对象
        List<CartSkuDto> cartSkus = iPmsSkuStockService.selectPmsSkuStockByCartIds(id);
        //3.根据购物车对象的列表集合生成确认订单信息
        OmsOrderShow orderShow = generatePreviewOrder(cartSkus, false, tokenUserId, addressId);
        return Arrays.asList(orderShow);
    }

    /**
     * ###########团购###########
     * 1.根据购物车Id 和 团购信息 获取购物车数据对象：处理价格
     * 2.根据购物车对象的列表集合生成确认订单信息
     *
     * @param param
     * @return
     */
    @Override
    public OmsOrderShow createPreviewByGroup(OmsCartItemParam param) {
        Checker.check(ObjectUtils.isEmpty(param), "参数缺失！");
        Checker.check(ObjectUtils.isEmpty(param.getQuantity()), "商品数量不能为空！");
        Checker.check(ObjectUtils.isEmpty(param.getProductSkuId()), "至少选择一个商品！");
        Checker.check(ObjectUtils.isEmpty(param.getPromotionId()), "活动信息错误！");

        //根据skuID查询CartSku需要的数据
        CartSkuDto cartSku = iSmsGroupGameSkuService.getCartSkuDtoBySkuId(param.getProductSkuId());
        Checker.check(ObjectUtils.isEmpty(cartSku), "该商品团购活动已结束.");
        //检查传来的活动skuid和通过商品skuId查到的id 是否一致 ,不一致则抛出异常
        Checker.checkOp(Objects.equals(cartSku.getGameSkuId(), param.getPromotionId()), "该商品团购活动已结束.");
        //设置购买数量
        cartSku.setQuantity(param.getQuantity());
        //团购没有锁定库存,没货直接返回团购失败
        cartSku.setLockStock(0L);
        //获取团购价格设置到cartSkus中
        return generatePreviewOrder(Arrays.asList(cartSku), true, param.getMemberId(), param.getAddressId());
    }

    /**
     * ###########秒杀###########
     * 1.利用购物车收集数据 并得到 购物车Id
     * 2.根据购物车Id 和 秒杀信息 获取购物车数据对象：处理价格
     * 3.根据购物车对象的列表集合生成确认订单信息
     *
     * @param param
     * @return
     */
    @Override
    public OmsOrderShow createPreviewBySeckill(OmsCartItemParam param) {
        Checker.check(ObjectUtils.isEmpty(param), "参数缺失！");
        Checker.check(ObjectUtils.isEmpty(param.getQuantity()), "商品数量不能为空！");
        Checker.check(ObjectUtils.isEmpty(param.getProductSkuId()), "至少选择一个商品！");
        Checker.check(ObjectUtils.isEmpty(param.getPromotionId()), "活动信息错误！");

        //根据活动id 和 skuId查询 商品规格的秒杀价格 和 库存
        //判断活动是否过期
        int i = iSmsFlashPromotionSkuService.validateGameTimeOut(param.getPromotionId(), new ValidateStockDTO(param.getProductSkuId(), param.getQuantity()));
        Checker.check(i != 1, "秒杀活动已过期");
        //通过商品sku查询商品id并判断是否可用返回可用商品id
        CartSkuDto cartSku = iPmsSkuStockService.getProductDetailBySkuId(param.getProductSkuId());
        //根据id查询秒杀活动sku关系表
        SmsFlashPromotionSku smsFlashPromotionSku = iSmsFlashPromotionSkuService.selectSmsFlashPromotionSkuById(param.getPromotionId());

        //更新sku信息为秒杀信息
        cartSku.setQuantity(param.getQuantity());
        cartSku.setStock(smsFlashPromotionSku.getFlashPromotionCount());
        cartSku.setPromotionPrice(smsFlashPromotionSku.getFlashPromotionPrice());
        cartSku.setLockStock(smsFlashPromotionSku.getLockStock());
        //获取确认订单信息
        //获取团购价格设置到cartSkus中
        return generatePreviewOrder(Arrays.asList(cartSku), true, param.getMemberId(), param.getAddressId());
    }

    /**
     * --------获取确认订单信息---------
     * 描述:根据购物车对象的列表集合生成确认订单信息
     * 步骤: 1.判断为空 并 设置 cartSkus
     * 2.获取默认用户收货地址列表 判断是否包邮 得到 UmsMemberReceiveAddressResult
     * 3.获取用户可用优惠券组合列表 得到  Map<String, CartCouponDTO>
     * 4.计算订单总价格
     */
    public OmsOrderShow generatePreviewOrder(List<CartSkuDto> cartSkus, Boolean isPromotions, Long memberId, Long addressId) {
        //验证商品是否存在
        Checker.check(ObjectUtils.isEmpty(cartSkus), isPromotions ? "活动已经结束" : "商品已下架");
        //声明返回确认订单数据
        OmsOrderShow returnData = new OmsOrderShow();
        //验证库存并获取商品总价格
        //map: key->categoryId value->购物车中对应商品分类的所有商品的总和
        Optional<BigDecimal> totalOpt = cartSkus.stream()
                .map(skuDto -> {
                    //判断库存是否充足
                    Long quantity = skuDto.getQuantity();
                    Checker.check(skuDto.getStock() - skuDto.getLockStock() - quantity < 0,
                            "商品:" + skuDto.getProductName() + skuDto.getSpData().replaceAll("\"", "") + "库存不足");
                    //直接使用促销价格即可
                    BigDecimal realPrice = skuDto.getPromotionPrice();
                    //求和
                    return realPrice.multiply(new BigDecimal(quantity));
                }).reduce((A, B) -> A.add(B));
        //--------------设置订单总额
        returnData.setTotalPrice(totalOpt.get());
        //--------------设置购物车中sku的信息
        returnData.setCartSkuList(cartSkus);
        /**1.获取默认用户收货地址 判断是否包邮*/
        //获取商品ids
        Long[] productIds = cartSkus.stream().map(sku -> sku.getProductId()).toArray(Long[]::new);
        UmsMemberReceiveAddressResult addressResult = getAddressResult(productIds, memberId, addressId);
        //--------------设置运费参数
        returnData.setErrMsg(addressResult.getErrMsg());
        returnData.setDefaultAddress(addressResult.getDefaultAddress());
        returnData.setFreightPrice(addressResult.getFreightPrice());
        /**如果没有活动 的购买-->3.获取用户可用优惠券组合列表*/
        if (!isPromotions) {
            //--------------设置优惠券集合
            returnData.setCouponList(iSmsMemberCouponService.getJoinCoupon(cartSkus, totalOpt.get(), memberId));
        }
        //设置店铺信息
        Long shopId = cartSkus.get(0).getShopId();
        BizShopInfo bizShopInfo = iBizShopInfoService.selectBizShopInfoById(shopId);
        Checker.check(ObjectUtils.isEmpty(bizShopInfo), "商铺信息已过期！");
        returnData.setShopName(bizShopInfo.getCompanyName());
        returnData.setShopId(bizShopInfo.getId());
        return returnData;
    }

    /**
     * 获取地址结果
     *
     * @return
     */
    public UmsMemberReceiveAddressResult getAddressResult(Long[] productIds, Long memberId, Long addressId) {
        //声明地址返回参数
        UmsMemberReceiveAddressResult addressResult = new UmsMemberReceiveAddressResult();
        /**1.获取默认用户收货地址 判断是否包邮*/
        UmsMemberReceiveAddress defalutAddress = null;
        if (ObjectUtils.isEmpty(addressId)) {
            //地址为空则获取默认,默认可以为空
            defalutAddress = umsAddressService.getDefAddr(memberId);
        } else {
            //地址id不为空则,获取改地址.获取失败抛异常
            defalutAddress = iUmsMemberReceiveAddressService.selectUmsMemberReceiveAddressById(addressId);
            Checker.check(ObjectUtils.isEmpty(defalutAddress), "选择的收货地址异常");
        }
        //错误消息到Map
        Map<String, String> errMsgMap = new HashMap<>();
        //初始化空的不配送商品ids到msg ,若有其他错误消息这里初始化.防止后面调用时空指针错误！！！
        errMsgMap.put(UmsAddressConst.NO_SEND,StringUtils.EMPTY);
        //若存在默认收货地址
        if (!ObjectUtils.isEmpty(defalutAddress)) {
            //根据区域和模板id查询运费和
            String province = defalutAddress.getProvince();
            //模板id去重使用
            /*Set<Long> templateIds = new HashSet<>();
            *//**
             * 流处理步骤：
             * 1.根据商品的ids查询商品集合 获取 stream流
             * 2.根据商品查询所有运费模板 去重
             * 3.根据运费模板id 和 配送的省 获取 运费(-1不配送,此时需要设置不配送消息到errMsgMap中)
             * 4.返回 运费的optional
             *//*
            Optional<BigDecimal> amountFeightPrice = iPmsProductService.selectPmsProductByIds(productIds).stream()
                    .map(p -> {
                        Long templateId = p.getFeightTemplateId();
                        BigDecimal price = iPmsFeightTemplateService.getFeightFeeByRegion(templateId, province);
                        if (BigDecimal.ONE.negate().equals(price)) {
                            //设置不配送地区的商品ids
                            errMsgMap.merge(UmsAddressConst.NO_SEND, String.valueOf(p.getId()), (oldId, newId) -> oldId + "," + newId);
                            price = BigDecimal.ZERO;
                        }
                        //去重过滤返回不重复的模板价格 如果不需要去重则直接返回 price即可
                        if (!templateIds.contains(templateId)) {
                            templateIds.add(templateId);
                            return price;
                        } else {
                            return BigDecimal.ZERO;
                        }
                    })
                    //合计最终价格
                    .reduce((amountPrice, nextPrice) -> amountPrice.add(nextPrice));
            //检查返回值不存在则返回异常：除非商品没查到或商品信息不完整否则不会不存在
            Checker.checkOp(amountFeightPrice.isPresent(), "商品信息已过期");*/
            addressResult.setDefaultAddress(defalutAddress);

            addressResult.setFreightPrice(BigDecimal.ZERO);
           // addressResult.setFreightPrice(amountFeightPrice.get());
            addressResult.setErrMsg(errMsgMap);
        } else {
            addressResult.setDefaultAddress(null);
            addressResult.setFreightPrice(BigDecimal.ZERO);
            addressResult.setErrMsg(errMsgMap);
        }
        return addressResult;
    }

    /**
     * 修改地址
     *
     * @param param
     * @return shopId : result
     */
    @Override
    public HashMap<String, Object> changeAddress(AddressParam param) {
        Long memberId = param.getMemberId();
        //根据购物车获取相应到店铺对应商品的数据
        List<ShopOfProductDatas> data = iOmsCartItemService.getShopOfProudctsData(param.getCartIds(), memberId);
        Long addressId = param.getMemberReceiveAddressId();
        //返回Map对象: key->店铺id  value->运费总和
        HashMap<String, Object> result = new HashMap<>();
        //返回的不配送地区id信息(所有店铺)
        Map<String, String> errMsg = new HashMap<>();
        data.forEach(x -> {
            UmsMemberReceiveAddressResult addressResult = getAddressResult(Convert.toLongArray(x.getPids()), memberId, addressId);
            //获取不配送地区商品id
            String noSend = addressResult.getErrMsg().get(UmsAddressConst.NO_SEND);
            if (!StringUtils.isEmpty(noSend)) {
                //若存在不配送地区商品id,则合并不配送地区中间用逗号分隔(所有店铺)
                errMsg.merge(UmsAddressConst.NO_SEND, noSend, (oldId, newId) -> oldId + "," + newId);
            }
            result.put(x.getShopId(), addressResult.getFreightPrice());
        });
        //将不配送地区合并到店铺运费到Map中一同返回
        result.putAll(errMsg);
        return result;
    }
}
