package com.fante.project.business.omsOrderReturnApply.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fante.common.business.enums.AccRecordConst;
import com.fante.common.business.enums.ProductReturnConst;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.Arith;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.framework.aspectj.lang.enums.OperatorType;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderItem.service.IOmsOrderItemService;
import com.fante.project.business.omsOrderReturnAddress.domain.OmsOrderReturnAddress;
import com.fante.project.business.omsOrderReturnAddress.service.IOmsOrderReturnAddressService;
import com.fante.project.business.omsOrderReturnApply.domain.OrderReturnOperationParam;
import com.fante.project.business.omsOrderSendCompany.domain.OmsOrderSendCompany;
import com.fante.project.business.omsOrderSendCompany.service.IOmsOrderSendCompanyService;
import com.fante.project.business.umsDistribution.service.IUmsDistributionService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.weixin.business.service.WechatOrderService;
import com.fante.project.weixin.core.domain.OrderRefundRsp;
import com.fante.project.weixin.core.service.impl.WeixinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.omsOrderReturnApply.mapper.OmsOrderReturnApplyMapper;
import com.fante.project.business.omsOrderReturnApply.domain.OmsOrderReturnApply;
import com.fante.project.business.omsOrderReturnApply.service.IOmsOrderReturnApplyService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 订单退货申请Service业务层处理
 *
 * @author fante
 * @date 2020-04-09
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements IOmsOrderReturnApplyService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderReturnApplyServiceImpl.class);
    /**
     * 订单退货申请Mapper扩展接口
     */
    @Autowired
    private OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;
    /**
     * 店铺收发货地址Service接口
     */
    @Autowired
    private IOmsOrderReturnAddressService iOmsOrderReturnAddressService;
    /**
     * 账户出入账记录Service接口
     */
    @Autowired
    private IAccAccountRecordService iAccAccountRecordService;
    /**
     * 用户佣金记录对象 acc_commission_record
     */
    @Autowired
    private IAccCommissionRecordService iAccCommissionRecordService;
    /**
     * 订单中所包含的商品Service接口
     */
    @Autowired
    private IOmsOrderItemService iOmsOrderItemService;
    /**
     * 会员Service接口
     */
    @Autowired
    private IUmsMemberService iUmsMemberService;
    /**
     * 微信支付退款-JSAPI
     */
    @Autowired
    private WechatOrderService wechatOrderService;
    /**
     * 分销比例商品角色关系Service接口
     */
    @Autowired
    private IUmsDistributionService iUmsDistributionService;
    /**
     * 店铺信息Service接口
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;

    /**
     * 查询订单退货申请
     *
     * @param id 订单退货申请ID
     * @return 订单退货申请
     */
    @Override
    public OmsOrderReturnApply selectOmsOrderReturnApplyById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsOrderReturnApplyMapper.selectOmsOrderReturnApplyById(id);
    }

    /**
     * 查询订单退货申请列表
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 订单退货申请集合
     */
    @Override
    public List<OmsOrderReturnApply> selectOmsOrderReturnApplyList(OmsOrderReturnApply omsOrderReturnApply) {
        return omsOrderReturnApplyMapper.getOmsOrderReturnApplyList(omsOrderReturnApply);
    }

    /**
     * 查询订单退货申请数量
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    @Override
    public int countOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply){
        return omsOrderReturnApplyMapper.countOmsOrderReturnApply(omsOrderReturnApply);
    }

    /**
     * 唯一校验
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    @Override
    public String checkOmsOrderReturnApplyUnique(OmsOrderReturnApply omsOrderReturnApply) {
        return omsOrderReturnApplyMapper.checkOmsOrderReturnApplyUnique(omsOrderReturnApply) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 新增订单退货申请数量
     */
    @Override
    public int insertOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply) {
        omsOrderReturnApply.setCreateTime(DateUtils.getNowDate());
        return omsOrderReturnApplyMapper.insertOmsOrderReturnApply(omsOrderReturnApply);
    }

    /**
     * 修改订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 修改订单退货申请数量
     */
    @Override
    public int updateOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply) {
        omsOrderReturnApply.setUpdateTime(DateUtils.getNowDate());
        return omsOrderReturnApplyMapper.updateOmsOrderReturnApply(omsOrderReturnApply);
    }

    /**
     * 删除订单退货申请对象
     * note: 只能删除拒绝退货的订单
     * @param ids 需要删除的数据ID
     * @return 删除订单退货申请数量
     */
    @Override
    public int deleteOmsOrderReturnApplyByIds(String ids) {
        int count = omsOrderReturnApplyMapper.realDelOmsOrderReturnApplyByIds(Convert.toStrArray(ids));
        Checker.checkOp(count>0,"只能删除拒绝退货的服务订单！");
        return omsOrderReturnApplyMapper.realDelOmsOrderReturnApplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单退货申请信息
     *
     * @param id 订单退货申请ID
     * @return 删除订单退货申请数量
     */
    @Override
    public int deleteOmsOrderReturnApplyById(Long id) {
        return omsOrderReturnApplyMapper.deleteOmsOrderReturnApplyById(id);
    }

    /**
     * 确认退货
     * @param param
     * @return
     */
    @Override
    public int confirmReturn(OrderReturnOperationParam param) {
        OmsOrderReturnApply update = ValidateParam(param, ProductReturnConst.ReturnAction.PASS);
        return omsOrderReturnApplyMapper.updateOmsOrderReturnApply(update);
    }
    /**
     * 拒绝退货
     * @param param
     * @return
     */
    @Override
    public int refuseReturn(OrderReturnOperationParam param) {
        OmsOrderReturnApply update = ValidateParam(param, ProductReturnConst.ReturnAction.REFUSE);
        return omsOrderReturnApplyMapper.updateOmsOrderReturnApply(update);
    }
    /**
     * 确认收货
     * @param param
     * @return
     */
    @Override
    public int confirmReceive(OrderReturnOperationParam param) {
        OmsOrderReturnApply update = ValidateParam(param, ProductReturnConst.ReturnAction.RECEIVE);
        int count = omsOrderReturnApplyMapper.updateOmsOrderReturnApply(update);

        // 1.账户记录 2条  2.佣金记录1条  3.用户表修改1条 4.店铺账户表修改1条
        //插入全金额到店铺账户记录
        OmsOrderReturnApply apply = selectOmsOrderReturnApplyById(param.getId());
        Long orderId = apply.getOrderId();
        Long shopId = apply.getShopId();
        UmsMember parent = iUmsMemberService.selectUmsMemberParentById(apply.getMemberId());

        BigDecimal singleCommission = BigDecimal.ZERO;
        //查询佣金记录中的佣金
        AccCommissionRecord recSelect = new AccCommissionRecord();
        recSelect.setOrderId(orderId);
        //待入佣金
        recSelect.setStatus(AccRecordConst.CommissionStatus.COMMISSION.getType());
        //获得佣金
        recSelect.setOperation(AccRecordConst.CommissionOperation.GET.getType());
        List<AccCommissionRecord> olds = iAccCommissionRecordService.selectAccCommissionRecordList(recSelect);
        if(!ObjectUtils.isEmpty(olds)){
            AccCommissionRecord record = olds.get(0);
            HashMap<String,BigDecimal> map = JSON.parseObject(record.getRemark(), HashMap.class);
            singleCommission = map.get(String.valueOf(apply.getOrderItemId()));
        }
        //退货商品总佣金 = 退货商品佣金 * 退货商品数量
        BigDecimal commission = Arith.mul(new BigDecimal(apply.getProductCount()),
                singleCommission);

        //退货订单金额 = 退货总金额 - 退货商品总佣金
        BigDecimal returnMoney = Arith.sub(apply.getReturnAmount(), commission);
        // 操作:0佣金收入 1订单金额入账2退货订单出账 3退货佣金出账4店铺提现出账

        //根据订单号 和 操作=0/1 查询 商品流水记录
        AccAccountRecord orderInRecord = iAccAccountRecordService.getRecordByOrderIdAndOperation(orderId,AccRecordConst.AccountOperation.ORDER_IN.getType());
        //若不为空则插入一条商品退货支出记录
        if(!ObjectUtils.isEmpty(orderInRecord)){
            orderInRecord.setMoney(returnMoney.negate());
            orderInRecord.setApplyId(apply.getId());
            iAccAccountRecordService.subAccountRecordOfProduct(orderInRecord);
            //修改店铺账户
            iBizShopInfoService.subCashToAccount(shopId,returnMoney);
        }

        //订单佣金入账记录
        AccAccountRecord commissionInRecord = iAccAccountRecordService.getRecordByOrderIdAndOperation(orderId,AccRecordConst.AccountOperation.COMMISSION_IN.getType());
        //若不为空则插入一条退货佣金出账(店铺佣金账户)
        if(!ObjectUtils.isEmpty(commissionInRecord)){
            orderInRecord.setMoney(commission.negate());
            commissionInRecord.setApplyId(apply.getId());
            iAccAccountRecordService.subAccountRecordOfCommission(commissionInRecord);
            //修改店铺佣金账户
            iBizShopInfoService.subCommissionToAccount(shopId,commission);
        }

        //查询佣金记录(用户佣金记录表)
        AccCommissionRecord commissionRecord = iAccCommissionRecordService.getCommissionRecordByMemberIdAndOperation(orderId, AccRecordConst.CommissionOperation.GET.getType());
        if(!ObjectUtils.isEmpty(commissionRecord)){
            commissionRecord.setMoney(commission.negate());
            commissionRecord.setApplyId(apply.getId());
            iAccCommissionRecordService.subtrackBrokerage(commissionRecord);
            //修改待入佣金
            iUmsMemberService.subWaitCommission(parent.getId(),commission);
        }
        //退钱接口
        OrderRefundRsp rsp = wechatOrderService.wxRefundOrder(apply);
        Checker.checkOp(rsp.getStatus(),rsp.getMsg());
        return  count;
    }

    /**
     * 验证参数
     * 1.判断id是否存在
     * 2.根据动作判断当前状态是否可执行
     * 3.验证是否是本店铺人员操作
     */
    private OmsOrderReturnApply ValidateParam(OrderReturnOperationParam param, ProductReturnConst.ReturnAction action){
        OmsOrderReturnApply data = new OmsOrderReturnApply();
        //通用参数
        Date now = DateUtils.getNowDate();
        String loginName = ShiroUtils.getLoginName();
        //查询数据库中的原有退货单
        OmsOrderReturnApply omsOrderReturnApply = omsOrderReturnApplyMapper.selectOmsOrderReturnApplyById(param.getId());
        Checker.check(ObjectUtils.isEmpty(omsOrderReturnApply),"选择的退货订单不存在！");
        //验证操作人员是否本店铺人员
        Checker.checkOp(Objects.equals(ShiroUtils.getSysUser().getDeptId(),omsOrderReturnApply.getShopId()),"非本店铺的退货单,不可操作！");
        //设置id
        data.setId(omsOrderReturnApply.getId());
        //根据状态判断是否可操作
        String status = omsOrderReturnApply.getStatus();
        ProductReturnConst.ReturnStatus returnStatus = ProductReturnConst.ReturnStatus.get(status);
        boolean exist = ProductReturnConst.ReturnAction
                .getBtns(status)
                .stream()
                .anyMatch(act -> StringUtils.equals(act, action.getType()));
        Checker.checkOp(exist,StringUtils.format("此服务单{},不能执行{}操作！", returnStatus.getDescribe(),action.getDescribe()));
        //验证其他参数
        switch (action){
            //同意退货
            case PASS:
                Checker.check(ObjectUtils.isEmpty(param.getReturnAmount()),"退款金额参数缺失！");
                Checker.check(ObjectUtils.isEmpty(param.getRemark()),"备注不能为空！");
                OmsOrderReturnAddress addr = iOmsOrderReturnAddressService.selectOmsOrderReturnAddressById(param.getCompanyAddressId());
                Checker.check(ObjectUtils.isEmpty(addr),"选择的退货地址不存在！");
                //设置状态:退货中 退款金额 操作人 操作备注 操作时间
                data.setStatus(ProductReturnConst.ReturnStatus.RETURNING.getType());
                data.setReturnAmount(param.getReturnAmount());
                data.setHandleMan(loginName);
                data.setHandleNote(param.getRemark());
                data.setHandleTime(now);
                //设置退货地址信息
                data.setCompanyAddressId(addr.getId());
                data.setReceiveName(addr.getPersionName());
                data.setReceivePhone(addr.getPersionPhone());
                data.setReceiveArea(addr.getProvince()+" "+addr.getCity()+" "+addr.getRegion());
                data.setReceiveAddr(addr.getDetailAddress());
                break;
            //拒绝退货
            case REFUSE:
                Checker.check(ObjectUtils.isEmpty(param.getRemark()),"备注不能为空！");
                //设置状态:已拒绝 操作人 操作备注 操作时间
                data.setStatus(ProductReturnConst.ReturnStatus.REFUSE.getType());
                data.setHandleMan(loginName);
                data.setHandleNote(param.getRemark());
                data.setHandleTime(now);
                break;
            //收到退货
            case RECEIVE:
                Checker.check(ObjectUtils.isEmpty(param.getRemark()),"备注不能为空！");
                //设置状态:已完成 收货人信息 收货备注 收货时间
                data.setStatus(ProductReturnConst.ReturnStatus.COMPLETE.getType());
                data.setReceiveMan(loginName);
                data.setReceiveNote(param.getRemark());
                data.setReceiveTime(now);
                break;
            default:
                break;
        }
        return data;
    }
}
