package com.fante.project.business.cmsTopic.service;

import com.fante.project.business.cmsTopic.domain.CmsTopic;
import com.fante.project.business.cmsTopic.domain.CmsTopicDTO;

import java.util.List;

/**
 * 话题Service接口
 *
 * @author fante
 * @date 2020-03-18
 */
public interface ICmsTopicService {
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
     * 查询话题列表
     * @param cmsTopicDTO
     * @return
     */
    public List<CmsTopicDTO> selectJoinList(CmsTopicDTO cmsTopicDTO);

    /**
     * 前端话题展示
     * @return
     */
    public List<CmsTopicDTO> selectShowList();

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
    public String checkCmsTopicUnique(CmsTopic cmsTopic);

    /**
     * 新增话题
     *
     * @param cmsTopic 话题
     * @return 新增话题数量
     */
    public int insertCmsTopic(CmsTopic cmsTopic);

    /**
     * 修改话题
     *
     * @param cmsTopic 话题
     * @return 修改话题数量
     */
    public int updateCmsTopic(CmsTopic cmsTopic);

    /**
     * 批量删除话题
     *
     * @param ids 需要删除的数据ID
     * @return 删除话题数量
     */
    public int deleteCmsTopicByIds(String ids);

    /**
     * 删除话题信息
     *
     * @param id 话题ID
     * @return 删除话题数量
     */
    public int deleteCmsTopicById(Long id);

    /**
     * 查询指定分类话题数量
     * @param categoryId
     * @return
     */
    public int countCmsTopicByCategoryId(Long categoryId);


    /**
     * 阅读话题
     * @param id
     * @return
     */
    public int readTopicProcess(Long id);

    }
