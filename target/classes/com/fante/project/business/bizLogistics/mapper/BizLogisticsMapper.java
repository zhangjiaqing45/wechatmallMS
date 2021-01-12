package com.fante.project.business.bizLogistics.mapper;

import com.fante.project.business.bizLogistics.domain.BizLogistics;
import java.util.List;

/**
 * 物流信息Mapper接口
 * 
 * @author fante
 * @date 2020-02-06
 */
public interface BizLogisticsMapper 
{
    /**
     * 查询物流信息
     * 
     * @param id 物流信息ID
     * @return 物流信息
     */
    public BizLogistics selectBizLogisticsById(Long id);

    /**
     * 查询物流信息
     *
     * @param number 单号
     * @return 物流信息
     */
    public BizLogistics queryByNumber(String number);

    /**
     * 查询物流信息列表
     * 
     * @param bizLogistics 物流信息
     * @return 物流信息集合
     */
    public List<BizLogistics> selectBizLogisticsList(BizLogistics bizLogistics);

    /**
     * 新增物流信息
     * 
     * @param bizLogistics 物流信息
     * @return 结果
     */
    public int insertBizLogistics(BizLogistics bizLogistics);

    /**
     * 修改物流信息
     * 
     * @param bizLogistics 物流信息
     * @return 结果
     */
    public int updateBizLogistics(BizLogistics bizLogistics);

    /**
     * 删除物流信息
     * 
     * @param id 物流信息ID
     * @return 结果
     */
    public int deleteBizLogisticsById(Long id);

    /**
     * 批量删除物流信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizLogisticsByIds(String[] ids);
}
