package com.fante.project.api.appView.service;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.project.api.appView.domain.PmsProductCommentParam;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderItem.service.IOmsOrderItemService;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.domain.PmsShopProductCategory;
import com.fante.project.business.pmsProductCategory.service.IPmsProductCategoryService;
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.business.pmsProductComment.service.IPmsProductCommentService;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.business.umsMemberShopStar.domain.UmsMemberShopStar;
import com.fante.project.business.umsMemberShopStar.service.IUmsMemberShopStarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:56
 * @Author: Mr.Z
 * @Description: 前端推荐商品相关处理服务
 */
@Service
public class PmsCommentService {

    private static Logger log = LoggerFactory.getLogger(PmsCommentService.class);
    /**
     * 商品评论
     */
    @Autowired
    private IPmsProductCommentService iPmsProductCommentService;
    /**
     * 用户表
     */
    @Autowired
    private UmsMemberProcessService umsMemberProcessService;
    /**
     * 订单详情
     */
    @Autowired
    private IOmsOrderItemService iOmsOrderItemService;
    /**
     * 订单
     */
    @Autowired
    private IOmsOrderService iOmsOrderService;
    /**
     * 获取商品评论
     * @param id
     * @return
     */
    public  List<PmsProductComment> product(Long id) {
        return iPmsProductCommentService.getCommentListForApp(id);
    }

    /**
     * 评论商品
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public  int add(PmsProductCommentParam param, String openId) {
        Long orderItemId = param.getOrderItemId();
        //获取商品评论信息通过 订单详情
        PmsProductComment info = iPmsProductCommentService.getCommentInfoByOrder(orderItemId);
        Checker.check(ObjectUtils.isEmpty(info),"订单信息异常！");
        UmsMember member = umsMemberProcessService.get(openId);
        Checker.check(ObjectUtils.isEmpty(member),"用户信息异常！");
        info.setMemberId(String.valueOf(member.getId()));
        info.setMemberNickName(member.getNickname());
        info.setMemberIcon(member.getHeadimgurl());
        //设置传入参数
        info.setContent(param.getContent());
        info.setPics(param.getPics());
        info.setStar(param.getStar());
        //插入评论信息
        int i = iPmsProductCommentService.insertPmsProductComment(info);
        //修改订单详情评论状态
        OmsOrderItem updateItem = new OmsOrderItem();
        updateItem.setId(orderItemId);
        updateItem.setStatus(OrderConst.CommentStatus.YES.getType());
        i+=iOmsOrderItemService.updateOmsOrderItem(updateItem);
        //更新订单评价时间
        i+=iOmsOrderService.updateOmsOrderCommentTime(orderItemId);
        Checker.check(i!=3,"评论失败");
        return i;
    }

}
