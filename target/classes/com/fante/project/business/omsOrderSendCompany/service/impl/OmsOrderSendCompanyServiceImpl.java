package com.fante.project.business.omsOrderSendCompany.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.omsOrderSendCompany.mapper.OmsOrderSendCompanyMapper;
import com.fante.project.business.omsOrderSendCompany.domain.OmsOrderSendCompany;
import com.fante.project.business.omsOrderSendCompany.service.IOmsOrderSendCompanyService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 物流公司Service业务层处理
 *
 * @author fante
 * @date 2020-04-02
 */
@Service
public class OmsOrderSendCompanyServiceImpl implements IOmsOrderSendCompanyService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderSendCompanyServiceImpl.class);

    @Autowired
    private OmsOrderSendCompanyMapper omsOrderSendCompanyMapper;

    /**
     * 查询物流公司
     *
     * @param id 物流公司ID
     * @return 物流公司
     */
    @Override
    public OmsOrderSendCompany selectOmsOrderSendCompanyById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsOrderSendCompanyMapper.selectOmsOrderSendCompanyById(id);
    }

    /**
     * 查询物流公司列表
     *
     * @param omsOrderSendCompany 物流公司
     * @return 物流公司集合
     */
    @Override
    public List<OmsOrderSendCompany> selectOmsOrderSendCompanyList(OmsOrderSendCompany omsOrderSendCompany) {
        omsOrderSendCompany.setShopId(ShiroUtils.getSysUser().getDeptId());
        return omsOrderSendCompanyMapper.selectOmsOrderSendCompanyList(omsOrderSendCompany);
    }

    /**
     * 查询物流公司数量
     *
     * @param omsOrderSendCompany 物流公司
     * @return 结果
     */
    @Override
    public int countOmsOrderSendCompany(OmsOrderSendCompany omsOrderSendCompany){
        return omsOrderSendCompanyMapper.countOmsOrderSendCompany(omsOrderSendCompany);
    }

    /**
     * 唯一校验
     *
     * @param omsOrderSendCompany 物流公司
     * @return 结果
     */
    @Override
    public String checkOmsOrderSendCompanyUnique(OmsOrderSendCompany omsOrderSendCompany) {
        return omsOrderSendCompanyMapper.checkOmsOrderSendCompanyUnique(omsOrderSendCompany) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增物流公司
     *
     * @param omsOrderSendCompany 物流公司
     * @return 新增物流公司数量
     */
    @Override
    public int insertOmsOrderSendCompany(OmsOrderSendCompany omsOrderSendCompany) {
        omsOrderSendCompany.setCreateTime(DateUtils.getNowDate());
        omsOrderSendCompany.setShopId(ShiroUtils.getSysUser().getDeptId());
        return omsOrderSendCompanyMapper.insertOmsOrderSendCompany(omsOrderSendCompany);
    }

    /**
     * 修改物流公司
     *
     * @param omsOrderSendCompany 物流公司
     * @return 修改物流公司数量
     */
    @Override
    public int updateOmsOrderSendCompany(OmsOrderSendCompany omsOrderSendCompany) {
        omsOrderSendCompany.setUpdateTime(DateUtils.getNowDate());
        return omsOrderSendCompanyMapper.updateOmsOrderSendCompany(omsOrderSendCompany);
    }

    /**
     * 删除物流公司对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除物流公司数量
     */
    @Override
    public int deleteOmsOrderSendCompanyByIds(String ids) {
        return omsOrderSendCompanyMapper.deleteOmsOrderSendCompanyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物流公司信息
     *
     * @param id 物流公司ID
     * @return 删除物流公司数量
     */
    @Override
    public int deleteOmsOrderSendCompanyById(Long id) {
        return omsOrderSendCompanyMapper.deleteOmsOrderSendCompanyById(id);
    }
}
