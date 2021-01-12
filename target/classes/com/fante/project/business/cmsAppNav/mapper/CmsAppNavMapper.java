package com.fante.project.business.cmsAppNav.mapper;

import com.fante.project.business.cmsAppNav.domain.CmsAppNav;
import com.fante.project.mapperBase.CmsAppNavMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前端导航管理Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-21
 */

public interface CmsAppNavMapper extends CmsAppNavMapperBase {

    /**
     * 前端导航显示
     * @param status
     * @param showArr
     * @return
     */
    List<CmsAppNav> selectAvailableNav(@Param("status") String status, @Param("showArr") String[] showArr);

}
