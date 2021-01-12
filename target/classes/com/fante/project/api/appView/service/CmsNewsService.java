package com.fante.project.api.appView.service;

import com.fante.project.business.cmsTopic.domain.CmsTopic;
import com.fante.project.business.cmsTopic.domain.CmsTopicDTO;
import com.fante.project.business.cmsTopic.service.ICmsTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/21 17:20
 * @Author: Mr.Z
 * @Description: 前端新闻资讯相关处理服务
 */
@Service
public class CmsNewsService {

    private static Logger log = LoggerFactory.getLogger(CmsHomeService.class);

    @Autowired
    private ICmsTopicService cmsTopicService;

    /**
     * 新闻咨询列表
     * @return
     */
    public List<CmsTopicDTO> topics() {
        return cmsTopicService.selectShowList();
    }

    /**
     * 新闻咨询详情
     * @param newsId
     * @return
     */
    public CmsTopic topicDetail(Long newsId) {
        if (ObjectUtils.isEmpty(newsId)) {
            return null;
        }
        cmsTopicService.readTopicProcess(newsId);
        return cmsTopicService.selectCmsTopicById(newsId);
    }

}
