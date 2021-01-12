package com.fante.project.business.bizDescription.service;

import com.fante.project.business.bizDescription.domain.BizDescription;
import java.util.List;
import com.fante.framework.web.domain.Ztree;

/**
 * 文档说明Service接口
 * 
 * @author fante
 * @date 2020-01-17
 */
public interface IBizDescriptionService 
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
     * 批量删除文档说明
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizDescriptionByIds(String ids);

    /**
     * 删除文档说明信息
     * 
     * @param id 文档说明ID
     * @return 结果
     */
    public int deleteBizDescriptionById(Long id);

    /**
     * 查询文档说明树列表
     * 
     * @return 所有文档说明信息
     */
    public List<Ztree> selectBizDescriptionTree();
}
