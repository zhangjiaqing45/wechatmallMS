package com.fante.project.business.pmsProductAttributeCategory.service.impl;

import java.util.List;

import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.pmsProductAttributeCategory.mapper.PmsProductAttributeCategoryMapper;
import com.fante.project.business.pmsProductAttributeCategory.domain.PmsProductAttributeCategory;
import com.fante.project.business.pmsProductAttributeCategory.service.IPmsProductAttributeCategoryService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 产品属性分类Service业务层处理
 *
 * @author fante
 * @date 2020-03-09
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements IPmsProductAttributeCategoryService {

    private static Logger log = LoggerFactory.getLogger(PmsProductAttributeCategoryServiceImpl.class);

    @Autowired
    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    /**
     * 查询产品属性分类
     *
     * @param id 产品属性分类ID
     * @return 产品属性分类
     */
    @Override
    public PmsProductAttributeCategory selectPmsProductAttributeCategoryById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsProductAttributeCategoryMapper.selectPmsProductAttributeCategoryById(id);
    }

    /**
     * 查询产品属性分类列表
     *
     *
     * @param pmsProductAttributeCategory 产品属性分类
     * @return 产品属性分类集合
     */
    @Override
    public List<PmsProductAttributeCategory> selectPmsProductAttributeCategoryList(PmsProductAttributeCategory pmsProductAttributeCategory) {
        return pmsProductAttributeCategoryMapper.selectPmsProductAttributeCategoryList(pmsProductAttributeCategory);
    }

    /**
     * 新增产品属性分类
     *
     * @param pmsProductAttributeCategory 产品属性分类
     * @return 新增产品属性分类数量
     */
    @Override
    public int insertPmsProductAttributeCategory(PmsProductAttributeCategory pmsProductAttributeCategory) {
        //检查入参
        Checker.check(StringUtils.isEmpty(pmsProductAttributeCategory.getName()),"产品类型分类名称为空");
        //设置当前的用户信息和店铺id
        User user = ShiroUtils.getSysUser();
        pmsProductAttributeCategory.setShopId(user.getDeptId());
        pmsProductAttributeCategory.setCreateBy(user.getLoginName());
        pmsProductAttributeCategory.setCreateTime(DateUtils.getNowDate());
        return pmsProductAttributeCategoryMapper.insertPmsProductAttributeCategory(pmsProductAttributeCategory);
    }

    /**
     * 修改产品属性分类
     *
     * @param pmsProductAttributeCategory 产品属性分类
     * @return 修改产品属性分类数量
     */
    @Override
    public int updatePmsProductAttributeCategory(PmsProductAttributeCategory pmsProductAttributeCategory) {
        //设置当前的用户信息和店铺id
        pmsProductAttributeCategory.setUpdateBy(ShiroUtils.getLoginName());
        pmsProductAttributeCategory.setUpdateTime(DateUtils.getNowDate());
        return pmsProductAttributeCategoryMapper.updatePmsProductAttributeCategory(pmsProductAttributeCategory);
    }

    /**
     * 删除产品属性分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除产品属性分类数量
     */
    @Override
    public int deletePmsProductAttributeCategoryByIds(String ids) {
        return pmsProductAttributeCategoryMapper.deletePmsProductAttributeCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品属性分类信息
     *
     * @param id 产品属性分类ID
     * @return 删除产品属性分类数量
     */
    @Override
    public int deletePmsProductAttributeCategoryById(Long id) {
        return pmsProductAttributeCategoryMapper.deletePmsProductAttributeCategoryById(id);
    }

    /**
     * 唯一校验
     *
     * @param pmsProductAttributeCategory 产品属性分类
     * @return 结果
     */
    @Override
    public String checkPmsProductAttributeCategoryUnique(PmsProductAttributeCategory pmsProductAttributeCategory) {
        return pmsProductAttributeCategoryMapper.checkPmsProductAttributeCategoryUnique(pmsProductAttributeCategory) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }
}
