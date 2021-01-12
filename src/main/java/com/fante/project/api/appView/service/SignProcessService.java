package com.fante.project.api.appView.service;

import com.fante.common.business.enums.PmsIntegralConst;
import com.fante.common.business.enums.SmsSignConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.LocalDateUtil;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import com.fante.project.business.pmsIntegralLog.service.IPmsIntegralLogService;
import com.fante.project.api.appView.domain.SignInfoRsp;
import com.fante.project.api.appView.domain.SignRsp;
import com.fante.project.business.smsHomeSignRecord.domain.SmsHomeSignRecord;
import com.fante.project.business.smsHomeSignRecord.service.ISmsHomeSignRecordService;
import com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward;
import com.fante.project.business.smsHomeSignReward.service.ISmsHomeSignRewardService;
import com.fante.project.business.smsHomeSignSetting.domain.SmsHomeSignSetting;
import com.fante.project.business.smsHomeSignSetting.service.ISmsHomeSignSettingService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName SignProcessService
 * @Description 签到流程处理
 * @Author LiuQingyu
 * @Date 2020-03-12 10:25
 * @Version 1.0
 **/
@Service
public class SignProcessService {

    private static Logger log = LoggerFactory.getLogger(SignProcessService.class);
    /**
     * 签到记录Service接口
     */
    @Autowired
    private ISmsHomeSignRecordService recordService;
    /**
     * 签到奖励记录Service接口
     */
    @Autowired
    private ISmsHomeSignRewardService rewardService;
    /**
     * 签到奖励设置Service接口
     */
    @Autowired
    private ISmsHomeSignSettingService settingService;
    /**
     * 会员Service接口
     */
    @Autowired
    private IUmsMemberService umsMemberService;
    /**
     * 会员redis接口
     */
    @Autowired
    private UmsMemberProcessService umsMemberProcessService;
    /**
     * 积分记录servie接口
     */
    @Autowired
    private IPmsIntegralLogService iPmsIntegralLogService;

    /**
     * 毫秒数（1天）
     */
    private static final long MINUS_OF_DAY = 86400000L;
    /**
     * 每日签到默认次数
     */
    private static final int DAILY_TIMES = 1;

    /**
     * @return com.fante.project.business.smsBizProcess.domain.SignInfoRsp
     * @Author LiuQingyu
     * @Description 用户签到信息
     * 返回结果{
     * 1.累计签到天数
     * 2.连续签到天数
     * 3.当月已经签到的日期集合（只返回日期）
     * 4.用户积分余额
     * }
     * @Date 11:21 2020-03-12
     * @Param [userId]
     **/
    public SignInfoRsp signInfo(String openId) {
        //从缓存中拿用户数据
        UmsMember member = umsMemberProcessService.get(openId);
        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        //当月第一天的日期
        LocalDateTime zero = LocalDateUtil.dateTimeZero(now);
        LocalDateTime beginDate = zero.withDayOfMonth(1);
        // 获取用户签到记录（倒序排列）
        List<SmsHomeSignRecord> signRecordList = signRecordList(member.getId(), beginDate, now);

        //累计签到
        int cumulative = signRecordList.size();

        //连续签到
        int continuous = getContinuousSignInDay(signRecordList, now);
        log.info("{}月累计签到：{}次；连续签到：{}次", now.getMonthValue(), cumulative, continuous);

        List<String> beforeSignDates = beforeSignDates(signRecordList);
        log.info("{}月已经签到的日期：{}", now.getMonthValue(), beforeSignDates.toString());
        String nowDay = LocalDateUtil.dateFormat(new Date(), "d");

        //用户积分
        log.info("用户当前积分为{}", member.getIntegration());
        return new SignInfoRsp(continuous, cumulative, beforeSignDates, member.getIntegration(),beforeSignDates.contains(nowDay));
    }

    /**
     * @return com.fante.project.business.smsBizProcess.domain.SignRsp
     * @Author LiuQingyu
     * @Description 签到操作处理
     * 处理过程：{
     * 1.检查当天是否已经签过
     * 2.创建签到记录
     * 3.获取用户签到记录
     * 4.获取累计/连续签到天数
     * 5.计算奖励
     * 5.1每日签到奖励
     * 5.2累计签到奖励
     * 5.3连续签到奖励
     * 5.4创建奖励记录
     * 5.5用户账户更新
     * 5.6更新积分记录
     * }
     * 返回结果：{
     * 1.成功code
     * 2.提示语：“签到成功”
     * 3.累计签到数
     * 4.连续签到数
     * 5.奖励信息
     * }
     * @Date 10:40 2020-03-12
     * @Param [userId]
     **/
    @Transactional(rollbackFor = Exception.class)
    public SignRsp sign(String openId) {
        //从缓存中取出用户信息
        UmsMember member = umsMemberProcessService.get(openId);
        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        //今天开始和结束时间
        LocalDateTime todayBegin = LocalDateUtil.dateTimeZero(now);
        LocalDateTime todayEnd = LocalDateUtil.dateTimeEnd(now);
        // 获取用户签到记录
        List<SmsHomeSignRecord> signRecordList = signRecordList(member.getId(), todayBegin, todayEnd);
        log.info("检查当天是否已经签到");
        Checker.check(signRecordList.size() > 0, "今天已签到");
        //当月第一天的日期
        LocalDateTime zero = LocalDateUtil.dateTimeZero(now);
        LocalDateTime beginDate = zero.withDayOfMonth(1);
        // 获取用户签到记录（倒序排列）
        List<SmsHomeSignRecord> allSignRecordList = signRecordList(member.getId(), beginDate, now);
        log.info("创建签到记录");
        //用户
        SmsHomeSignRecord record = new SmsHomeSignRecord();
        record.setUserId(member.getId());
        record.setNickname(member.getNickname());
        recordService.insertSmsHomeSignRecord(record);
        //累计签到 + 1 (加上本次签到的次数)
        int cumulative = allSignRecordList.size()+1;
        //连续签到 + 1 (加上本次签到的次数)
        int continuous = getContinuousSignInDay(allSignRecordList, now)+1;
        log.info("{}月累计签到：{}次；连续签到：{}次", now.getMonthValue(), cumulative, continuous);

        log.info("签到奖励结算");
        List<SmsHomeSignReward> rewards = signSettle(member, cumulative, continuous, now);
        //获取总记录数
        BigDecimal rewardTatol = rewards.stream().map(r -> r.getRewardVal()).reduce((A, B) -> A.add(B)).orElse(BigDecimal.ZERO);
        //若积分不为空则更新记录/更新用户积分
        if (rewardTatol.compareTo(BigDecimal.ZERO)!=0){
            //插入积分记录
            PmsIntegralLog integralLog = new PmsIntegralLog();
            integralLog.setIntegral(rewardTatol);
            integralLog.setMemberId(member.getId());
            integralLog.setName(PmsIntegralConst.IntegralLogType.SIGNIN.getDescribe());
            integralLog.setType(PmsIntegralConst.IntegralLogType.SIGNIN.getType());
            integralLog.setCreateTime(LocalDateUtil.localDateTimeToDate(now));
            log.info("更新积分记录");
            iPmsIntegralLogService.insertPmsIntegralLog(integralLog);
            //更新用户积分
            log.info("更新用户积分");
            umsMemberService.addMemberIntegral(member.getId(),rewardTatol);
        }
        return new SignRsp(AjaxResult.success("签到成功"), continuous, cumulative, rewards);
    }

    /**
     * @return java.util.List<com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward>
     * @Author LiuQingyu
     * @Description 签到奖励结算
     * @Date 16:15 2020-03-12
     * @Param [userId, cumulative, continuous]
     **/
    private List<SmsHomeSignReward> signSettle(UmsMember member, int cumulative, int continuous, LocalDateTime now) {
        /**根据签到类型（每日/连续/累计）、签到次数，查询对应奖励设置*/
        //每日
        SmsHomeSignSetting dailyReward = getRewardSet(SmsSignConst.SignType.DAILY.getType(), DAILY_TIMES);
        //累计
        SmsHomeSignSetting cumulativeReward = getRewardSet(SmsSignConst.SignType.CUMULATIVE.getType(), cumulative);
        //连续
        SmsHomeSignSetting continuousReward = getRewardSet(SmsSignConst.SignType.CONTINUOUS.getType(), continuous);

        // 签到获奖
        List<SmsHomeSignReward> rewards = new ArrayList<>();

        if (rewardEffective(dailyReward)) {
            log.info("每日签到奖励：{}", dailyReward.getRewardVal());
            rewards.add(reward(dailyReward, member, now));
        }
        if (rewardEffective(cumulativeReward)) {
            log.info("累计签到奖励：{}", cumulativeReward.getRewardVal());
            rewards.add(reward(cumulativeReward, member, now));
        }
        if (rewardEffective(continuousReward)) {
            log.info("连续签到奖励：{}", continuousReward.getRewardVal());
            rewards.add(reward(continuousReward, member, now));
        }

        log.info("创建奖励记录");
        rewardService.insertBatch(rewards);

        log.info("账户更新");

        return rewards;
    }

    /**
     * @return com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward
     * @Author LiuQingyu
     * @Description 签到奖励记录
     * @Date 17:19 2020-03-12
     * @Param []
     **/
    private SmsHomeSignReward reward(SmsHomeSignSetting setting, UmsMember member, LocalDateTime now) {
        SmsHomeSignReward reward = new SmsHomeSignReward();
        reward.setUserId(member.getId());
        reward.setNickname(member.getNickname());
        reward.setSettingId(setting.getId());
        reward.setSignType(setting.getSignType());
        reward.setSignTimes(setting.getSignTimes());
        reward.setRewardVal(setting.getRewardVal());
        reward.setCreateTime(LocalDateUtil.localDateTimeToDate(now));
        return reward;
    }

    /**
     * 连续签到天数
     *
     * @param signRecords
     * @param now
     * @return
     */
    private int getContinuousSignInDay(List<SmsHomeSignRecord> signRecords, LocalDateTime now) {
        // 连续签到数
        int continuousDay = 1;
        // 今日是否签到
        boolean todaySignIn = false;

        for (int i = 0, size = signRecords.size(); i < size; i++) {
            Date datetime = signRecords.get(i).getCreateTime();
            int intervalDay = distanceDay(LocalDateUtil.dateToLocalDateTime(datetime), now);
            //当天签到
            if (intervalDay == 0 && i == 0) {
                todaySignIn = true;
            } else if (intervalDay == continuousDay) {
                continuousDay++;
            } else {
                //不连续，终止判断
                break;
            }
        }
        if (!todaySignIn) {
            continuousDay--;
        }
        return continuousDay;
    }

    /**
     * 两个日期对比间隔天数
     *
     * @param small
     * @param large
     * @return
     */
    private int distanceDay(LocalDateTime small, LocalDateTime large) {
        LocalDateTime smallZero = LocalDateUtil.dateTimeZero(small);
        LocalDateTime largeZero = LocalDateUtil.dateTimeZero(large);
        long minus = LocalDateUtil.minusToMillsLocalDateTime(smallZero, largeZero);
        return (int) (minus / MINUS_OF_DAY);
    }

    /**
     * 已经签到的日期
     *
     * @param signRecordList
     * @return
     */
    private List<String> beforeSignDates(List<SmsHomeSignRecord> signRecordList) {
        List<String> beforeSignDates = new ArrayList<>();
        for (int i = 0, size = signRecordList.size(); i < size; i++) {
            Date datetime = signRecordList.get(i).getCreateTime();
            String beforeSignDate = LocalDateUtil.dateFormat(datetime, "d");
            beforeSignDates.add(beforeSignDate);
        }
        return beforeSignDates;
    }

    /**
     * @return int
     * @Author LiuQingyu
     * @Description 查询用户时间段内的签到记录
     * @Date 15:43 2020-03-12
     * @Param [userId, now]
     **/
    private List<SmsHomeSignRecord> signRecordList(Long userId, LocalDateTime beginDate, LocalDateTime endDate) {
        // 获取用户签到记录（倒序排列）
        SmsHomeSignRecord record = new SmsHomeSignRecord();
        Map<String, Object> params = new HashMap<>(16);
        params.put("beginCreateTime", LocalDateUtil.localDateTimeToDate(beginDate));
        params.put("endCreateTime", LocalDateUtil.localDateTimeToDate(endDate));
        record.setUserId(userId);
        record.setParams(params);
        List<SmsHomeSignRecord> signRecordList = recordService.selectUserSignRecordList(record);
        return signRecordList;
    }

    /**
     * @return com.fante.project.business.smsHomeSignSetting.domain.SmsHomeSignSetting
     * @Author LiuQingyu
     * @Description 根据签到类型（每日/连续/累计）、签到次数，查询奖励设置
     * @Date 16:21 2020-03-12
     * @Param [type, dailyTimes]
     **/
    private SmsHomeSignSetting getRewardSet(String type, int times) {
        SmsHomeSignSetting setting = new SmsHomeSignSetting();
        setting.setSignType(type);
        setting.setSignTimes(times);
        setting.setStatus(Constants.ENABLE);
        return settingService.selectByTypeAndTimes(setting);
    }

    /**
     * 检查奖励是否有效
     *
     * @param rewardSet
     * @return
     */
    private boolean rewardEffective(SmsHomeSignSetting rewardSet) {
        // 奖励设置存在，奖励值大于0，奖励类型正常
        return !ObjectUtils.isEmpty(rewardSet)
                && rewardSet.getRewardVal().compareTo(BigDecimal.ZERO) > 0
                && !ObjectUtils.isEmpty(rewardSet.getRewardType());
    }
}
