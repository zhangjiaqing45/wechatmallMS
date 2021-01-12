package com.fante.project.api.omsOrderProcess.service;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.ProductReturnConst;
import com.fante.common.utils.Arith;
import com.fante.common.utils.Checker;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.api.omsOrderProcess.domain.OmsOrderReturnApplyParam;
import com.fante.project.api.omsOrderProcess.domain.SmsCouponHistoryDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderItem.service.IOmsOrderItemService;
import com.fante.project.business.omsOrderReturnApply.domain.OmsOrderReturnApply;
import com.fante.project.business.omsOrderReturnApply.service.IOmsOrderReturnApplyService;
import com.fante.project.business.omsOrderReturnReason.domain.OmsOrderReturnReason;
import com.fante.project.business.omsOrderReturnReason.service.IOmsOrderReturnReasonService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ISmsOrderReturnService {
    /**
     * 退货申请服务
     */
    @Autowired
    IOmsOrderReturnApplyService iOmsOrderReturnApplyService;
    /**
     * 退货原因
     */
    @Autowired
    IOmsOrderReturnReasonService iOmsOrderReturnReasonService;
    /**
     * 订单详情服务
     */
    @Autowired
    IOmsOrderItemService iOmsOrderItemService;

    public List<OmsOrderReturnApply> getMemberReturnApply(Long memberId) {
        OmsOrderReturnApply apply = new OmsOrderReturnApply();
        apply.setMemberId(memberId);
        return iOmsOrderReturnApplyService.selectOmsOrderReturnApplyList(apply);
    }

    /**
     * 获取订单退货原因
     *
     * @param shopId
     * @return
     */
    public List<OmsOrderReturnReason> getReturnReason(Long shopId) {
        OmsOrderReturnReason reason = new OmsOrderReturnReason();
        //店铺id
        reason.setShopId(shopId);
        //退货原因状态:启用
        reason.setStatus(CommonUse.Status.ENABLE.getType());
        return iOmsOrderReturnReasonService.selectOmsOrderReturnReasonList(reason);
    }

    /**
     * 申请退货
     *
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int applyForReturn(OmsOrderReturnApplyParam param) {
        //验证参数
        param.validateApplayParam();
        Long orderItemId = param.getOrderItemId();
        UmsMember member = param.getMember();
        //获取订单 和 订单详情
        OmsMemberOrderDetail detail = iOmsOrderItemService.getOrderDetailForReturnByOrderItemId(orderItemId, member.getId());
        Checker.check(ObjectUtils.isEmpty(detail), "订单已删除或不存在！");
        OmsOrderItem orderItem = detail.getItemList().get(0);
        //取出订单中的商品信息填充到退货订单中
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        //退货人姓名
        returnApply.setReturnName(param.getReturnName());
        //退货人电话
        returnApply.setReturnPhone(param.getReturnPhone());
        //店铺id
        returnApply.setShopId(detail.getShopId());
        //订单id
        returnApply.setOrderId(detail.getId());
        //商品规格id
        returnApply.setSkuId(orderItem.getProductSkuId());
        //订单详情id
        returnApply.setOrderItemId(orderItem.getId());
        //用户id
        returnApply.setMemberId(detail.getMemberId());
        //退货商品id
        returnApply.setProductId(orderItem.getProductId());
        //商品图片
        returnApply.setProductPic(orderItem.getProductPic());
        //商品货号
        returnApply.setProductSn(orderItem.getProductSn());
        //商品名称
        returnApply.setProductName(orderItem.getProductName());
        //商品品牌
        returnApply.setProductBrand(orderItem.getProductBrand());
        //商品规格
        returnApply.setSpData(orderItem.getProductSpData());
        //退货数量
        returnApply.setProductCount(param.getProductCount());
        //商品单价 不包含运费 不包含平均优惠券初始价格
        returnApply.setProductPrice(orderItem.getInitPrice());
        //商品实际支付单价 实际支付总价格 / 数量  包含运费的单价格
        returnApply.setProductRealPrice(Arith.div(orderItem.getPayPrice(),new BigDecimal(orderItem.getProductQuantity())));
        //申请状态：0->待处理；    1->退货中；2->已完成；3->已拒绝
        returnApply.setStatus(ProductReturnConst.ReturnStatus.PENDING.getType());
        //订单编号
        returnApply.setOrderSn(detail.getOrderSn());
        //用户昵称
        returnApply.setMemberNickName(member.getNickname() );
        //原因
        returnApply.setReason(param.getReason());
        //描述
        returnApply.setDescription(param.getDescription());
        //凭证图片，以逗号隔开
        returnApply.setProofPics(param.getProofPics());
        //用户账户名
        returnApply.setCreateBy(member.getUsername());
        //生成退货单号
        returnApply.setReturnApplySn(String.valueOf(IdGenerator.nextId()));
        //退款金额 审核通过后有商家确定
        //退货地址表id 审核通过后有商家确定
        //退货接受人 审核通过后有商家确定
        //退货接受电话 审核通过后有商家确定
        //退货接受区域 审核通过后有商家确定
        //退货接受地址 审核通过后有商家确定
        int i = iOmsOrderReturnApplyService.insertOmsOrderReturnApply(returnApply);
        //修改订单详情退货状态
        OmsOrderItem updateItem = new OmsOrderItem();
        updateItem.setId(orderItemId);
        updateItem.setReturnStatus(OrderConst.ReturnStatus.YES.getType());
        i += iOmsOrderItemService.updateOmsOrderItem(updateItem);
        Checker.check(i!=2,"申请失败！");
        return i;
    }

    /**
     * 更新退货信息
     * 更新 邮寄单号
     * @param param
     * @return
     */
    public int updateReturnApply(OmsOrderReturnApplyParam param) {
        //验证必要参数
        param.validateUpdateParam();
        //取出订单中的商品信息填充到退货订单中
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        returnApply.setId(param.getReturnApplyId());
        //退货服务单号
        returnApply.setId(param.getReturnApplyId());
        // 邮寄公司
        returnApply.setDeliveryCompany(param.getDeliveryCompany());
        //邮寄快递号
        returnApply.setDeliverySn(param.getDeliverySn());
        return iOmsOrderReturnApplyService.updateOmsOrderReturnApply(returnApply);
    }
}
