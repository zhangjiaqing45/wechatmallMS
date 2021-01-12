package com.fante.project.business.omsOrderWriteOff.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.omsOrderWriteOff.mapper.OmsOrderWriteOffMapper;
import com.fante.project.business.omsOrderWriteOff.domain.OmsOrderWriteOff;
import com.fante.project.business.omsOrderWriteOff.service.IOmsOrderWriteOffService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 订单核销记录Service业务层处理
 *
 * @author fante
 * @date 2020-11-14
 */
@Service
public class OmsOrderWriteOffServiceImpl implements IOmsOrderWriteOffService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderWriteOffServiceImpl.class);

    @Autowired
    private OmsOrderWriteOffMapper omsOrderWriteOffMapper;

    /**
     * 查询订单核销记录
     *
     * @param id 订单核销记录ID
     * @return 订单核销记录
     */
    @Override
    public OmsOrderWriteOff selectOmsOrderWriteOffById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsOrderWriteOffMapper.selectOmsOrderWriteOffById(id);
    }

    /**
     * 查询订单核销记录列表
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 订单核销记录集合
     */
    @Override
    public List<OmsOrderWriteOff> selectOmsOrderWriteOffList(OmsOrderWriteOff omsOrderWriteOff) {
        return omsOrderWriteOffMapper.selectOmsOrderWriteOffList(omsOrderWriteOff);
    }

    /**
     * 查询订单核销记录数量
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 结果
     */
    @Override
    public int countOmsOrderWriteOff(OmsOrderWriteOff omsOrderWriteOff){
        return omsOrderWriteOffMapper.countOmsOrderWriteOff(omsOrderWriteOff);
    }

    /**
     * 唯一校验
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 结果
     */
    @Override
    public String checkOmsOrderWriteOffUnique(OmsOrderWriteOff omsOrderWriteOff) {
        return omsOrderWriteOffMapper.checkOmsOrderWriteOffUnique(omsOrderWriteOff) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增订单核销记录
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 新增订单核销记录数量
     */
    @Override
    public int insertOmsOrderWriteOff(OmsOrderWriteOff omsOrderWriteOff) {
        omsOrderWriteOff.setCreateTime(DateUtils.getNowDate());
        return omsOrderWriteOffMapper.insertOmsOrderWriteOff(omsOrderWriteOff);
    }

    /**
     * 修改订单核销记录
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 修改订单核销记录数量
     */
    @Override
    public int updateOmsOrderWriteOff(OmsOrderWriteOff omsOrderWriteOff) {
        omsOrderWriteOff.setUpdateTime(DateUtils.getNowDate());
        return omsOrderWriteOffMapper.updateOmsOrderWriteOff(omsOrderWriteOff);
    }

    /**
     * 删除订单核销记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单核销记录数量
     */
    @Override
    public int deleteOmsOrderWriteOffByIds(String ids) {
        return omsOrderWriteOffMapper.deleteOmsOrderWriteOffByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单核销记录信息
     *
     * @param id 订单核销记录ID
     * @return 删除订单核销记录数量
     */
    @Override
    public int deleteOmsOrderWriteOffById(Long id) {
        return omsOrderWriteOffMapper.deleteOmsOrderWriteOffById(id);
    }
}
