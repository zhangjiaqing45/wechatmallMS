package com.fante.project.business.pmsFeightTemplate.service.impl;

import com.fante.common.constant.Constants;
import com.fante.common.exception.BusinessException;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplateUseDTO;
import com.fante.project.business.pmsFeightTemplate.mapper.PmsFeightTemplateMapper;
import com.fante.project.business.pmsFeightTemplate.service.IPmsFeightTemplateService;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.system.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 运费模版Service业务层处理
 *
 * @author fante
 * @date 2020-03-16
 */
@Service
public class PmsFeightTemplateServiceImpl implements IPmsFeightTemplateService {

    private static Logger log = LoggerFactory.getLogger(PmsFeightTemplateServiceImpl.class);

    @Autowired
    private PmsFeightTemplateMapper pmsFeightTemplateMapper;
    @Autowired
    private IPmsProductService pmsProductService;

    /**
     * 查询运费模版
     *
     * @param id 运费模版ID
     * @return 运费模版
     */
    @Override
    public PmsFeightTemplate selectPmsFeightTemplateById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsFeightTemplateMapper.selectPmsFeightTemplateById(id);
    }

    /**
     * 查询运费模版列表
     *
     * @param pmsFeightTemplate 运费模版
     * @return 运费模版集合
     */
    @Override
    public List<PmsFeightTemplate> selectPmsFeightTemplateList(PmsFeightTemplate pmsFeightTemplate) {
        return pmsFeightTemplateMapper.selectPmsFeightTemplateList(pmsFeightTemplate);
    }

    /**
     * 查询可用运费模板
     * @return
     */
    @Override
    public List<PmsFeightTemplate> selectAvailableList() {
        User user = ShiroUtils.getSysUser();
        PmsFeightTemplate pmsFeightTemplate = new PmsFeightTemplate();
        pmsFeightTemplate.setStatus(Constants.ENABLE);
        if (user.isFranchisee()) {
            pmsFeightTemplate.setShopId(user.getDeptId());
        }
        return selectPmsFeightTemplateList(pmsFeightTemplate);
    }

    /**
     * 查询运费模版数量
     *
     * @param pmsFeightTemplate 运费模版
     * @return 结果
     */
    @Override
    public int countPmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate){
        return pmsFeightTemplateMapper.countPmsFeightTemplate(pmsFeightTemplate);
    }

    /**
     * 唯一校验
     *
     * @param pmsFeightTemplate 运费模版
     * @return 结果
     */
    @Override
    public boolean checkPmsFeightTemplateUnique(PmsFeightTemplate pmsFeightTemplate) {
        return pmsFeightTemplateMapper.checkPmsFeightTemplateUnique(pmsFeightTemplate) > 0;
    }

    /**
     * 新增运费模版
     *
     * @param pmsFeightTemplate 运费模版
     * @return 新增运费模版数量
     */
    @Override
    public int insertPmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate) {
        Checker.check(checkPmsFeightTemplateUnique(pmsFeightTemplate), "店铺模板名称重复");
        Checker.checkOp(!ObjectUtils.isEmpty(pmsFeightTemplate.getFeightFee()) && pmsFeightTemplate.getFeightFee().compareTo(BigDecimal.ZERO) > 0, "付费区域运费需要大于0");
        if (StringUtils.isBlank(pmsFeightTemplate.getCreateBy())) {
            pmsFeightTemplate.setCreateBy(ShiroUtils.getLoginName());
        }
        pmsFeightTemplate.setCreateTime(DateUtils.getNowDate());
        return pmsFeightTemplateMapper.insertPmsFeightTemplate(pmsFeightTemplate);
    }

    /**
     * 修改运费模版
     *
     * @param pmsFeightTemplate 运费模版
     * @return 修改运费模版数量
     */
    @Override
    public int updatePmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate) {
        Checker.check(checkPmsFeightTemplateUnique(pmsFeightTemplate), "店铺模板名称重复");
        Checker.checkOp(!ObjectUtils.isEmpty(pmsFeightTemplate.getFeightFee()) && pmsFeightTemplate.getFeightFee().compareTo(BigDecimal.ZERO) > 0, "付费区域运费需要大于0");
        if (StringUtils.isBlank(pmsFeightTemplate.getUpdateBy())) {
            pmsFeightTemplate.setUpdateBy(ShiroUtils.getLoginName());
        }
        pmsFeightTemplate.setUpdateTime(DateUtils.getNowDate());
        return pmsFeightTemplateMapper.updatePmsFeightTemplate(pmsFeightTemplate);
    }

    /**
     * 删除运费模版对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除运费模版数量
     */
    @Override
    public int deletePmsFeightTemplateByIds(String ids) {
        return pmsFeightTemplateMapper.deletePmsFeightTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除运费模版信息
     *
     * @param id 运费模版ID
     * @return 删除运费模版数量
     */
    @Override
    public int deletePmsFeightTemplateById(Long id) {
        int count = countPmsProductByTemplateId(id);
        Checker.check(count > 0, "当前模板使用中，暂时无法删除");
        return pmsFeightTemplateMapper.deletePmsFeightTemplateById(id);
    }

    /**
     * 统计使用指定运费模板的商品
     * @param templateId
     * @return
     */
    private int countPmsProductByTemplateId(Long templateId){
        Checker.check(ObjectUtils.isEmpty(templateId), "缺少运费模板标识");
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setFeightTemplateId(templateId);
        return pmsProductService.countPmsProduct(pmsProduct);
    }


    /**
     * 根据商品关联的运费模板ID和下单区域，查询商品对应运费<br/>
     * 返回值：0 包邮，大于0 付费， -1 不配送
     * @param tpId
     * @param region
     * @return
     */
    @Override
    public BigDecimal getFeightFeeByRegion(Long tpId, String region) {

        PmsFeightTemplate template = selectPmsFeightTemplateById(tpId);
        //Checker.check(ObjectUtils.isEmpty(template), "运费模板不存在");

        String freeDest = StringUtils.defaultString(template.getFreeDest());
        String tollDest = StringUtils.defaultString(template.getTollDest());
        String excludeDest = StringUtils.defaultString(template.getExcludeDest());

        if (freeDest.contains(region)) {
            // 包邮配送区域
            return BigDecimal.ZERO;
        } else if (tollDest.contains(region)) {
            // 买家付运费区域
            return template.getFeightFee();
        } else if (excludeDest.contains(region)) {
            // 不配送区域
            return BigDecimal.ONE.negate();
        } else {
            // 传入的区域未包含于在包邮、付运费或不配送
            log.error("区域: {}, 参数异常", region);
            return BigDecimal.ONE.negate();
            //throw new BusinessException(AjaxResult.Type.ERROR.value(), "区域参数异常");
        }
    }

    /**
     * 模板使用情况
     * @param useDTO
     * @return
     */
    @Override
    public List<PmsFeightTemplateUseDTO> selectTemplateUse(PmsFeightTemplateUseDTO useDTO) {
        return pmsFeightTemplateMapper.selectTemplateUse(useDTO);
    }
}
