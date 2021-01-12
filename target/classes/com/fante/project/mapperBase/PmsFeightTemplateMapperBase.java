package com.fante.project.mapperBase;

import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import java.util.List;

/**
 * 运费模版Mapper基础接口
 *
 * @author fante
 * @date 2020-03-16
 */
public interface PmsFeightTemplateMapperBase {
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
    public int checkPmsFeightTemplateUnique(PmsFeightTemplate pmsFeightTemplate);

    /**
     * 新增运费模版
     *
     * @param pmsFeightTemplate 运费模版
     * @return 结果
     */
    public int insertPmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate);

    /**
     * 修改运费模版
     *
     * @param pmsFeightTemplate 运费模版
     * @return 结果
     */
    public int updatePmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate);

    /**
     * 删除运费模版
     *
     * @param id 运费模版ID
     * @return 结果
     */
    public int deletePmsFeightTemplateById(Long id);

    /**
     * 批量删除运费模版
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsFeightTemplateByIds(String[] ids);

}
