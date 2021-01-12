package com.fante.project.business.smsFlashPromotionSession.service.impl;

import java.util.List;

import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsFlashPromotionSession.mapper.SmsFlashPromotionSessionMapper;
import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
import com.fante.project.business.smsFlashPromotionSession.service.ISmsFlashPromotionSessionService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 秒杀活动时间段Service业务层处理
 *
 * @author fante
 * @date 2020-03-21
 */
@Service
public class SmsFlashPromotionSessionServiceImpl implements ISmsFlashPromotionSessionService {

    private static Logger log = LoggerFactory.getLogger(SmsFlashPromotionSessionServiceImpl.class);

    @Autowired
    private SmsFlashPromotionSessionMapper smsFlashPromotionSessionMapper;

    /**
     * 查询秒杀活动时间段
     *
     * @param id 秒杀活动时间段ID
     * @return 秒杀活动时间段
     */
    @Override
    public SmsFlashPromotionSession selectSmsFlashPromotionSessionById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsFlashPromotionSessionMapper.selectSmsFlashPromotionSessionById(id);
    }

    /**
     * 查询秒杀活动时间段列表
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 秒杀活动时间段集合
     */
    @Override
    public List<SmsFlashPromotionSession> selectSmsFlashPromotionSessionList(SmsFlashPromotionSession smsFlashPromotionSession) {
        return smsFlashPromotionSessionMapper.selectSmsFlashPromotionSessionList(smsFlashPromotionSession);
    }

    /**
     * 可用的秒杀活动时间段
     * @return
     */
    @Override
    public List<SmsFlashPromotionSession> selectAvailableSessionList() {
        SmsFlashPromotionSession session = new SmsFlashPromotionSession();
        session.setStatus(Constants.ENABLE);
        return selectSmsFlashPromotionSessionList(session);
    }

    /**
     * 查询秒杀活动时间段数量
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 结果
     */
    @Override
    public int countSmsFlashPromotionSession(SmsFlashPromotionSession smsFlashPromotionSession){
        return smsFlashPromotionSessionMapper.countSmsFlashPromotionSession(smsFlashPromotionSession);
    }

    /**
     * 唯一校验
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 结果
     */
    @Override
    public boolean checkSmsFlashPromotionSessionUnique(SmsFlashPromotionSession smsFlashPromotionSession) {
        return smsFlashPromotionSessionMapper.checkSmsFlashPromotionSessionUnique(smsFlashPromotionSession) > 0;
    }

    /**
     * 新增秒杀活动时间段
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 新增秒杀活动时间段数量
     */
    @Override
    public int insertSmsFlashPromotionSession(SmsFlashPromotionSession smsFlashPromotionSession) {
        Checker.check(checkSmsFlashPromotionSessionUnique(smsFlashPromotionSession), "时段名称已存在");
        if (StringUtils.isBlank(smsFlashPromotionSession.getCreateBy())) {
            smsFlashPromotionSession.setCreateBy(ShiroUtils.getLoginName());
        }
        smsFlashPromotionSession.setCreateTime(DateUtils.getNowDate());
        return smsFlashPromotionSessionMapper.insertSmsFlashPromotionSession(smsFlashPromotionSession);
    }

    /**
     * 修改秒杀活动时间段
     *
     * @param smsFlashPromotionSession 秒杀活动时间段
     * @return 修改秒杀活动时间段数量
     */
    @Override
    public int updateSmsFlashPromotionSession(SmsFlashPromotionSession smsFlashPromotionSession) {
        Checker.check(checkSmsFlashPromotionSessionUnique(smsFlashPromotionSession), "时段名称已存在");
        if (StringUtils.isBlank(smsFlashPromotionSession.getUpdateBy())) {
            smsFlashPromotionSession.setUpdateBy(ShiroUtils.getLoginName());
        }
        smsFlashPromotionSession.setUpdateTime(DateUtils.getNowDate());
        return smsFlashPromotionSessionMapper.updateSmsFlashPromotionSession(smsFlashPromotionSession);
    }

    /**
     * 删除秒杀活动时间段对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除秒杀活动时间段数量
     */
    @Override
    public int deleteSmsFlashPromotionSessionByIds(String ids) {
        return smsFlashPromotionSessionMapper.deleteSmsFlashPromotionSessionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除秒杀活动时间段信息
     *
     * @param id 秒杀活动时间段ID
     * @return 删除秒杀活动时间段数量
     */
    @Override
    public int deleteSmsFlashPromotionSessionById(Long id) {
        return smsFlashPromotionSessionMapper.deleteSmsFlashPromotionSessionById(id);
    }

    /**
     * 获取当前秒杀时间段
     */
    @Override
    public Long getNowFlashSession(){
        return smsFlashPromotionSessionMapper.getNowFlashSession();
    }
}
