package com.fante.project.mapperBase;

import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
import java.util.List;

/**
 * 秒杀活动时间段Mapper基础接口
 *
 * @author fante
 * @date 2020-03-21
 */
public interface SmsFlashPromotionSessionMapperBase {
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
    public int checkSmsFlashPromotionSessionUnique(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 新增秒杀活动时间段
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 结果
     */
    public int insertSmsFlashPromotionSession(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 修改秒杀活动时间段
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 结果
     */
    public int updateSmsFlashPromotionSession(SmsFlashPromotionSession smsFlashPromotionSession);

    /**
     * 删除秒杀活动时间段
     *
     * @param id 秒杀活动时间段ID
     * @return 结果
     */
    public int deleteSmsFlashPromotionSessionById(Long id);

    /**
     * 批量删除秒杀活动时间段
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsFlashPromotionSessionByIds(String[] ids);

}
