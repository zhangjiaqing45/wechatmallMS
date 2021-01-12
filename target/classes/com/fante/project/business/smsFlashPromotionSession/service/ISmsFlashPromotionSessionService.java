package com.fante.project.business.smsFlashPromotionSession.service;

import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
import java.util.List;

/**
 * 秒杀活动时间段Service接口
 *
 * @author fante
 * @date 2020-03-21
 */
public interface ISmsFlashPromotionSessionService {
    /**
     * 查询秒杀活动时间段
     *
     * @param id 秒杀活动时间段ID
     * @return 秒杀活动时间段
     */
    public SmsFlashPromotionSession selectSmsFlashPromotionSessionById(Long id);

    /**
     * 查询秒杀活动时间段列表
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 秒杀活动时间段集合
     */
    public List<SmsFlashPromotionSession> selectSmsFlashPromotionSessionList(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 可用的秒杀活动时间段
     * @return
     */
    public List<SmsFlashPromotionSession> selectAvailableSessionList();


    /**
     * 查询秒杀活动时间段数量
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 结果
     */
    public int countSmsFlashPromotionSession(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 唯一校验
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 结果
     */
    public boolean checkSmsFlashPromotionSessionUnique(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 新增秒杀活动时间段
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 新增秒杀活动时间段数量
     */
    public int insertSmsFlashPromotionSession(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 修改秒杀活动时间段
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 修改秒杀活动时间段数量
     */
    public int updateSmsFlashPromotionSession(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 批量删除秒杀活动时间段
     *
     * @param ids 需要删除的数据ID
     * @return 删除秒杀活动时间段数量
     */
    public int deleteSmsFlashPromotionSessionByIds(String ids);

    /**
     * 删除秒杀活动时间段信息
     *
     * @param id 秒杀活动时间段ID
     * @return 删除秒杀活动时间段数量
     */
    public int deleteSmsFlashPromotionSessionById(Long id);

    /**
     * 获取当前秒杀时间段
     */
    public Long getNowFlashSession();
}
