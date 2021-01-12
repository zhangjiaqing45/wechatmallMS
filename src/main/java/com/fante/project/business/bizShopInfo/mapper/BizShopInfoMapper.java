package com.fante.project.business.bizShopInfo.mapper;

import com.fante.project.api.appView.domain.ShopAndCouponInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfoDTO;
import com.fante.project.business.bizShopInfo.domain.ShopInfoVo;
import com.fante.project.mapperBase.BizShopInfoMapperBase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺信息Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-11
 */

public interface BizShopInfoMapper extends BizShopInfoMapperBase {

    /**
     * 店铺信息唯一校验
     * @param bizShopInfo
     * @return
     */
    int checkShopUnique(BizShopInfo bizShopInfo);

    /**
     * 店铺信息列表
     * @param shopInfoDTO
     * @return
     */
    List<BizShopInfoDTO> selectJoinList(BizShopInfoDTO shopInfoDTO);

    /**
     * 根据条件查询店铺信息
     * @param shopInfoDTO
     * @return
     */
    BizShopInfoDTO selectJoinSigle(BizShopInfoDTO shopInfoDTO);

    /**
     * 根据店铺编号查询店铺信息
     * @param shopCode
     * @return
     */
    BizShopInfo selectBizShopInfoByCode(String shopCode);

    /**
     * (app)查询店铺
     *
     * @return
     */
     List<BizShopInfo> selectBizShopInfoForApp(@Param("recommend") String recommend,
                                                      @Param("name") String name,
                                                      @Param("audit") String audit);

    /**
     * (app)查询热门店铺
     *
     * @return
     */
     List<BizShopInfo> selectBizShopInfoForAppByIshot(@Param("ishot") String ishot,
                                                      @Param("audit") String audit);

    /**
     * 插入现金 到平台现金账户
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    int addCashToAccount(@Param("shopId")Long shopId, @Param("money")BigDecimal money);
    /**
     * 插入佣金 到平台佣金账户
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    int addCommissionToAccount(@Param("shopId")Long shopId, @Param("money")BigDecimal money);
    /**
     * 从平台现金账户中去除@退货
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    int subCashToAccount(@Param("shopId")Long shopId, @Param("money")BigDecimal money);
    /**
     * 从平台佣金账户中去除@退货
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    int subCommissionToAccount(@Param("shopId")Long shopId, @Param("money")BigDecimal money);
    /**
     * 查询店铺推荐及优惠券信息
     * @return
     */
    List<ShopAndCouponInfo> selectJoinCouponList(@Param("ids") List<Long> ids);
    
    /**
     * 查询店铺推荐及优惠券信息
     * @return
     */
    List<ShopAndCouponInfo> selectJoinCouponListRemake(ShopInfoVo shopInfoVo);
}
