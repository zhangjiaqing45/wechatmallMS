package com.fante.project.mapperBase;

import com.fante.project.business.umsDistribution.domain.UmsDistribution;
import java.util.List;

/**
 * 分销比例商品角色关系Mapper基础接口
 *
 * @author fante
 * @date 2020-04-30
 */
public interface UmsDistributionMapperBase {
    /**
     * 查询分销比例商品角色关系
     *
     * @param id 分销比例商品角色关系ID
     * @return 分销比例商品角色关系
     */
    public UmsDistribution selectUmsDistributionById(Long id);

    /**
     * 查询分销比例商品角色关系列表
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 分销比例商品角色关系集合
     */
    public List<UmsDistribution> selectUmsDistributionList(UmsDistribution umsDistribution);

    /**
     * 查询分销比例商品角色关系数量
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 结果
     */
    public int countUmsDistribution(UmsDistribution umsDistribution);

    /**
     * 唯一校验
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 结果
     */
    public int checkUmsDistributionUnique(UmsDistribution umsDistribution);

    /**
     * 新增分销比例商品角色关系
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 结果
     */
    public int insertUmsDistribution(UmsDistribution umsDistribution);

    /**
     * 修改分销比例商品角色关系
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 结果
     */
    public int updateUmsDistribution(UmsDistribution umsDistribution);

    /**
     * 删除分销比例商品角色关系
     *
     * @param id 分销比例商品角色关系ID
     * @return 结果
     */
    public int deleteUmsDistributionById(Long id);

    /**
     * 批量删除分销比例商品角色关系
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUmsDistributionByIds(String[] ids);

}
