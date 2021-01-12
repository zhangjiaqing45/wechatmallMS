package com.fante.project.mapperBase;

import com.fante.project.business.pmsProductAttribute.domain.PmsProductAttribute;
import java.util.List;

/**
 * 商品属性＆参数Mapper基础接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface PmsProductAttributeMapperBase {
    /**
     * 查询商品属性＆参数
     *
     * @param id 商品属性＆参数ID
     * @return 商品属性＆参数
     */
    public PmsProductAttribute selectPmsProductAttributeById(Long id);

    /**
     * 查询商品属性＆参数列表
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 商品属性＆参数集合
     */
    public List<PmsProductAttribute> selectPmsProductAttributeList(PmsProductAttribute pmsProductAttribute);

    /**
     * 查询商品属性＆参数数量
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 结果
     */
    public int countPmsProductAttribute(PmsProductAttribute pmsProductAttribute);

    /**
     * 唯一校验
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 结果
     */
    public int checkPmsProductAttributeUnique(PmsProductAttribute pmsProductAttribute);

    /**
     * 新增商品属性＆参数
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 结果
     */
    public int insertPmsProductAttribute(PmsProductAttribute pmsProductAttribute);

    /**
     * 修改商品属性＆参数
     *
     * @param pmsProductAttribute 商品属性＆参数
     * @return 结果
     */
    public int updatePmsProductAttribute(PmsProductAttribute pmsProductAttribute);

    /**
     * 删除商品属性＆参数
     *
     * @param id 商品属性＆参数ID
     * @return 结果
     */
    public int deletePmsProductAttributeById(Long id);

    /**
     * 批量删除商品属性＆参数
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsProductAttributeByIds(String[] ids);

}
