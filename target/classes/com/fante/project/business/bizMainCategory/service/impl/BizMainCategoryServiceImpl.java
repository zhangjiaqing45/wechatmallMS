package com.fante.project.business.bizMainCategory.service.impl;

import com.fante.common.constant.Constants;
import com.fante.common.constant.UserConstants;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.bizMainCategory.domain.BizMainCategory;
import com.fante.project.business.bizMainCategory.mapper.BizMainCategoryMapper;
import com.fante.project.business.bizMainCategory.service.IBizMainCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 店铺主营类目Service业务层处理
 *
 * @author fante
 * @date 2020-03-10
 */
@Service
public class BizMainCategoryServiceImpl implements IBizMainCategoryService {

    private static Logger log = LoggerFactory.getLogger(BizMainCategoryServiceImpl.class);

    @Autowired
    private BizMainCategoryMapper bizMainCategoryMapper;

    /**
     * 查询店铺主营类目
     *
     * @param id 店铺主营类目ID
     * @return 店铺主营类目
     */
    @Override
    public BizMainCategory selectBizMainCategoryById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return bizMainCategoryMapper.selectBizMainCategoryById(id);
    }

    /**
     * 查询店铺主营类目列表
     *
     * @param bizMainCategory 店铺主营类目
     * @return 店铺主营类目集合
     */
    @Override
    public List<BizMainCategory> selectBizMainCategoryList(BizMainCategory bizMainCategory) {
        return bizMainCategoryMapper.selectBizMainCategoryList(bizMainCategory);
    }

    /**
     * 查询店铺主营类目数量
     *
     * @param bizMainCategory 店铺主营类目
     * @return 结果
     */
    @Override
    public int countBizMainCategory(BizMainCategory bizMainCategory){
        return bizMainCategoryMapper.countBizMainCategory(bizMainCategory);
    }

    /**
     * 新增店铺主营类目
     *
     * @param bizMainCategory 店铺主营类目
     * @return 新增店铺主营类目数量
     */
    @Override
    public int insertBizMainCategory(BizMainCategory bizMainCategory) {
        if (StringUtils.isBlank(bizMainCategory.getSubmitInfo())) {
            bizMainCategory.setSubmitNum(Constants.ZERO_STR);
        }
        if (StringUtils.isBlank(bizMainCategory.getCreateBy())) {
            bizMainCategory.setCreateBy(ShiroUtils.getUserName());
        }
        bizMainCategory.setCreateTime(DateUtils.getNowDate());
        return bizMainCategoryMapper.insertBizMainCategory(bizMainCategory);
    }

    /**
     * 修改店铺主营类目
     *
     * @param bizMainCategory 店铺主营类目
     * @return 修改店铺主营类目数量
     */
    @Override
    public int updateBizMainCategory(BizMainCategory bizMainCategory) {
        if (StringUtils.isBlank(bizMainCategory.getSubmitInfo())) {
            bizMainCategory.setSubmitNum(Constants.ZERO_STR);
        }
        if (StringUtils.isBlank(bizMainCategory.getUpdateBy())) {
            bizMainCategory.setUpdateBy(ShiroUtils.getUserName());
        }
        bizMainCategory.setUpdateTime(DateUtils.getNowDate());
        return bizMainCategoryMapper.updateBizMainCategory(bizMainCategory);
    }

    /**
     * 删除店铺主营类目对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺主营类目数量
     */
    @Override
    public int deleteBizMainCategoryByIds(String ids) {
        return bizMainCategoryMapper.deleteBizMainCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除店铺主营类目信息
     *
     * @param id 店铺主营类目ID
     * @return 删除店铺主营类目数量
     */
    @Override
    public int deleteBizMainCategoryById(Long id) {
        return bizMainCategoryMapper.deleteBizMainCategoryById(id);
    }

    /**
     * 校验主营类目名称是否唯一
     * @param bizMainCategory
     * @return
     */
    @Override
    public String checkCategoryUnique(BizMainCategory bizMainCategory) {
        if (bizMainCategoryMapper.checkCategoryUnique(bizMainCategory) > 0) {
            return Constants.NOT_UNIQUE;
        }
        return Constants.UNIQUE;
    }
}
