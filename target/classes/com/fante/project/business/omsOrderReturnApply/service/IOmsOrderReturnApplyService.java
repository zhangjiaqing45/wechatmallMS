package com.fante.project.business.omsOrderReturnApply.service;

import com.fante.project.business.omsOrderReturnApply.domain.OmsOrderReturnApply;
import com.fante.project.business.omsOrderReturnApply.domain.OrderReturnOperationParam;
import com.fante.project.weixin.core.domain.OrderRefundRsp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单退货申请Service接口
 *
 * @author fante
 * @date 2020-04-09
 */
public interface IOmsOrderReturnApplyService {
    /**
     * 查询订单退货申请
     *
     * @param id 订单退货申请ID
     * @return 订单退货申请
     */
    public OmsOrderReturnApply selectOmsOrderReturnApplyById(Long id);

    /**
     * 查询订单退货申请列表
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 订单退货申请集合
     */
    public List<OmsOrderReturnApply> selectOmsOrderReturnApplyList(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 查询订单退货申请数量
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    public int countOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 唯一校验
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    public String checkOmsOrderReturnApplyUnique(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 新增订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 新增订单退货申请数量
     */
    public int insertOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 修改订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 修改订单退货申请数量
     */
    public int updateOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 批量删除订单退货申请
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单退货申请数量
     */
    public int deleteOmsOrderReturnApplyByIds(String ids);

    /**
     * 删除订单退货申请信息
     *
     * @param id 订单退货申请ID
     * @return 删除订单退货申请数量
     */
    public int deleteOmsOrderReturnApplyById(Long id);


    /**
     * 确认退货
     * @param param
     * @return
     */
    public int confirmReturn(OrderReturnOperationParam param);
    /**
     * 拒绝退货
     * @param param
     * @return
     */
    public int refuseReturn(OrderReturnOperationParam param);
    /**
     * 确认收货
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int confirmReceive(OrderReturnOperationParam param);
}
