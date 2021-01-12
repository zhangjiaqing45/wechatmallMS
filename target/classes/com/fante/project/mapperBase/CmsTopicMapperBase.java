package com.fante.project.mapperBase;

import com.fante.project.business.cmsTopic.domain.CmsTopic;
import java.util.List;

/**
 * 话题Mapper基础接口
 *
 * @author fante
 * @date 2020-03-18
 */
public interface CmsTopicMapperBase {
    /**
     * 查询话题
     *
     * @param id 话题ID
     * @return 话题
     */
    public CmsTopic selectCmsTopicById(Long id);

    /**
     * 查询话题列表
     *
     * @param cmsTopic 话题
     * @return 话题集合
     */
    public List<CmsTopic> selectCmsTopicList(CmsTopic cmsTopic);

    /**
     * 查询话题数量
     *
     * @param cmsTopic 话题
     * @return 结果
     */
    public int countCmsTopic(CmsTopic cmsTopic);

    /**
     * 唯一校验
     *
     * @param cmsTopic 话题
     * @return 结果
     */
    public int checkCmsTopicUnique(CmsTopic cmsTopic);

    /**
     * 新增话题
     *
     * @param cmsTopic 话题
     * @return 结果
     */
    public int insertCmsTopic(CmsTopic cmsTopic);

    /**
     * 修改话题
     *
     * @param cmsTopic 话题
     * @return 结果
     */
    public int updateCmsTopic(CmsTopic cmsTopic);

    /**
     * 删除话题
     *
     * @param id 话题ID
     * @return 结果
     */
    public int deleteCmsTopicById(Long id);

    /**
     * 批量删除话题
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmsTopicByIds(String[] ids);

}
