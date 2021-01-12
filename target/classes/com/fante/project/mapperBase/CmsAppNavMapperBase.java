package com.fante.project.mapperBase;

import com.fante.project.business.cmsAppNav.domain.CmsAppNav;
import java.util.List;

/**
 * 前端导航管理Mapper基础接口
 *
 * @author fante
 * @date 2020-04-21
 */
public interface CmsAppNavMapperBase {
    /**
     * 查询前端导航管理
     *
     * @param id 前端导航管理ID
     * @return 前端导航管理
     */
    public CmsAppNav selectCmsAppNavById(Long id);

    /**
     * 查询前端导航管理列表
     *
     * @param cmsAppNav 前端导航管理
     * @return 前端导航管理集合
     */
    public List<CmsAppNav> selectCmsAppNavList(CmsAppNav cmsAppNav);

    /**
     * 查询前端导航管理数量
     *
     * @param cmsAppNav 前端导航管理
     * @return 结果
     */
    public int countCmsAppNav(CmsAppNav cmsAppNav);

    /**
     * 唯一校验
     *
     * @param cmsAppNav 前端导航管理
     * @return 结果
     */
    public int checkCmsAppNavUnique(CmsAppNav cmsAppNav);

    /**
     * 新增前端导航管理
     *
     * @param cmsAppNav 前端导航管理
     * @return 结果
     */
    public int insertCmsAppNav(CmsAppNav cmsAppNav);

    /**
     * 修改前端导航管理
     *
     * @param cmsAppNav 前端导航管理
     * @return 结果
     */
    public int updateCmsAppNav(CmsAppNav cmsAppNav);

    /**
     * 删除前端导航管理
     *
     * @param id 前端导航管理ID
     * @return 结果
     */
    public int deleteCmsAppNavById(Long id);

    /**
     * 批量删除前端导航管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmsAppNavByIds(String[] ids);

}
