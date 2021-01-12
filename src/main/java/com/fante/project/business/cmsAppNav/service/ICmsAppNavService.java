package com.fante.project.business.cmsAppNav.service;

import com.fante.project.business.cmsAppNav.domain.CmsAppNav;

import java.util.List;

/**
 * 前端导航管理Service接口
 *
 * @author fante
 * @date 2020-04-21
 */
public interface ICmsAppNavService {
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
    public String checkCmsAppNavUnique(CmsAppNav cmsAppNav);

    /**
     * 新增前端导航管理
     *
     * @param cmsAppNav 前端导航管理
     * @return 新增前端导航管理数量
     */
    public int insertCmsAppNav(CmsAppNav cmsAppNav);

    /**
     * 修改前端导航管理
     *
     * @param cmsAppNav 前端导航管理
     * @return 修改前端导航管理数量
     */
    public int updateCmsAppNav(CmsAppNav cmsAppNav);

    /**
     * 批量删除前端导航管理
     *
     * @param ids 需要删除的数据ID
     * @return 删除前端导航管理数量
     */
    public int deleteCmsAppNavByIds(String ids);

    /**
     * 删除前端导航管理信息
     *
     * @param id 前端导航管理ID
     * @return 删除前端导航管理数量
     */
    public int deleteCmsAppNavById(Long id);

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    public int changeStatus(Long id, String status);

    /**
     * 更改排序
     * @param id
     * @param sort
     * @return
     */
    public int changeSort(Long id, Long sort);

    /**
     * APP首页导航显示
     * @return
     */
    List<CmsAppNav> selectAvailableNavForAppIndex();

    /**
     * 店铺首页导航显示
     * @return
     */
    List<CmsAppNav> selectAvailableNavForShopIndex();

    }
