package com.fante.project.business.omsOrderReturnReason.service.impl;

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
import com.fante.project.business.omsOrderReturnReason.mapper.OmsOrderReturnReasonMapper;
import com.fante.project.business.omsOrderReturnReason.domain.OmsOrderReturnReason;
import com.fante.project.business.omsOrderReturnReason.service.IOmsOrderReturnReasonService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 退货原因设置Service业务层处理
 *
 * @author fante
 * @date 2020-03-27
 */
@Service
public class OmsOrderReturnReasonServiceImpl implements IOmsOrderReturnReasonService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderReturnReasonServiceImpl.class);

    @Autowired
    private OmsOrderReturnReasonMapper omsOrderReturnReasonMapper;

    /**
     * 查询退货原因设置
     *
     * @param id 退货原因设置ID
     * @return 退货原因设置
     */
    @Override
    public OmsOrderReturnReason selectOmsOrderReturnReasonById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsOrderReturnReasonMapper.selectOmsOrderReturnReasonById(id);
    }

    /**
     * 查询退货原因设置列表
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 退货原因设置集合
     */
    @Override
    public List<OmsOrderReturnReason> selectOmsOrderReturnReasonList(OmsOrderReturnReason omsOrderReturnReason) {
        return omsOrderReturnReasonMapper.selectOmsOrderReturnReasonList(omsOrderReturnReason);
    }

    /**
     * 查询退货原因设置数量
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 结果
     */
    @Override
    public int countOmsOrderReturnReason(OmsOrderReturnReason omsOrderReturnReason){
        return omsOrderReturnReasonMapper.countOmsOrderReturnReason(omsOrderReturnReason);
    }

    /**
     * 唯一校验
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 结果
     */
    @Override
    public String checkOmsOrderReturnReasonUnique(OmsOrderReturnReason omsOrderReturnReason) {
        return omsOrderReturnReasonMapper.checkOmsOrderReturnReasonUnique(omsOrderReturnReason) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增退货原因设置
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 新增退货原因设置数量
     */
    @Override
    public int insertOmsOrderReturnReason(OmsOrderReturnReason omsOrderReturnReason) {
        //验证
        ValidateParams(omsOrderReturnReason);
        omsOrderReturnReason.setCreateBy(ShiroUtils.getLoginName());
        omsOrderReturnReason.setCreateTime(DateUtils.getNowDate());
        return omsOrderReturnReasonMapper.insertOmsOrderReturnReason(omsOrderReturnReason);
    }

    /**
     * 验证参数
     * @param omsOrderReturnReason
     */
    private void ValidateParams(OmsOrderReturnReason omsOrderReturnReason){
        Checker.check(StringUtils.isEmpty(omsOrderReturnReason.getName()),"缺少退货原因信息.");
        String exist = checkOmsOrderReturnReasonUnique(omsOrderReturnReason);
        Checker.checkOp(StringUtils.equals(exist,Constants.UNIQUE),"此退货原因已存在.");
    }
    /**
     * 修改退货原因设置
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 修改退货原因设置数量
     */
    @Override
    public int updateOmsOrderReturnReason(OmsOrderReturnReason omsOrderReturnReason) {
        //验证
        Checker.check(ObjectUtils.isEmpty(omsOrderReturnReason.getId()),"未找到要修改的退货原因.");
        ValidateParams(omsOrderReturnReason);
        omsOrderReturnReason.setUpdateBy(ShiroUtils.getLoginName());
        omsOrderReturnReason.setUpdateTime(DateUtils.getNowDate());
        return omsOrderReturnReasonMapper.updateOmsOrderReturnReason(omsOrderReturnReason);
    }

    /**
     * 删除退货原因设置对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除退货原因设置数量
     */
    @Override
    public int deleteOmsOrderReturnReasonByIds(String ids) {
        return omsOrderReturnReasonMapper.realDelOmsOrderReturnReasonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退货原因设置信息
     *
     * @param id 退货原因设置ID
     * @return 删除退货原因设置数量
     */
    @Override
    public int deleteOmsOrderReturnReasonById(Long id) {
        return omsOrderReturnReasonMapper.deleteOmsOrderReturnReasonById(id);
    }
}
