package com.fante.project.business.bizDescription.mapper;

import com.fante.project.business.bizDescription.domain.BizDescription;
import java.util.List;

/**
 * 文档说明Mapper接口
 * 
 * @author fante
 * @date 2020-01-17
 */
public interface BizDescriptionMapper 
{
    /**
     * 查询文档说明
     * 
     * @param id 文档说明ID
     * @return 文档说明
     */
    public BizDescription selectBizDescriptionById(Long id);

    /**
     * 查询文档说明列表
     * 
     * @param bizDescription 文档说明
     * @return 文档说明集合
     */
    public List<BizDescription> selectBizDescriptionList(BizDescription bizDescription);

    /**
     * 新增文档说明
     * 
     * @param bizDescription 文档说明
     * @return 结果
     */
    public int insertBizDescription(BizDescription bizDescription);

    /**
     * 修改文档说明
     * 
     * @param bizDescription 文档说明
     * @return 结果
     */
    public int updateBizDescription(BizDescription bizDescription);

    /**
     * 删除文档说明
     * 
     * @param id 文档说明ID
     * @return 结果
     */
    public int deleteBizDescriptionById(Long id);

    /**
     * 批量删除文档说明
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizDescriptionByIds(String[] ids);
}
