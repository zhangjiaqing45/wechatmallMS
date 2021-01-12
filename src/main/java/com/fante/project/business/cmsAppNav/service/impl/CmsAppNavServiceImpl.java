package com.fante.project.business.cmsAppNav.service.impl;

import java.util.List;

import com.fante.common.business.enums.CmsAppNavConst;
import com.fante.common.business.enums.CommonUse;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.cmsAppNav.mapper.CmsAppNavMapper;
import com.fante.project.business.cmsAppNav.domain.CmsAppNav;
import com.fante.project.business.cmsAppNav.service.ICmsAppNavService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 前端导航管理Service业务层处理
 *
 * @author fante
 * @date 2020-04-21
 */
@Service
public class CmsAppNavServiceImpl implements ICmsAppNavService {

    private static Logger log = LoggerFactory.getLogger(CmsAppNavServiceImpl.class);

    @Autowired
    private CmsAppNavMapper cmsAppNavMapper;

    /**
     * 查询前端导航管理
     *
     * @param id 前端导航管理ID
     * @return 前端导航管理
     */
    @Override
    public CmsAppNav selectCmsAppNavById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return cmsAppNavMapper.selectCmsAppNavById(id);
    }

    /**
     * 查询前端导航管理列表
     *
     * @param cmsAppNav 前端导航管理
     * @return 前端导航管理集合
     */
    @Override
    public List<CmsAppNav> selectCmsAppNavList(CmsAppNav cmsAppNav) {
        return cmsAppNavMapper.selectCmsAppNavList(cmsAppNav);
    }

    /**
     * 查询前端导航管理数量
     *
     * @param cmsAppNav 前端导航管理
     * @return 结果
     */
    @Override
    public int countCmsAppNav(CmsAppNav cmsAppNav){
        return cmsAppNavMapper.countCmsAppNav(cmsAppNav);
    }

    /**
     * 唯一校验
     *
     * @param cmsAppNav 前端导航管理
     * @return 结果
     */
    @Override
    public String checkCmsAppNavUnique(CmsAppNav cmsAppNav) {
        return cmsAppNavMapper.checkCmsAppNavUnique(cmsAppNav) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增前端导航管理
     *
     * @param cmsAppNav 前端导航管理
     * @return 新增前端导航管理数量
     */
    @Override
    public int insertCmsAppNav(CmsAppNav cmsAppNav) {
        if (StringUtils.isBlank(cmsAppNav.getCreateBy())) {
            cmsAppNav.setCreateBy(ShiroUtils.getLoginName());
        }
        cmsAppNav.setCreateTime(DateUtils.getNowDate());
        return cmsAppNavMapper.insertCmsAppNav(cmsAppNav);
    }

    /**
     * 修改前端导航管理
     *
     * @param cmsAppNav 前端导航管理
     * @return 修改前端导航管理数量
     */
    @Override
    public int updateCmsAppNav(CmsAppNav cmsAppNav) {
        if (StringUtils.isBlank(cmsAppNav.getUpdateBy())) {
            cmsAppNav.setUpdateBy(ShiroUtils.getLoginName());
        }
        cmsAppNav.setUpdateTime(DateUtils.getNowDate());
        return cmsAppNavMapper.updateCmsAppNav(cmsAppNav);
    }

    /**
     * 删除前端导航管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除前端导航管理数量
     */
    @Override
    public int deleteCmsAppNavByIds(String ids) {
        return cmsAppNavMapper.deleteCmsAppNavByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除前端导航管理信息
     *
     * @param id 前端导航管理ID
     * @return 删除前端导航管理数量
     */
    @Override
    public int deleteCmsAppNavById(Long id) {
        return cmsAppNavMapper.deleteCmsAppNavById(id);
    }

    /**
     * 更改状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int changeStatus(Long id, String status) {
        CmsAppNav nav = new CmsAppNav();
        nav.setId(id);
        nav.setStatus(status);
        return updateCmsAppNav(nav);
    }

    /**
     * 更改排序
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int changeSort(Long id, Long sort) {
        CmsAppNav nav = new CmsAppNav();
        nav.setId(id);
        nav.setSort(sort);
        return updateCmsAppNav(nav);
    }

    /**
     * APP首页导航显示
     * @return
     */
    @Override
    public List<CmsAppNav> selectAvailableNavForAppIndex() {
        return cmsAppNavMapper.selectAvailableNav(CommonUse.Status.ENABLE.getType(), CmsAppNavConst.NavShowType.APP_SHOW);
    }

    /**
     * 店铺首页导航显示
     * @return
     */
    @Override
    public List<CmsAppNav> selectAvailableNavForShopIndex() {
        return cmsAppNavMapper.selectAvailableNav(CommonUse.Status.ENABLE.getType(), CmsAppNavConst.NavShowType.SHOP_SHOW);
    }
}
