package com.fante.project.business.umsMemberProductStar.service.impl;

import java.util.List;
import java.util.Objects;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.business.enums.SmsRecommendConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.umsMemberShopStar.domain.UmsMemberShopStar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.umsMemberProductStar.mapper.UmsMemberProductStarMapper;
import com.fante.project.business.umsMemberProductStar.domain.UmsMemberProductStar;
import com.fante.project.business.umsMemberProductStar.service.IUmsMemberProductStarService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 商品收藏表Service业务层处理
 *
 * @author fante
 * @date 2020-04-24
 */
@Service
public class UmsMemberProductStarServiceImpl implements IUmsMemberProductStarService {

    private static Logger log = LoggerFactory.getLogger(UmsMemberProductStarServiceImpl.class);

    @Autowired
    private UmsMemberProductStarMapper umsMemberProductStarMapper;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;

    /**
     * 查询商品收藏表
     *
     * @param id 商品收藏表ID
     * @return 商品收藏表
     */
    @Override
    public UmsMemberProductStar selectUmsMemberProductStarById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return umsMemberProductStarMapper.selectUmsMemberProductStarById(id);
    }

    /**
     * 查询商品收藏表列表
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 商品收藏表集合
     */
    @Override
    public List<UmsMemberProductStar> selectUmsMemberProductStarList(UmsMemberProductStar umsMemberProductStar) {
        return umsMemberProductStarMapper.selectUmsMemberProductStarList(umsMemberProductStar);
    }

    /**
     * 查询商品收藏表数量
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 结果
     */
    @Override
    public int countUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar){
        return umsMemberProductStarMapper.countUmsMemberProductStar(umsMemberProductStar);
    }

    /**
     * 唯一校验
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 结果
     */
    @Override
    public String checkUmsMemberProductStarUnique(UmsMemberProductStar umsMemberProductStar) {
        return umsMemberProductStarMapper.checkUmsMemberProductStarUnique(umsMemberProductStar) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增商品收藏表
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 新增商品收藏表数量
     */
    @Override
    public int insertUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar) {
        //验证商品是否可用
        PmsProduct product = iPmsProductService.selectPmsProductById(umsMemberProductStar.getProductId());
        Checker.check(ObjectUtils.isEmpty(product),"该商品已下架！");
        Checker.checkOp(StringUtils.equals(product.getPublishStatus(), ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType()),"该商品已下架！");
        umsMemberProductStar.setStarTime(DateUtils.getNowDate());
        return umsMemberProductStarMapper.insertUmsMemberProductStar(umsMemberProductStar);
    }

    /**
     * 修改商品收藏表
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 修改商品收藏表数量
     */
    @Override
    public int updateUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar) {
        return umsMemberProductStarMapper.updateUmsMemberProductStar(umsMemberProductStar);
    }

    /**
     * 删除商品收藏表对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品收藏表数量
     */
    @Override
    public int deleteUmsMemberProductStarByIds(String ids) {
        return umsMemberProductStarMapper.deleteUmsMemberProductStarByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品收藏表信息
     *
     * @param id 商品收藏表ID
     * @return 删除商品收藏表数量
     */
    @Override
    public int deleteUmsMemberProductStarById(Long id,Long memberId) {
        return umsMemberProductStarMapper.memberDelProductStar(id,memberId);
    }
    /**
     * 获取会员收藏到所有商品
     */
    @Override
    public List<PmsProductDetailPageInfo> getMemberStarProduct(Long memberId){
        return umsMemberProductStarMapper.getMemberStarProduct(memberId);
    }
}
