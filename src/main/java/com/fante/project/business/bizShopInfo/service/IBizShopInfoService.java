package com.fante.project.business.bizShopInfo.service;

import com.fante.project.api.appView.domain.ShopAndCouponInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfoDTO;
import com.fante.project.business.bizShopInfo.domain.ShopInfoVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺信息Service接口
 *
 * @author fante
 * @date 2020-03-11
 */
public interface IBizShopInfoService {
    /**
     * 查询店铺信息
     *
     * @param id 店铺信息ID
     * @return 店铺信息
     */
    public BizShopInfo selectBizShopInfoById(Long id);

    /**
     * 查询店铺信息列表
     *
     * @param bizShopInfo 店铺信息
     * @return 店铺信息集合
     */
    public List<BizShopInfo> selectBizShopInfoList(BizShopInfo bizShopInfo);

    /**
     * 查询店铺信息数量
     *
     * @param bizShopInfo 店铺信息
     * @return 结果
     */
    public int countBizShopInfo(BizShopInfo bizShopInfo);

    /**
     * 新增店铺信息
     *
     * @param bizShopInfo 店铺信息
     * @return 新增店铺信息数量
     */
    public int insertBizShopInfo(BizShopInfo bizShopInfo);

    /**
     * 修改店铺信息
     *
     * @param bizShopInfo 店铺信息
     * @return 修改店铺信息数量
     */
    public int updateBizShopInfo(BizShopInfo bizShopInfo);

    /**
     * 批量删除店铺信息
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺信息数量
     */
    public int deleteBizShopInfoByIds(String ids);

    /**
     * 删除店铺信息信息
     *
     * @param id 店铺信息ID
     * @return 删除店铺信息数量
     */
    public int deleteBizShopInfoById(Long id);

    /**
     * 检验是否允许操作
     * @param bizShopInfo
     */
    public void checkAllowed(BizShopInfo bizShopInfo);

    /**
     * 店铺信息唯一校验
     * @param bizShopInfo
     * @return
     */
    public String checkShopUnique(BizShopInfo bizShopInfo);


    /**
     * 店铺信息列表
     * @param shopInfoDTO
     * @return
     */
    public List<BizShopInfoDTO> selectJoinList(BizShopInfoDTO shopInfoDTO);

    /**
     * 根据条件查询店铺信息
     * @param shopInfoDTO
     * @return
     */
    public BizShopInfoDTO selectJoinSingle(BizShopInfoDTO shopInfoDTO);


    /**
     * 查看店铺是否启用
     * @param deptId
     * @return
     */
    public boolean checkEnable(Long deptId);

    /**
     * 根据店铺编号查询店铺信息
     * @param shopCode
     * @return
     */
    BizShopInfo selectBizShopInfoByCode(String shopCode);

    /**
     * (app)查询店铺
     * @return
     */
    List<BizShopInfo> selectBizShopInfoForApp(String recommend,String name);

    /**
     * (app)查询热门店铺
     * @return
     */
    List<BizShopInfo> selectBizShopInfoForAppByIshot(String ishot);

    /**
     * 插入现金 到平台现金账户
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    int addCashToAccount(Long shopId, BigDecimal money);

    /**
     * 插入佣金 到平台佣金账户
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    int addCommissionToAccount(Long shopId, BigDecimal money);
    /**
     * 从平台现金账户中去除@退货/提现
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    int subCashToAccount(Long shopId, BigDecimal money);
    /**
     * 从平台佣金账户中去除@退货/提现
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    int subCommissionToAccount(Long shopId, BigDecimal money);

    /**
     * 查询店铺推荐及优惠券信息
     * @return
     */
    List<ShopAndCouponInfo> selectJoinCouponList(List<Long> ids);
    
    /**
     * 查询店铺推荐及优惠券信息
     * @return
     */
    List<ShopAndCouponInfo> selectJoinCouponListRemake(ShopInfoVo shopInfoVo);
}
