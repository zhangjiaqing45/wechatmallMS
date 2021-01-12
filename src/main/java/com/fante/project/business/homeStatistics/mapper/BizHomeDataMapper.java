package com.fante.project.business.homeStatistics.mapper;

import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.business.cmsTopic.domain.CmsTopicDTO;
import com.fante.project.business.homeStatistics.domain.ChartDto;
import com.fante.project.business.homeStatistics.domain.ChartOfOrderDto;
import com.fante.project.business.homeStatistics.domain.HomeData;
import com.fante.project.business.homeStatistics.domain.SomeDaysOrderInfo;
import com.fante.project.mapperBase.BizShopInfoMapperBase;
import com.fante.project.mapperBase.CmsTopicMapperBase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 主页数据查询接口
 *
 * @author wz
 * @date 2020-03-18
 */

public interface BizHomeDataMapper extends BizShopInfoMapperBase {
    /**
     * 查询主页显示的数据
     * @param shopId 店铺id
     * @return
     */
    public HomeData getHomeData(@Param( "shopId" ) Long shopId);
    /**
     * 获取某月每天日期，成交总额，总数量
     * @param shopId 店铺id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 图表数据
     */
    public List<ChartOfOrderDto> getEveryDayOrderCountOfMonth(@Param("shopId") Long shopId
                                                                , @Param("startTime") String startTime
                                                                , @Param("endTime") String endTime);

    /**
     * 查询每日时间优惠券使用数量
     * @param shopId 店铺id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 图表数据
     */
    List<ChartOfOrderDto> getEveryDayCouponCount(@Param("shopId") Long shopId
                                                    , @Param("startTime") String startTime
                                                    , @Param("endTime") String endTime);
    /**
     *获取一段时间内订单数量
     * @param param
     * @return
     */
    public int getSomeDayOrderCount(SomeDaysOrderInfo param);
    /**
     *获取一段时间内订单交易额
     * @param param
     * @return
     */
    public BigDecimal getSomeDayOrderMoney(SomeDaysOrderInfo param);
    /**
     *获取一段时间内用户佣金发放总额
     * @param param
     * @return
     */
    public BigDecimal getSomeDayMemberCash(SomeDaysOrderInfo param);

    /**
     * 获取一段时间内优惠券使用量
     * @param param
     * @return
     */
    int getSomeDayCouponCount(SomeDaysOrderInfo param);
}
