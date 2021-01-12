package com.fante.project.business.pmsShopCategoryRelation.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.mapper.PmsProductMapper;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsProduct.service.impl.PmsProductServiceImpl;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.mapper.PmsProductCategoryMapper;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationParam;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationResult;
import com.fante.project.system.user.domain.User;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.pmsShopCategoryRelation.mapper.PmsShopCategoryRelationMapper;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelation;
import com.fante.project.business.pmsShopCategoryRelation.service.IPmsShopCategoryRelationService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 店铺从平台选择的分类Service业务层处理
 *
 * @author fante
 * @date 2020-03-10
 */
@Service
public class PmsShopCategoryRelationServiceImpl implements IPmsShopCategoryRelationService {

    private static Logger log = LoggerFactory.getLogger(PmsShopCategoryRelationServiceImpl.class);

    @Autowired
    private PmsShopCategoryRelationMapper pmsShopCategoryRelationMapper;

    @Autowired
    private IPmsProductService iPmsProductService;
    /**
     * 查询店铺从平台选择的分类
     *
     * @param id 店铺从平台选择的分类ID
     * @return 店铺从平台选择的分类
     */
    @Override
    public PmsShopCategoryRelation selectPmsShopCategoryRelationById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsShopCategoryRelationMapper.selectPmsShopCategoryRelationById(id);
    }
    /**
     * 查询店铺从平台选择的分类关系列表
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 店铺从平台选择的分类关系集合
     */
    @Override
    public List<PmsShopCategoryRelation> selectPmsShopCategoryRelationList(PmsShopCategoryRelation pmsShopCategoryRelation) {
        return pmsShopCategoryRelationMapper.selectPmsShopCategoryRelationList(pmsShopCategoryRelation);
    }

    /**
     * 新增店铺从平台选择的分类(废弃)
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 新增店铺从平台选择的分类数量
     */
    @Override
    public int insertPmsShopCategoryRelation(PmsShopCategoryRelation pmsShopCategoryRelation) {
        pmsShopCategoryRelation.setCreateTime(DateUtils.getNowDate());
        return pmsShopCategoryRelationMapper.insertPmsShopCategoryRelation(pmsShopCategoryRelation);
    }

    /**
     * 修改店铺从平台选择的分类
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 修改店铺从平台选择的分类数量
     */
    @Override
    public int updatePmsShopCategoryRelation(PmsShopCategoryRelation pmsShopCategoryRelation) {
        //设置更新人的信息
        pmsShopCategoryRelation.setUpdateBy(ShiroUtils.getLoginName());
        pmsShopCategoryRelation.setUpdateTime(DateUtils.getNowDate());
        return pmsShopCategoryRelationMapper.updatePmsShopCategoryRelation(pmsShopCategoryRelation);
    }

    /**
     * 删除店铺从平台选择的分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺从平台选择的分类数量
     */
    @Override
    public int deletePmsShopCategoryRelationByIds(String ids) {
        return pmsShopCategoryRelationMapper.deletePmsShopCategoryRelationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除店铺从平台选择的分类信息
     *
     * @param strId 店铺从平台选择的分类ID
     * @return 删除店铺从平台选择的分类数量
     */
    @Override
    public int deletePmsShopCategoryRelationById(String strId) {
        //校验参数,不为空且是数字
        Checker.checkOp(StringUtils.isNotEmpty(strId)&&strId.matches("\\d+"),"店铺删除的商品分类id异常:参数strId="+strId);
        Long id = Long.parseLong(strId);
        PmsShopCategoryRelation relation = pmsShopCategoryRelationMapper.selectPmsShopCategoryRelationById(id);
        //如果id查不到关系表则返回删除失败
        Checker.check(ObjectUtils.isEmpty(relation),"您要删除的分类不存在！");
        //声明查询条件
        PmsProduct product = new PmsProduct();
        //设置店铺
        product.setShopId(ShiroUtils.getSysUser().getDeptId());
        //设置分类id
        product.setProductCategoryId(relation.getProductCategoryId());
        //设置商品状态未删除
        product.setDelFlag(Constants.ENABLE);
        //查询该店下该分类商品的数量
        Checker.check(iPmsProductService.countPmsProduct(product)>0," 该分类下存在商品不允许删除！");
        //真实删除
        return pmsShopCategoryRelationMapper.deletePmsShopCategoryRelationById(id);
    }
    /**
     * 查询店铺从平台选择的分类列表
     * note: 需要去除掉平台删除的分类,但不去除平台导航禁用的分类
     * @return 店铺从平台选择的分类集合
     */
    @Override
    public List<PmsShopCategoryRelationResult> selectPmsShopCategoryList(PmsShopCategoryRelationParam pmsProductCategoryParam) {
        pmsProductCategoryParam.setShopId(ShiroUtils.getSysUser().getDeptId());
        return pmsShopCategoryRelationMapper.selectPmsShopCategoryList(pmsProductCategoryParam);
    }
    /**
     * 查询店铺下的所有商品分类
     * @return
     */
    @Override
    public List<PmsShopCategoryRelationResult> selectPmsCategoryListForAddPms() {
        return selectPmsShopCategoryList(new PmsShopCategoryRelationParam()).stream()
                .map(item->{
                    if(Objects.equals(item.getShopStatus(), CommonUse.Status.DISABLE.getType())){
                        item.setName(item.getName()+"   导航:"+CommonUse.Status.DISABLE.getDescribe());
                    }
                    return item;
                }).collect(Collectors.toList());
    }

    /**
     * 批量插入店铺选择的商品分类
     * @param ids
     * @return
     */
    @Override
    public int insertPmsShopCategoryRelationList(String ids) {
        //设置用户所在店铺 和 创建人姓名
        User user = ShiroUtils.getSysUser();
        //验证这些ids是否在商店列表中已经存在
        int count = pmsShopCategoryRelationMapper.countShopCategoryRelationByPmsCateId(user.getDeptId(), Convert.toStrArray(ids));
        Checker.check(count>0,"所选择的分类信息发生变动,请刷新后重新选择.");
        return pmsShopCategoryRelationMapper.insertPmsShopCategoryRelationByIds(user.getLoginName(),user.getDeptId(),Convert.toStrArray(ids));
    }

    /**
     * 通过关系表获取对应店铺的商品分类
     * @param id 店铺与商品分类关系表id
     * @return
     */
    @Override
    public PmsProductCategory selectPmsShopCategoryByRelationId(Long id) {
        return pmsShopCategoryRelationMapper.selectPmsShopCategoryByRelationId(id);
    }
    /**
     * 唯一校验
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 结果
     */
    @Override
    public String checkPmsShopCategoryRelationUnique(PmsShopCategoryRelation pmsShopCategoryRelation) {
        return pmsShopCategoryRelationMapper.checkPmsShopCategoryRelationUnique(pmsShopCategoryRelation) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }
}
