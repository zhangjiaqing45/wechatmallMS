package com.fante.project.business.pmsIntegralProduct.service.impl;

import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.business.enums.SmsCouponConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
import com.fante.project.business.pmsIntegralProduct.mapper.PmsIntegralProductMapper;
import com.fante.project.business.pmsIntegralProduct.service.IPmsIntegralProductService;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 积分商品Service业务层处理
 *
 * @author fante
 * @date 2020-03-19
 */
@Service
public class PmsIntegralProductServiceImpl implements IPmsIntegralProductService {

    private static Logger log = LoggerFactory.getLogger( PmsIntegralProductServiceImpl.class );

    @Autowired
    private PmsIntegralProductMapper pmsIntegralProductMapper;

    /**
     * 查询积分商品
     *
     * @param id 积分商品ID
     * @return 积分商品
     */
    @Override
    public PmsIntegralProduct selectPmsIntegralProductById(Long id) {
        if (ObjectUtils.isEmpty( id )) {
            return null;
        }
        return pmsIntegralProductMapper.selectPmsIntegralProductById( id );
    }

    /**
     * 查询积分商品列表
     *
     * @param pmsIntegralProduct 积分商品
     * @return 积分商品集合
     */
    @Override
    public List<PmsIntegralProduct> selectPmsIntegralProductList(PmsIntegralProduct pmsIntegralProduct) {
        return pmsIntegralProductMapper.selectPmsIntegralProductList( pmsIntegralProduct );
    }

    /**
     * 查询积分商品列表
     *
     * @param pmsIntegralProduct 积分商品
     * @return
     */
    @Override
    public List<PmsIntegralProduct> selectJoinList(PmsIntegralProduct pmsIntegralProduct) {
        return pmsIntegralProductMapper.selectJoinList( pmsIntegralProduct );
    }

    /**
     * 查询积分商品数量
     *
     * @param pmsIntegralProduct 积分商品
     * @return 结果
     */
    @Override
    public int countPmsIntegralProduct(PmsIntegralProduct pmsIntegralProduct) {
        return pmsIntegralProductMapper.countPmsIntegralProduct( pmsIntegralProduct );
    }

    /**
     * 唯一校验
     *
     * @param pmsIntegralProduct 积分商品
     * @return 结果
     */
    @Override
    public String checkPmsIntegralProductUnique(PmsIntegralProduct pmsIntegralProduct) {
        return pmsIntegralProductMapper.checkPmsIntegralProductUnique( pmsIntegralProduct ) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增积分商品
     *
     * @param pmsIntegralProduct 积分商品
     * @return 新增积分商品数量
     */
    @Override
    public int insertPmsIntegralProduct(PmsIntegralProduct pmsIntegralProduct) {
        if (StringUtils.isBlank( pmsIntegralProduct.getCreateBy() )) {
            pmsIntegralProduct.setCreateBy( ShiroUtils.getLoginName() );
        }
        pmsIntegralProduct.setProductSn( String.valueOf( IdGenerator.nextId() ) );
        pmsIntegralProduct.setVerifyStatus( AuditTypeEnum.SUCCESS.getType() );
        pmsIntegralProduct.setPublishStatus( ProductAttributeCategoryConst.publicStatusEnum.SOLDOUT.getType() );
        pmsIntegralProduct.setCreateTime( DateUtils.getNowDate() );
        return pmsIntegralProductMapper.insertPmsIntegralProduct( pmsIntegralProduct );
    }

    /**
     * 修改积分商品
     *
     * @param pmsIntegralProduct 积分商品
     * @return 修改积分商品数量
     */
    @Override
    public int updatePmsIntegralProduct(PmsIntegralProduct pmsIntegralProduct) {
        if (StringUtils.isBlank( pmsIntegralProduct.getUpdateBy() )) {
            pmsIntegralProduct.setUpdateBy( ShiroUtils.getLoginName() );
        }
        pmsIntegralProduct.setUpdateTime( DateUtils.getNowDate() );
        return pmsIntegralProductMapper.updatePmsIntegralProduct( pmsIntegralProduct );
    }

    /**
     * 删除积分商品对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除积分商品数量
     */
    @Override
    public int deletePmsIntegralProductByIds(String ids) {
        return pmsIntegralProductMapper.deletePmsIntegralProductByIds( Convert.toStrArray( ids ) );
    }

    /**
     * 删除积分商品信息
     *
     * @param id 积分商品ID
     * @return 删除积分商品数量
     */
    @Override
    public int deletePmsIntegralProductById(Long id) {
        return pmsIntegralProductMapper.deletePmsIntegralProductById( id );
    }

    /**
     * 商品上架
     *
     * @param id
     * @return
     */
    @Override
    public int putawayPmsIntegralProduct(Long id) {
        Checker.check( ObjectUtils.isEmpty( id ), "缺少积分商品标识" );
        operateValidate( id, ProductAttributeCategoryConst.IntegralProductBtnEnum.BTN_PUTAWAY );
        PmsIntegralProduct pmsIntegralProduct = new PmsIntegralProduct();
        pmsIntegralProduct.setId( id );
        pmsIntegralProduct.setPublishStatus( ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType() );
        return updatePmsIntegralProduct( pmsIntegralProduct );
    }

    /**
     * 商品下架
     *
     * @param id
     * @return
     */
    @Override
    public int soldoutPmsIntegralProduct(Long id) {
        Checker.check( ObjectUtils.isEmpty( id ), "缺少积分商品标识" );
        operateValidate( id, ProductAttributeCategoryConst.IntegralProductBtnEnum.BTN_SOLDOUT );
        PmsIntegralProduct pmsIntegralProduct = new PmsIntegralProduct();
        pmsIntegralProduct.setId( id );
        pmsIntegralProduct.setPublishStatus( ProductAttributeCategoryConst.publicStatusEnum.SOLDOUT.getType() );
        return updatePmsIntegralProduct( pmsIntegralProduct );
    }

    /**
     * 更改排序
     *
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int changeSort(Long id, Long sort) {
        Checker.check( ObjectUtils.isEmpty( id ), "缺少积分商品标识" );
        Checker.check( ObjectUtils.isEmpty( sort ), "缺少排序信息" );
        PmsIntegralProduct pmsIntegralProduct = new PmsIntegralProduct();
        pmsIntegralProduct.setId( id );
        pmsIntegralProduct.setSort( sort );
        return updatePmsIntegralProduct( pmsIntegralProduct );
    }


    /**
     * 校验是否满足操作执行需要的状态
     *
     * @param productId
     * @param btnEnum
     */
    @Override
    public void operateValidate(Long productId, ProductAttributeCategoryConst.IntegralProductBtnEnum btnEnum) {
        PmsIntegralProduct product = selectPmsIntegralProductById( productId );
        operateValidate( product, btnEnum );
    }

    @Override
    public void operateValidate(PmsIntegralProduct product, ProductAttributeCategoryConst.IntegralProductBtnEnum btnEnum) {
        log.info( "校验是否满足操作执行需要的状态" );
        Checker.check( ObjectUtils.isEmpty( product ), "积分商品数据异常" );
        Checker.checkOp( Arrays.asList( btnEnum.getPublishStatus() ).contains( product.getPublishStatus() ), "操作异常，请刷新后重试！" );
    }

    /**
     * 兑换成功>减库存
     *
     * @param quantity
     * @param id
     * @return
     */
    @Override
    public int subStock(Long quantity, Long id) {
        return pmsIntegralProductMapper.subStrock( quantity, id );
    }

    /**
     * （app）获取积分商品列表
     *
     * @param categoryId
     * @param name
     * @return
     */
    @Override
    public List<PmsIntegralProduct> getPmsIntegralProductListForDisplay(Long categoryId, String name) {
        return pmsIntegralProductMapper.getPmsIntegralProductListForDisplay( categoryId, name );
    }

    /**
     * （app）获取积分商品详情
     *
     * @param id
     * @return
     */
    @Override
    public PmsIntegralProduct getPmsIntegralProductDetailById(Long id) {
        return pmsIntegralProductMapper.getPmsIntegralProductDetailById( id );
    }
}
