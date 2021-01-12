package com.fante.project.business.homeStatistics.service;

import com.fante.common.utils.LocalDateUtil;
import com.fante.project.business.homeStatistics.domain.ChartDto;
import com.fante.project.business.homeStatistics.domain.ChartOfOrderDto;
import com.fante.project.business.homeStatistics.domain.HomeData;
import com.fante.project.business.homeStatistics.domain.SomeDaysOrderInfo;
import com.fante.project.business.homeStatistics.mapper.BizHomeDataMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by ftnet on 2020/5/11
 */
@Service
public class IhomeService {
    /**
     * 主页数据查询接口
     */
    @Autowired
    BizHomeDataMapper bizHomeDataMapper;
    /**
     * 获取首页数据
     * @param shopId
     * @return
     */
    public HomeData getHomeData(Long shopId) {
        return bizHomeDataMapper.getHomeData(shopId);
    }
    /**
     * 获取 条形图 所需数据
     * @param param
     * @return
     */
    public ChartDto getEchartData(SomeDaysOrderInfo param) {
        String startDate = param.getStartTime();
        String endDate = param.getEndTime();
        //如果为空则获取当前月开始时间和下个月开始时间
        if(StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime zero = LocalDateUtil.dateTimeZero(now);
            startDate =  LocalDateUtil.localDateTimeFormat(zero.withDayOfMonth(1), LocalDateUtil.DatePattern.yMd1.pattern());
            endDate = LocalDateUtil.localDateTimeFormat(now, LocalDateUtil.DatePattern.yMd1.pattern());
        }
        // X轴数据
        List<String> xAxis = getTimeList(startDate, endDate);
        startDate +=" 00:00:00";
        endDate +=" 23:59:59";
        // 通过开始/结束时间，数据库查询数据
        //List<ChartOfOrderDto> datas = bizHomeDataMapper.getEveryDayOrderCountOfMonth(param.getShopId(),startDate,endDate);
        //查询每日时间优惠券使用数量
        List<ChartOfOrderDto> datas = bizHomeDataMapper.getEveryDayCouponCount(param.getShopId(),startDate,endDate);
        //订单最大数量
        int maxOrderCount = datas.stream().map(x -> Integer.valueOf(x.getNum())).max(Integer::compareTo).orElse(100);
        //最大订单金额
        //BigDecimal maxOrderMoney = datas.stream().map(x->new BigDecimal(x.getTotal())).max(BigDecimal::compareTo).orElse(new BigDecimal(1000));
        //显示优惠券个数 数据 = 0
        BigDecimal maxOrderMoney = BigDecimal.ZERO;
        //将ChartOfOrderDto对象 转换为Map对象
        Map<String, ChartOfOrderDto> map  = datas.stream().collect(Collectors.toMap(ChartOfOrderDto::getDate, Function.identity()));
        // Y轴数据
        Map<String, List<String>> series = Maps.newHashMap();
        // 总额数据
        //List<String> totals = Lists.newArrayList();
        // 总量数据
        List<String> nums = Lists.newArrayList();

        // 组装数据
        for (String date : xAxis) {
            ChartOfOrderDto d = map.get(date);
            if (ObjectUtils.isEmpty(d)) {
                //totals.add("0");
                nums.add("0");
            } else {
                //totals.add(d.getTotal());
                nums.add(d.getNum());
            }
        }

        //series.put("totals", totals);
        series.put("nums", nums);

        return new ChartDto(xAxis, series,maxOrderCount,maxOrderMoney);
    }

    /**
     * 获取时间段内的订单数量 成交总额 发放佣金
     * @param param
     * @return
     */
    public SomeDaysOrderInfo getSomeDayOrderInfo(SomeDaysOrderInfo param) {
        //若时间错误则获取当天时间
        if( !isValidDate(param.getStartTime(), LocalDateUtil.DatePattern.yMd1.pattern()) ||
                !isValidDate(param.getEndTime(), LocalDateUtil.DatePattern.yMd1.pattern())){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime zero = LocalDateUtil.dateTimeZero(now);
            String startDate =  LocalDateUtil.localDateTimeFormat(zero.withDayOfMonth(1), LocalDateUtil.DatePattern.yMd1.pattern());
            String endDate = LocalDateUtil.localDateTimeFormat(now, LocalDateUtil.DatePattern.yMd1.pattern());
            startDate +=" 00:00:00";
            endDate +=" 23:59:59";
            param.setStartTime(startDate);
            param.setEndTime(endDate);
        }
        //获取一段时间内订单数量
        int someDayOrderCount = bizHomeDataMapper.getSomeDayOrderCount(param);
        //获取一段时间内订单交易额
        BigDecimal someDayOrderMoney = bizHomeDataMapper.getSomeDayOrderMoney(param);
        //获取一段时间内用户佣金发放总额
        BigDecimal someDayMemberCash = bizHomeDataMapper.getSomeDayMemberCash(param);
        //获取一段时间内优惠券使用量
        int someDayCouponCount = bizHomeDataMapper.getSomeDayCouponCount(param);

        return new SomeDaysOrderInfo(someDayOrderCount,someDayOrderMoney,someDayMemberCash,someDayCouponCount);
    }

    /**
     * 判断日期是否是指定格式
     * @param str
     * @param pattern
     * @return
     */
    private static boolean isValidDate(String str,String pattern) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }


    /**
     * 将ChartOfOrderDto对象 转换为Map对象
     * @param datas ChartOfOrderDto对象
     * @return Map对象
     */
    public Map<String, ChartOfOrderDto> formatChartOfOrderDto(List<ChartOfOrderDto> datas) {
        Map<String, ChartOfOrderDto> map = Maps.newHashMap();
        datas.forEach(data -> {
            map.put(data.getDate(), data);
        });
        return map;
    }

    /**
     * 获取 从 startDate 到 endDate的所有日期 并自动匹配格式
     * 日期格式只能是: yyyy-MM-dd  |  yyyy-MM  |  yyyy
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @example getTimeList("2018", "2020") ->[2018, 2019, 2020]
     *          getTimeList("2018-10", "2019-02") ->[2018-10, 2018-11, 2018-12, 2019-01, 2019-02]
     *          getTimeList("2020-05-01", "2020-05-08") ->[2020-05-28, 2020-05-29, 2020-05-30, 2020-05-31, 2020-06-01, 2020-06-02]
     */
    public List<String> getTimeList(String startDate, String endDate){
        SimpleDateFormat sdf ;
        int calendarType;

        switch (startDate.length()){
            case 10:
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                calendarType = Calendar.DATE;
                break;
            case 7:
                sdf = new SimpleDateFormat("yyyy-MM");
                calendarType = Calendar.MONTH;
                break;
            case 4:
                sdf = new SimpleDateFormat("yyyy");
                calendarType = Calendar.YEAR;
                break;
            default:
                return null;
        }

        List<String> result = new ArrayList<>();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        try {
            min.setTime(sdf.parse(startDate));
            min.add(calendarType, 0);
            max.setTime(sdf.parse(endDate));
            max.add(calendarType, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(min.getTime()));
            curr.add(calendarType, 1);
        }
        return result;
    }

}
