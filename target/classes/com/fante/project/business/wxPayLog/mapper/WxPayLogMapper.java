package com.fante.project.business.wxPayLog.mapper;

import com.fante.project.business.wxPayLog.domain.WxPayLog;
import java.util.List;

/**
 * 微信支付日志Mapper接口
 *
 * @author fante
 * @date 2020-02-21
 */
public interface WxPayLogMapper {
    /**
     * 查询微信支付日志
     *
     * @param id 微信支付日志ID
     * @return 微信支付日志
     */
    public WxPayLog selectWxPayLogById(Long id);

    /**
     * 查询微信支付日志列表
     *
     * @param wxPayLog 微信支付日志
     * @return 微信支付日志集合
     */
    public List<WxPayLog> selectWxPayLogList(WxPayLog wxPayLog);

    /**
     * 新增微信支付日志
     *
     * @param wxPayLog 微信支付日志
     * @return 结果
     */
    public int insertWxPayLog(WxPayLog wxPayLog);

    /**
     * 修改微信支付日志
     *
     * @param wxPayLog 微信支付日志
     * @return 结果
     */
    public int updateWxPayLog(WxPayLog wxPayLog);

    /**
     * 删除微信支付日志
     *
     * @param id 微信支付日志ID
     * @return 结果
     */
    public int deleteWxPayLogById(Long id);

    /**
     * 批量删除微信支付日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWxPayLogByIds(String[] ids);
}
