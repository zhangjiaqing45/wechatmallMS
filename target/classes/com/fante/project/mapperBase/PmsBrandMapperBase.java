package com.fante.project.mapperBase;

import com.fante.project.business.pmsBrand.domain.PmsBrand;
import java.util.List;

/**
 * 品牌Mapper基础接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface PmsBrandMapperBase {
    /**
     * 查询品牌
     *
     * @param id 品牌ID
     * @return 品牌
     */
    public PmsBrand selectPmsBrandById(Long id);

    /**
     * 查询品牌列表
     *
     * @param pmsBrand 品牌
     * @return 品牌集合
     */
    public List<PmsBrand> selectPmsBrandList(PmsBrand pmsBrand);

    /**
     * 查询品牌数量
     *
     * @param pmsBrand 品牌
     * @return 结果
     */
    public int countPmsBrand(PmsBrand pmsBrand);

    /**
     * 唯一校验
     *
     * @param pmsBrand 品牌
     * @return 结果
     */
    public int checkPmsBrandUnique(PmsBrand pmsBrand);

    /**
     * 新增品牌
     *
     * @param pmsBrand 品牌
     * @return 结果
     */
    public int insertPmsBrand(PmsBrand pmsBrand);

    /**
     * 修改品牌
     *
     * @param pmsBrand 品牌
     * @return 结果
     */
    public int updatePmsBrand(PmsBrand pmsBrand);

    /**
     * 删除品牌
     *
     * @param id 品牌ID
     * @return 结果
     */
    public int deletePmsBrandById(Long id);

    /**
     * 批量删除品牌
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsBrandByIds(String[] ids);

}
