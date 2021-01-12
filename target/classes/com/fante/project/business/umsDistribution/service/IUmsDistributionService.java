package com.fante.project.business.umsDistribution.service;

import com.fante.project.api.omsOrderProcess.domain.OmsOrderCommissionDTO;
import com.fante.project.business.umsDistribution.domain.UmsDistribution;
import com.fante.project.business.umsDistribution.domain.UmsDistributionResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * 分销比例商品角色关系Service接口
 *
 * @author fante
 * @date 2020-04-30
 */
public interface IUmsDistributionService {
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
    public String checkUmsDistributionUnique(UmsDistribution umsDistribution);

    /**
     * 新增分销比例商品角色关系
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 新增分销比例商品角色关系数量
     */
    public int insertUmsDistribution(UmsDistribution umsDistribution);

    /**
     * 修改分销比例商品角色关系
     *
     * @param umsDistribution 分销比例商品角色关系
     * @return 修改分销比例商品角色关系数量
     */
    public int updateUmsDistribution(UmsDistribution umsDistribution);

    /**
     * 批量删除分销比例商品角色关系
     *
     * @param ids 需要删除的数据ID
     * @return 删除分销比例商品角色关系数量
     */
    public int deleteUmsDistributionByIds(String ids);

    /**
     * 删除分销比例商品角色关系信息
     *
     * @param id 分销比例商品角色关系ID
     * @return 删除分销比例商品角色关系数量
     */
    public int deleteUmsDistributionById(Long id);

    /**
     * 根据商品id查询该商品sku 到各个分销金
     * @param id
     * @return
     */
    List<UmsDistributionResult> selectUmsDistributionByProudctId(Long id);

    /**
     * 根据获取分销金额
     * @param itemIds
     * @param memberId
     * @return
     */
    List<OmsOrderCommissionDTO>  getCommissionByOrderItemIds(List<Long> itemIds, Long memberId);

    /**
     * 根据订单详情获取单个分销金额
     * @param orderItemId
     * @param memberId
     * @return
     */
    public BigDecimal getCommissionByOrderItem( Long orderItemId, Long memberId);
}
