package com.fante.project.api.setting;

import com.fante.common.business.enums.OrderConst;
import com.fante.project.business.omsOrderSetting.domain.OmsOrderSetting;
import com.fante.project.business.omsOrderSetting.service.IOmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author ftnet
 * @Description OrderStting
 * @CreatTime 2020/4/15
 */
@Service
public class OrderStting {

    @Autowired
    IOmsOrderSettingService omsOrderSettingService;

    private static final OmsOrderSetting EMPTY = new OmsOrderSetting();
    /**
     * 默认自动取消订单时间(单位: 分钟)
     */
    private static final long DEF_CANCEL_ORDER_TIME = 30L;
    /**
     * 默认自动收货时间(单位: 天)
     */
    private static final long DEF_CONFIRM_RECEIVE_DAY = 7L;
    /**
     * 默认自动关闭订单时间(单位: 天)//改分钟
     */
    private static final long DEF_CLOSE_ORDER_DAY = 10L;
    /**
     * 默认佣金有效期(单位: 天)
     */
    private static final long DEF_COMMISSION_AGING = 30L;


    /**
     * @return 自动收货时间(单位 : 天) <br/>
     * 范围（3 ~ 7）
     */
    public Long getAutoConfirmDay() {
        return nvl(getSetting().getAutoConfirmReceive(), DEF_CONFIRM_RECEIVE_DAY);
    }

    /**
     * @return 自动取消订单时间(单位 : 分钟) <br/>
     * 范围（10 ~ 30）
     */
    public Long getAutoCancelOrderTime(OrderConst.OrderType type) {
        Long time = 0L;
        switch (type) {
            case GENERAL:
                time = nvl(getSetting().getAutoCancelGeneralOrder(), DEF_CANCEL_ORDER_TIME);
                break;
            case GROUP:
                time = nvl(getSetting().getAutoCancelGroupOrder(), DEF_CANCEL_ORDER_TIME);
                break;
            case FLASH:
                time = nvl(getSetting().getAutoCancelFlashOrder(), DEF_CANCEL_ORDER_TIME);
                break;
            case INTEGRAL:
                time = nvl(getSetting().getAutoCancelIntegralOrder(), DEF_CANCEL_ORDER_TIME);
                break;
            default:
                ;
        }
        //获取订单超时时间
        return time;
    }

    /**
     * 获取上下级关系的时效 <br/>
     * 单位(天) <br/>
     * 范围（0 ~ 360）<br/>
     */
    public Long getCommissionAging() {
        return nvl(getSetting().getCommissionAging(), DEF_COMMISSION_AGING);
    }
    /**
     * 获取自动关闭订单的时间 <br/>
     * 单位(天) <br/>
     * 范围（3 ~ 7）<br/>
     */
    public Long getAutoCloseOrderTime() {
        return nvl(getSetting().getAutoCloseOrder(), DEF_CLOSE_ORDER_DAY);
    }


    /**
     * 获取设置信息
     * @return
     */
    private OmsOrderSetting getSetting() {
        OmsOrderSetting setting = omsOrderSettingService.selectOmsOrderSettingRecent();
        return ObjectUtils.isEmpty(setting) ? EMPTY : setting;
    }

    /**
     * 设置默认值
     * @param val
     * @param defVal
     * @return
     */
    private Long nvl(Long val, Long defVal) {
        return ObjectUtils.isEmpty(val) ? defVal : val;
    }
}
