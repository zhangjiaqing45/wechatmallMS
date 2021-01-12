package com.fante.project.mapperBase;

import com.fante.project.business.pmsIntegralFeightTemplate.domain.PmsIntegralFeightTemplate;
import java.util.List;

/**
 * 积分商品运费设置Mapper基础接口
 *
 * @author fante
 * @date 2020-04-13
 */
public interface PmsIntegralFeightTemplateMapperBase {
    /**
     * 查询积分商品运费设置
     *
     * @param id 积分商品运费设置ID
     * @return 积分商品运费设置
     */
    public PmsIntegralFeightTemplate selectPmsIntegralFeightTemplateById(Long id);

    /**
     * 查询积分商品运费设置列表
     *
     * @param pmsIntegralFeightTemplate 积分商品运费设置
     * @return 积分商品运费设置集合
     */
    public List<PmsIntegralFeightTemplate> selectPmsIntegralFeightTemplateList(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);

    /**
     * 查询积分商品运费设置数量
     *
     * @param pmsIntegralFeightTemplate 积分商品运费设置
     * @return 结果
     */
    public int countPmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);

    /**
     * 唯一校验
     *
     * @param pmsIntegralFeightTemplate 积分商品运费设置
     * @return 结果
     */
    public int checkPmsIntegralFeightTemplateUnique(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);

    /**
     * 新增积分商品运费设置
     *
     * @param pmsIntegralFeightTemplate 积分商品运费设置
     * @return 结果
     */
    public int insertPmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);

    /**
     * 修改积分商品运费设置
     *
     * @param pmsIntegralFeightTemplate 积分商品运费设置
     * @return 结果
     */
    public int updatePmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);

    /**
     * 删除积分商品运费设置
     *
     * @param id 积分商品运费设置ID
     * @return 结果
     */
    public int deletePmsIntegralFeightTemplateById(Long id);

    /**
     * 批量删除积分商品运费设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsIntegralFeightTemplateByIds(String[] ids);

}
