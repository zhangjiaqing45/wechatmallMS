package com.fante.project.business.smsFlashPromotionSession.mapper;

import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
import com.fante.project.mapperBase.SmsFlashPromotionSessionMapperBase;

/**
 * 秒杀活动时间段Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-21
 */

public interface SmsFlashPromotionSessionMapper extends SmsFlashPromotionSessionMapperBase {
    /**
     * 获取当前秒杀时间段
     */
    Long getNowFlashSession();
}
