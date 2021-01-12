package com.fante.project.business.omsOrderOperateHistory.mapper;

import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistory;
import com.fante.project.mapperBase.OmsOrderOperateHistoryMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作历史记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-02
 */

public interface OmsOrderOperateHistoryMapper extends OmsOrderOperateHistoryMapperBase {
    /**
     * 批量插入
     * @param operateHistoryList
     * @return
     */
    int batchInsertOmsOrderOperateHistory(@Param("list")List<OmsOrderOperateHistory> operateHistoryList);
}
