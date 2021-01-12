package com.fante.project.business.pmsFeightTemplate.service;

import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplateUseDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 运费模版Service接口
 *
 * @author fante
 * @date 2020-03-16
 */
public interface IPmsFeightTemplateService {
    /**
     * 查询运费模版
     *
     * @param id 运费模版ID
     * @return 运费模版
     */
    public PmsFeightTemplate selectPmsFeightTemplateById(Long id);

    /**
     * 查询运费模版列表
     *
     * @param pmsFeightTemplate 运费模版
     * @return 运费模版集合
     */
    public List<PmsFeightTemplate> selectPmsFeightTemplateList(PmsFeightTemplate pmsFeightTemplate);

    /**
     * 查询可用运费模板
     * @return
     */
    public List<PmsFeightTemplate> selectAvailableList();

    /**
     * 查询运费模版数量
     *
     * @param pmsFeightTemplate 运费模版
     * @return 结果
     */
    public int countPmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate);

    /**
     * 唯一校验
     *
     * @param pmsFeightTemplate 运费模版
     * @return 结果
     */
    public boolean checkPmsFeightTemplateUnique(PmsFeightTemplate pmsFeightTemplate);

    /**
     * 新增运费模版
     *
     * @param pmsFeightTemplate 运费模版
     * @return 新增运费模版数量
     */
    public int insertPmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate);

    /**
     * 修改运费模版
     *
     * @param pmsFeightTemplate 运费模版
     * @return 修改运费模版数量
     */
    public int updatePmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate);

    /**
     * 批量删除运费模版
     *
     * @param ids 需要删除的数据ID
     * @return 删除运费模版数量
     */
    public int deletePmsFeightTemplateByIds(String ids);

    /**
     * 删除运费模版信息
     *
     * @param id 运费模版ID
     * @return 删除运费模版数量
     */
    public int deletePmsFeightTemplateById(Long id);

    /**
     * 根据商品关联的运费模板ID和下单区域，查询商品对应运费<br/>
     * 返回值：0 包邮，大于0 付费， -1 不配送
     * @param tpId
     * @param region
     * @return
     */
    public BigDecimal getFeightFeeByRegion(Long tpId, String region);

    /**
     * 模板使用情况
     * @param useDTO
     * @return
     */
    List<PmsFeightTemplateUseDTO> selectTemplateUse(PmsFeightTemplateUseDTO useDTO);

    }
