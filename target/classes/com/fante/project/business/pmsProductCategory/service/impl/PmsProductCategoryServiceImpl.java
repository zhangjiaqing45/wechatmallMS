package com.fante.project.business.pmsProductCategory.service.impl;

import java.util.List;
import java.util.Map;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategoryDetail;
import com.fante.project.business.pmsProductCategory.domain.PmsShopProductCategory;
import com.fante.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.pmsProductCategory.mapper.PmsProductCategoryMapper;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.service.IPmsProductCategoryService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 产品分类Service业务层处理
 *
 * @author fante
 * @date 2020-03-09
 */
@Service
public class PmsProductCategoryServiceImpl implements IPmsProductCategoryService {

    private static Logger log = LoggerFactory.getLogger(PmsProductCategoryServiceImpl.class);

    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;

    /**
     * 查询产品分类
     *
     * @param id 产品分类ID
     * @return 产品分类
     */
    @Override
    public PmsProductCategory selectPmsProductCategoryById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsProductCategoryMapper.selectPmsProductCategoryById(id);
    }

    /**
     * 查询产品分类列表
     *
     * @param pmsProductCategory 产品分类
     * @return 产品分类集合
     */
    @Override
    public List<PmsProductCategory> selectPmsProductCategoryList(PmsProductCategory pmsProductCategory) {
        return pmsProductCategoryMapper.selectPmsProductCategoryList(pmsProductCategory);
    }
    
    /**
     * 查询产品分类列表
     *
     * @param param 产品分类
     * @return 产品分类集合
     */
    @Override
    public List<PmsProductCategoryDetail> getPmsProductCategoryList(Map<String, Object> param) {
        return pmsProductCategoryMapper.getPmsProductCategoryList(param);
    }

    /**
     * 新增产品分类
     *
     * @param pmsProductCategory 产品分类
     * @return 新增产品分类数量
     */
    @Override
    public int insertPmsProductCategory(PmsProductCategory pmsProductCategory) {
        //验证参数
        Checker.check(StringUtils.isAnyBlank(pmsProductCategory.getName(),pmsProductCategory.getIcon())
                ,"添加的商品分类参数缺失！");
        //设置创建时间
        pmsProductCategory.setCreateTime(DateUtils.getNowDate());
        //设置当前用户所在店铺id
        pmsProductCategory.setShopId(ShiroUtils.getSysUser().getDeptId());
        //设置当前操作用户
        pmsProductCategory.setCreateBy(ShiroUtils.getLoginName());
        return pmsProductCategoryMapper.insertPmsProductCategory(pmsProductCategory);
    }

    /**
     * 修改产品分类
     *
     * @param pmsProductCategory 产品分类
     * @return 修改产品分类数量
     */
    @Override
    public int updatePmsProductCategory(PmsProductCategory pmsProductCategory) {
        //设置更新时间
        pmsProductCategory.setUpdateTime(DateUtils.getNowDate());
        //设置当前操作用户
        pmsProductCategory.setUpdateBy(ShiroUtils.getLoginName());
        return pmsProductCategoryMapper.updatePmsProductCategory(pmsProductCategory);
    }

    /**
     * 删除产品分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除产品分类数量
     */
    @Override
    public int deletePmsProductCategoryByIds(String ids) {
        return pmsProductCategoryMapper.deleteCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品分类信息
     *
     * @param id 产品分类ID
     * @return 删除产品分类数量
     */
    @Override
    public int deletePmsProductCategoryById(Long id) {
        return pmsProductCategoryMapper.deletePmsProductCategoryById(id);
    }
    /**
     * 查询当前店铺未选商品分类
     *
     * @return 当前店铺未选商品分类列表
     */
    @Override
    public List<PmsProductCategory> selectableCategory(String name) {
        //获取当前用户所在店铺id
        User user = ShiroUtils.getSysUser();
        return pmsProductCategoryMapper.selectableCategory(name,user.getDeptId());
    }

    /**
     * 唯一校验
     *
     * @param pmsProductCategory 产品分类
     * @return 结果
     */
    @Override
    public String checkPmsProductCategoryUnique(PmsProductCategory pmsProductCategory) {
        return pmsProductCategoryMapper.checkPmsProductCategoryUnique(pmsProductCategory) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }
    /**
     * 通过id校验这个分类是否可用
     * @param id 产品分类id
     * @return 结果
     */
    @Override
    public PmsProductCategory validatePmsProductCategoryById(Long id) {
        Checker.check(ObjectUtils.isEmpty(id),"分类参数缺失");
        return pmsProductCategoryMapper.validatePmsProductCategoryById(id,ShiroUtils.getSysUser().getDeptId());
    }
    
    /**
     * 获取分类app店铺展示
     * @param shopId shopId
     * @return 结果
     */
    @Override
    public List<PmsShopProductCategory> getCategoryForNavInShop(Long shopId, Long level) {
        Checker.check(ObjectUtils.isEmpty(shopId),"店铺参数缺失");
        return pmsProductCategoryMapper.getCategoryForNavInShop(CommonUse.Status.ENABLE.getType(),
                CommonUse.Status.ENABLE.getType(),shopId, level);
    }
    /**
     * 获取所有分类app展示
     * @return 结果
     */
    @Override
    public List<PmsProductCategory> getCategoryForNavInPlatform(Long level) {
        return pmsProductCategoryMapper.getCategoryForNavInPlatform(CommonUse.Status.ENABLE.getType(), level);
    }

}
