package com.fante.project.business.pmsIntegralLog.service.impl;

import com.fante.common.constant.Constants;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLogDTO;
import com.fante.project.business.pmsIntegralLog.mapper.PmsIntegralLogMapper;
import com.fante.project.business.pmsIntegralLog.service.IPmsIntegralLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 积分兑换记录Service业务层处理
 *
 * @author fante
 * @date 2020-05-01
 */
@Service
public class PmsIntegralLogServiceImpl implements IPmsIntegralLogService {

    private static Logger log = LoggerFactory.getLogger(PmsIntegralLogServiceImpl.class);

    @Autowired
    private PmsIntegralLogMapper pmsIntegralLogMapper;

    /**
     * 查询积分兑换记录
     *
     * @param id 积分兑换记录ID
     * @return 积分兑换记录
     */
    @Override
    public PmsIntegralLog selectPmsIntegralLogById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsIntegralLogMapper.selectPmsIntegralLogById(id);
    }

    /**
     * 查询积分兑换记录列表
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 积分兑换记录集合
     */
    @Override
    public List<PmsIntegralLog> selectPmsIntegralLogList(PmsIntegralLog pmsIntegralLog) {
        return pmsIntegralLogMapper.getList(pmsIntegralLog);
    }

    /**
     * 查询积分兑换记录数量
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 结果
     */
    @Override
    public int countPmsIntegralLog(PmsIntegralLog pmsIntegralLog){
        return pmsIntegralLogMapper.countPmsIntegralLog(pmsIntegralLog);
    }

    /**
     * 唯一校验
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 结果
     */
    @Override
    public String checkPmsIntegralLogUnique(PmsIntegralLog pmsIntegralLog) {
        return pmsIntegralLogMapper.checkPmsIntegralLogUnique(pmsIntegralLog) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增积分兑换记录
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 新增积分兑换记录数量
     */
    @Override
    public int insertPmsIntegralLog(PmsIntegralLog pmsIntegralLog) {
        pmsIntegralLog.setCreateTime(DateUtils.getNowDate());
        return pmsIntegralLogMapper.insertPmsIntegralLog(pmsIntegralLog);
    }

    /**
     * 修改积分兑换记录
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 修改积分兑换记录数量
     */
    @Override
    public int updatePmsIntegralLog(PmsIntegralLog pmsIntegralLog) {
        pmsIntegralLog.setUpdateTime(DateUtils.getNowDate());
        return pmsIntegralLogMapper.updatePmsIntegralLog(pmsIntegralLog);
    }

    /**
     * 删除积分兑换记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除积分兑换记录数量
     */
    @Override
    public int deletePmsIntegralLogByIds(String ids) {
        return pmsIntegralLogMapper.deletePmsIntegralLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除积分兑换记录信息
     *
     * @param id 积分兑换记录ID
     * @return 删除积分兑换记录数量
     */
    @Override
    public int deletePmsIntegralLogById(Long id) {
        return pmsIntegralLogMapper.deletePmsIntegralLogById(id);
    }

    /**
     * (app) 获取会员积分记录
     * @param log
     * @return
     */
    @Override
    public List<PmsIntegralLog> getMemberList(PmsIntegralLog log) {
        return pmsIntegralLogMapper.getMemberList(log);
    }


    /**
     * 积分记录与用户信息关联查询
     * @param dto
     * @return
     */
    @Override
    public List<PmsIntegralLogDTO> selectJoinList(PmsIntegralLogDTO dto) {
        return pmsIntegralLogMapper.selectJoinList(dto);
    }
}
