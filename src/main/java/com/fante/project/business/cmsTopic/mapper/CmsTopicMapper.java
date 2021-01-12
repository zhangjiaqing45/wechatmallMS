package com.fante.project.business.cmsTopic.mapper;

import com.fante.project.business.cmsTopic.domain.CmsTopicDTO;
import com.fante.project.mapperBase.CmsTopicMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 话题Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-18
 */

public interface CmsTopicMapper extends CmsTopicMapperBase {

    /**
     * 查询话题信息
     * @param cmsTopicDTO
     * @return
     */
    public List<CmsTopicDTO> selectJoinList(CmsTopicDTO cmsTopicDTO);

    /**
     * 前端话题展示
     * @param cs
     * @param ts
     * @return
     */
    public List<CmsTopicDTO> selectShowList(@Param("cs") String cs, @Param("ts") String ts);

    /**
     * 阅读话题
     * @param id
     * @return
     */
    public int readTopicProcess(@Param("id") Long id);
}
