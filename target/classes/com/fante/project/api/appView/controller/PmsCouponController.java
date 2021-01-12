package com.fante.project.api.appView.controller;

import com.fante.common.business.enums.SmsCouponConst;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.MemberLevelRsp;
import com.fante.project.api.appView.domain.SmsMemberCouponDetail;
import com.fante.project.api.appView.service.PmsCouponService;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.txmap.domain.GeocoderRsp;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:54
 * @Author: wz
 * @Description: 优惠券
 */
@Api(tags = "PmsCouponController", description = "优惠券")
@Controller
@RequestMapping("/api/coupon")
public class PmsCouponController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsCouponController.class);

    @Autowired
    PmsCouponService pmsCouponService;
    @Autowired
    ISmsCouponService iSmsCouponService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UmsMemberProcessService umsMemberProcessService;
    /**
     * 获取用户类型接口
     */
    private final String url = "http://www.henangaiyin.com/sunshinecredit/RealNameCertification/getJudgeOcr";

    @ApiOperation("获取优惠券类型")
    @PostMapping("/couponUseTypes")
    @ResponseBody
    public AjaxResult couponUseTypes() {
        return AjaxResult.success().put("couponUseTypes", SmsCouponConst.useTypeEnum.toMap());
    }

    @ApiOperation("根据商品id查询商品的可用优惠券")
    @PostMapping("/product")
    @ResponseBody
    public AjaxResult usableCouponsOfProduct(Long productId) {
        String openId = getTokenClientId();
        log.info("解析用户等级:openId={}",openId);
        String url_1 = url +"?openid="+openId;
        MemberLevelRsp rsp = restTemplate.getForObject(url_1, MemberLevelRsp.class);
        log.info("状态码: {}, 状态说明: {},等级标识{}", rsp.getStatus(), rsp.getMsg(),rsp.getData());
        UmsMember member = umsMemberProcessService.get(getTokenClientId());
        List<SmsCoupon> list = pmsCouponService.getUsableCouponsByproductId(productId, member.getId());
        //未认证则过滤掉专用优惠券
        if(StringUtils.equals(rsp.getData(), UmsMemberConst.MemberLevel.NOT.getType())){
            list = list.stream().filter(item-> StringUtils.equals(item.getMemberLevel(),SmsCouponConst.MemberLevelEnum.COMMON.getType())).collect(Collectors.toList());
        }
        return AjaxResult.success().put("couponList", list);
    }

    @ApiOperation("根据店铺id查询商品的可用优惠券")
    @PostMapping("/shop")
    @ResponseBody
    public AjaxResult usableCouponsOfShop(Long shopId) {
        String openId = getTokenClientId();
        log.info("解析用户等级:openId={}",openId);
        String url_1 = url +"?openid="+openId;
        MemberLevelRsp rsp = restTemplate.getForObject(url_1, MemberLevelRsp.class);
        log.info("状态码: {}, 状态说明: {},等级标识{}", rsp.getStatus(), rsp.getMsg(),rsp.getData());
        startPage();
        List<SmsCoupon> list = pmsCouponService.getUsableCouponsByShopId(shopId, getTokenUserId());
        boolean canLoad = new PageInfo(list).isHasNextPage();
        //未认证则过滤掉专用优惠券
        if(StringUtils.equals(rsp.getData(), UmsMemberConst.MemberLevel.NOT.getType())){
            list = list.stream().filter(item-> StringUtils.equals(item.getMemberLevel(),SmsCouponConst.MemberLevelEnum.COMMON.getType())).collect(Collectors.toList());
        }
        return AjaxResult.success()
                .put("couponList", list)
                .put("canLoad", canLoad);
    }

    @ApiOperation("根据优惠券id查询优惠券详情")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult couponDetail(Long couponId) {
        return AjaxResult.success().put("detail", pmsCouponService.detail(couponId));
    }

    @ApiOperation("用户领取优惠券")
    @PostMapping("/give")
    @ResponseBody
    public AjaxResult giveCoupon(Long couponId) {
        //查询并验证优惠券是否可用
        SmsCoupon smsCoupon = iSmsCouponService.getUsableCouponsById(couponId,null);
        Checker.check(ObjectUtils.isEmpty(smsCoupon),"优惠券已过期");
        return toAjax(pmsCouponService.giveCoupon(smsCoupon, getTokenUserId()));
    }

    @ApiOperation("用户领取的优惠券")
    @PostMapping("/member")
    @ResponseBody
    public AjaxResult memberCouponList(String useStatus) {
        startPage();
        List<SmsMemberCouponDetail> list = pmsCouponService.memberCouponList(getTokenUserId(),useStatus);
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("couponList", list)
                .put("canLoad", canLoad);
    }

    @ApiOperation("核销优惠券")
    @PostMapping("/cancelCoupon")
    @ResponseBody
    public AjaxResult cancelCoupon(String code) {
        long memberId = getTokenUserId();
        return toAjax( pmsCouponService.cancelCoupon(memberId,code));
    }
    
    @ApiOperation("查询优惠券是否使用")
    @PostMapping("/queryCouponUseStatus")
    @ResponseBody
    public AjaxResult queryCouponUseStatusByCode(String code){
        return AjaxResult.success(pmsCouponService.queryCouponUseStatusByCode(code));
    }
}
