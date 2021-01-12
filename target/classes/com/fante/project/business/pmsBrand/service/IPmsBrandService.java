package com.fante.project.business.pmsBrand.service;

import com.fante.project.business.pmsBrand.domain.PmsBrand;
import java.util.List;

/**
 * 品牌Service接口
 *
 * @author fante
 * @date 2020-03-09
 */
public interface IPmsBrandService {
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
     * 新增品牌
     *
     * @param pmsBrand 品牌
     * @return 新增品牌数量
     */
    public int insertPmsBrand(PmsBrand pmsBrand);

    /**
     * 修改品牌
     *
     * @param pmsBrand 品牌
     * @return 修改品牌数量
     */
    public int updatePmsBrand(PmsBrand pmsBrand);

    /**
     * 批量删除品牌
     *
     * @param ids 需要删除的数据ID
     * @return 删除品牌数量
     */
    public int deletePmsBrandByIds(String ids);

    /**
     * 删除品牌信息
     *
     * @param id 品牌ID
     * @return 删除品牌数量
     */
    public int deletePmsBrandById(Long id);
    /**
     * 唯一校验
     *
     * @param pmsBrand 品牌
     * @return 结果
     */
    public String checkPmsBrandUnique(PmsBrand pmsBrand);
 }
