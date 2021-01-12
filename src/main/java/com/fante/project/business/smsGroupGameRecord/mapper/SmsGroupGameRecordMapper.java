package com.fante.project.business.smsGroupGameRecord.mapper;

import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecordDetail;
import com.fante.project.mapperBase.SmsGroupGameRecordMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 团购记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-30
 */

 public interface SmsGroupGameRecordMapper extends SmsGroupGameRecordMapperBase {

    /**
     * 查询团购记录
     *
     * @param id 团购记录ID
     * @return 团购记录
     */
     SmsGroupGameRecordDetail getSmsGroupGameRecordDetailById(Long id);
    /**
     * 查询团购记录列表
     *
     * @param smsGroupGameRecord 团购记录
     * @return 团购记录集合
     */
     List<SmsGroupGameRecord> getSmsGroupGameRecordList(SmsGroupGameRecord smsGroupGameRecord);
    /**
     * 预定名额
     * @param groupId
     * @return
     */
     int reservationQuotas(Long groupId);
    /**
     * 释放名额
     * @param groupId
     * @return
     */
     int recoverQuotas(Long groupId);
    /**
     * 支付成功正式加入此团
     * @param groupId
     * @return
     */
     int paySuccess(Long groupId);
    /**
     * 判断次团购的时效是否过期(-1:过期 其他:团购目标数量-实际数量)
     */
     int validateAging(Long id);
    /**
     * 设置团购状态为成功
     * @param recordId
     */
     int groupSuccess(Long recordId);
    /**
     * 设置团购状态为失败
     * @param recordId
     */
    int groupFailure(Long recordId);
    /**
     * (app)获取当前商品团购记录
     * @param groupGameId
     * @param memberId
     * @return
     */
    List<Long> getGroupRecordListBase(@Param("groupGameId") Long groupGameId,
                                                      @Param("memberId")Long memberId,
                                                      @Param("status")String status);
    /**
     * (app)获取当前商品团购记录
     * @param ids
     * @return
     */
    List<SmsGroupGameRecordDetail> getGroupRecordList( @Param("ids")List<Long> ids, @Param("aging") int aging);

    /**
     * 获取用户团购记录详情
     * @param memberId
     * @return
     */
    List<Long> getMemberGroupRecordBase( @Param("memberId")Long memberId,
                                                         @Param("status")String status);
    /**
     * 获取用户团购记录详情
     * @param ids
     * @return
     */
    List<SmsGroupGameRecordDetail> getMemberGroupRecord( @Param("ids") List<Long> ids);
}
