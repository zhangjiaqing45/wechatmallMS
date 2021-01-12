package com.fante.project.business.wxPayLog.service.impl;

import java.util.List;
                                                                                                                                    import com.fante.common.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.wxPayLog.mapper.WxPayLogMapper;
import com.fante.project.business.wxPayLog.domain.WxPayLog;
import com.fante.project.business.wxPayLog.service.IWxPayLogService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 微信支付日志Service业务层处理
 *
 * @author fante
 * @date 2020-02-21
 */
@Service
public class WxPayLogServiceImpl implements IWxPayLogService {

    private static Logger log = LoggerFactory.getLogger(WxPayLogServiceImpl.class);

    @Autowired
    private WxPayLogMapper wxPayLogMapper;

    /**
     * 查询微信支付日志
     *
     * @param id 微信支付日志ID
     * @return 微信支付日志
     */
    @Override
    public WxPayLog selectWxPayLogById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return wxPayLogMapper.selectWxPayLogById(id);
    }

    /**
     * 查询微信支付日志列表
     *
     * @param wxPayLog 微信支付日志
     * @return 微信支付日志
     */
    @Override
    public List<WxPayLog> selectWxPayLogList(WxPayLog wxPayLog) {
        return wxPayLogMapper.selectWxPayLogList(wxPayLog);
    }

    /**
     * 新增微信支付日志
     *
     * @param wxPayLog 微信支付日志
     * @return 结果
     */
    @Override
    public int insertWxPayLog(WxPayLog wxPayLog) {
                                                                                                                                                                                                                        wxPayLog.setCreateTime(DateUtils.getNowDate());
                                                                                                            return wxPayLogMapper.insertWxPayLog(wxPayLog);
    }

    /**
     * 修改微信支付日志
     *
     * @param wxPayLog 微信支付日志
     * @return 结果
     */
    @Override
    public int updateWxPayLog(WxPayLog wxPayLog) {
                                                                                                                                                                                                                                                                wxPayLog.setUpdateTime(DateUtils.getNowDate());
                                                                    return wxPayLogMapper.updateWxPayLog(wxPayLog);
    }

    /**
     * 删除微信支付日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWxPayLogByIds(String ids) {
        return wxPayLogMapper.deleteWxPayLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信支付日志信息
     *
     * @param id 微信支付日志ID
     * @return 结果
     */
    @Override
    public int deleteWxPayLogById(Long id) {
        return wxPayLogMapper.deleteWxPayLogById(id);
    }
    }
