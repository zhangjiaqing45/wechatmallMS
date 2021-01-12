package com.fante.project.business.cmsTopic.service.impl;

import java.util.List;

import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.cmsTopic.domain.CmsTopicDTO;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.cmsTopic.mapper.CmsTopicMapper;
import com.fante.project.business.cmsTopic.domain.CmsTopic;
import com.fante.project.business.cmsTopic.service.ICmsTopicService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 话题Service业务层处理
 *
 * @author fante
 * @date 2020-03-18
 */
@Service
public class CmsTopicServiceImpl implements ICmsTopicService {

    private static Logger log = LoggerFactory.getLogger(CmsTopicServiceImpl.class);

    @Autowired
    private CmsTopicMapper cmsTopicMapper;

    /**
     * 查询话题
     *
     * @param id 话题ID
     * @return 话题
     */
    @Override
    public CmsTopic selectCmsTopicById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return cmsTopicMapper.selectCmsTopicById(id);
    }

    /**
     * 查询话题列表
     *
     * @param cmsTopic 话题
     * @return 话题集合
     */
    @Override
    public List<CmsTopic> selectCmsTopicList(CmsTopic cmsTopic) {
        return cmsTopicMapper.selectCmsTopicList(cmsTopic);
    }

    /**
     * 查询话题列表
     * @param cmsTopicDTO
     * @return
     */
    @Override
    public List<CmsTopicDTO> selectJoinList(CmsTopicDTO cmsTopicDTO) {
        return cmsTopicMapper.selectJoinList(cmsTopicDTO);
    }

    /**
     * 前端话题展示
     * @return
     */
    @Override
    public List<CmsTopicDTO> selectShowList() {
        return cmsTopicMapper.selectShowList(Constants.ENABLE, Constants.ENABLE);
    }

    /**
     * 查询话题数量
     *
     * @param cmsTopic 话题
     * @return 结果
     */
    @Override
    public int countCmsTopic(CmsTopic cmsTopic){
        return cmsTopicMapper.countCmsTopic(cmsTopic);
    }

    /**
     * 唯一校验
     *
     * @param cmsTopic 话题
     * @return 结果
     */
    @Override
    public String checkCmsTopicUnique(CmsTopic cmsTopic) {
        return cmsTopicMapper.checkCmsTopicUnique(cmsTopic) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增话题
     *
     * @param cmsTopic 话题
     * @return 新增话题数量
     */
    @Override
    public int insertCmsTopic(CmsTopic cmsTopic) {
        if (StringUtils.isBlank(cmsTopic.getCreateBy())) {
            cmsTopic.setCreateBy(ShiroUtils.getLoginName());
        }
        cmsTopic.setCreateTime(DateUtils.getNowDate());
        return cmsTopicMapper.insertCmsTopic(cmsTopic);
    }

    /**
     * 修改话题
     *
     * @param cmsTopic 话题
     * @return 修改话题数量
     */
    @Override
    public int updateCmsTopic(CmsTopic cmsTopic) {
        if (StringUtils.isBlank(cmsTopic.getUpdateBy())) {
            cmsTopic.setUpdateBy(ShiroUtils.getLoginName());
        }
        cmsTopic.setUpdateTime(DateUtils.getNowDate());
        return cmsTopicMapper.updateCmsTopic(cmsTopic);
    }

    /**
     * 删除话题对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除话题数量
     */
    @Override
    public int deleteCmsTopicByIds(String ids) {
        return cmsTopicMapper.deleteCmsTopicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除话题信息
     *
     * @param id 话题ID
     * @return 删除话题数量
     */
    @Override
    public int deleteCmsTopicById(Long id) {
        return cmsTopicMapper.deleteCmsTopicById(id);
    }

    /**
     * 查询指定分类话题数量
     * @param categoryId
     * @return
     */
    @Override
    public int countCmsTopicByCategoryId(Long categoryId) {
        Checker.check(ObjectUtils.isEmpty(categoryId), "缺少话题分类标识");
        CmsTopic cmsTopic = new CmsTopic();
        cmsTopic.setCategoryId(categoryId);
        return countCmsTopic(cmsTopic);
    }

    /**
     * 阅读话题
     * @param id
     * @return
     */
    @Override
    public int readTopicProcess(Long id) {
        return cmsTopicMapper.readTopicProcess(id);
    }
}
