package com.fante.project.business.pmsIntegralProductCategory.service.impl;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
import com.fante.project.business.pmsIntegralProduct.service.IPmsIntegralProductService;
import com.fante.project.business.pmsIntegralProductCategory.domain.PmsIntegralProductCategory;
import com.fante.project.business.pmsIntegralProductCategory.mapper.PmsIntegralProductCategoryMapper;
import com.fante.project.business.pmsIntegralProductCategory.service.IPmsIntegralProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 积分商品分类Service业务层处理
 *
 * @author fante
 * @date 2020-03-18
 */
@Service
public class PmsIntegralProductCategoryServiceImpl implements IPmsIntegralProductCategoryService {

    private static Logger log = LoggerFactory.getLogger(PmsIntegralProductCategoryServiceImpl.class);

    @Autowired
    private PmsIntegralProductCategoryMapper pmsIntegralProductCategoryMapper;
    @Autowired
    private IPmsIntegralProductService pmsIntegralProductService;

    /**
     * 查询积分商品分类
     *
     * @param id 积分商品分类ID
     * @return 积分商品分类
     */
    @Override
    public PmsIntegralProductCategory selectPmsIntegralProductCategoryById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsIntegralProductCategoryMapper.selectPmsIntegralProductCategoryById(id);
    }

    /**
     * 查询积分商品分类列表
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 积分商品分类集合
     */
    @Override
    public List<PmsIntegralProductCategory> selectPmsIntegralProductCategoryList(PmsIntegralProductCategory pmsIntegralProductCategory) {
        return pmsIntegralProductCategoryMapper.selectPmsIntegralProductCategoryList(pmsIntegralProductCategory);
    }

    @Override
    public List<PmsIntegralProductCategory> selectJoinList(PmsIntegralProductCategory pmsIntegralProductCategory) {
        return pmsIntegralProductCategoryMapper.selectJoinList(pmsIntegralProductCategory);
    }

    /**
     * 查询积分商品分类数量
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 结果
     */
    @Override
    public int countPmsIntegralProductCategory(PmsIntegralProductCategory pmsIntegralProductCategory){
        return pmsIntegralProductCategoryMapper.countPmsIntegralProductCategory(pmsIntegralProductCategory);
    }

    /**
     * 唯一校验
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 结果
     */
    @Override
    public String checkPmsIntegralProductCategoryUnique(PmsIntegralProductCategory pmsIntegralProductCategory) {
        return pmsIntegralProductCategoryMapper.checkPmsIntegralProductCategoryUnique(pmsIntegralProductCategory) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增积分商品分类
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 新增积分商品分类数量
     */
    @Override
    public int insertPmsIntegralProductCategory(PmsIntegralProductCategory pmsIntegralProductCategory) {
        if (StringUtils.isBlank(pmsIntegralProductCategory.getCreateBy())) {
            pmsIntegralProductCategory.setCreateBy(ShiroUtils.getLoginName());
        }
        pmsIntegralProductCategory.setCreateTime(DateUtils.getNowDate());
        return pmsIntegralProductCategoryMapper.insertPmsIntegralProductCategory(pmsIntegralProductCategory);
    }

    /**
     * 修改积分商品分类
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 修改积分商品分类数量
     */
    @Override
    public int updatePmsIntegralProductCategory(PmsIntegralProductCategory pmsIntegralProductCategory) {
        if (StringUtils.isBlank(pmsIntegralProductCategory.getUpdateBy())) {
            pmsIntegralProductCategory.setUpdateBy(ShiroUtils.getLoginName());
        }
        pmsIntegralProductCategory.setUpdateTime(DateUtils.getNowDate());
        return pmsIntegralProductCategoryMapper.updatePmsIntegralProductCategory(pmsIntegralProductCategory);
    }

    /**
     * 删除积分商品分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除积分商品分类数量
     */
    @Override
    public int deletePmsIntegralProductCategoryByIds(String ids) {
        return pmsIntegralProductCategoryMapper.deletePmsIntegralProductCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除积分商品分类信息
     *
     * @param id 积分商品分类ID
     * @return 删除积分商品分类数量
     */
    @Override
    public int deletePmsIntegralProductCategoryById(Long id) {
        int count = countPmsIntegralProductByCategoryById(id);
        Checker.check(count > 0, "该分类下存在积分商品，暂时无法删除");
        return pmsIntegralProductCategoryMapper.deletePmsIntegralProductCategoryById(id);
    }

    /**
     * 统计指定分类下商品数量
     * @param categoryById
     * @return
     */
    private int countPmsIntegralProductByCategoryById(Long categoryById) {
        Checker.check(ObjectUtils.isEmpty(categoryById), "缺少积分商品分类标识");
        PmsIntegralProduct pmsIntegralProduct = new PmsIntegralProduct();
        pmsIntegralProduct.setProductCategoryId(categoryById);
        return pmsIntegralProductService.countPmsIntegralProduct(pmsIntegralProduct);
    }
    /**
     * (app)积分商品分类查询
     * @return
     */
    @Override
    public List<PmsIntegralProductCategory> getPmsIntegralProductCategoryForDisplay() {
        return pmsIntegralProductCategoryMapper.getPmsIntegralProductCategoryForDisplay(CommonUse.Status.ENABLE.getType());
    }
}
