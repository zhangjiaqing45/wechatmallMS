package com.fante.project.business.bizShopInfo.service.impl;

import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.api.appView.domain.ShopAndCouponInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfoDTO;
import com.fante.project.business.bizShopInfo.domain.ShopInfoVo;
import com.fante.project.business.bizShopInfo.mapper.BizShopInfoMapper;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.naming.event.ObjectChangeListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 店铺信息Service业务层处理
 *
 * @author fante
 * @date 2020-03-11
 */
@Service
public class BizShopInfoServiceImpl implements IBizShopInfoService {

    private static Logger log = LoggerFactory.getLogger(BizShopInfoServiceImpl.class);

    @Autowired
    private BizShopInfoMapper bizShopInfoMapper;

    /**
     * 查询店铺信息
     *
     * @param id 店铺信息ID
     * @return 店铺信息
     */
    @Override
    public BizShopInfo selectBizShopInfoById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return bizShopInfoMapper.selectBizShopInfoById(id);
    }

    /**
     * 查询店铺信息列表
     *
     * @param bizShopInfo 店铺信息
     * @return 店铺信息集合
     */
    @Override
    public List<BizShopInfo> selectBizShopInfoList(BizShopInfo bizShopInfo) {
        return bizShopInfoMapper.selectBizShopInfoList(bizShopInfo);
    }

    /**
     * 查询店铺信息数量
     *
     * @param bizShopInfo 店铺信息
     * @return 结果
     */
    @Override
    public int countBizShopInfo(BizShopInfo bizShopInfo){
        return bizShopInfoMapper.countBizShopInfo(bizShopInfo);
    }

    /**
     * 新增店铺信息
     *
     * @param bizShopInfo 店铺信息
     * @return 新增店铺信息数量
     */
    @Override
    public int insertBizShopInfo(BizShopInfo bizShopInfo) {
        if (StringUtils.isBlank(bizShopInfo.getCreateBy())) {
            bizShopInfo.setCreateBy(ShiroUtils.getLoginName());
        }
        bizShopInfo.setCreateTime(DateUtils.getNowDate());
        bizShopInfo.setCode(String.valueOf(IdGenerator.nextId()));
        return bizShopInfoMapper.insertBizShopInfo(bizShopInfo);
    }

    /**
     * 修改店铺信息
     *
     * @param bizShopInfo 店铺信息
     * @return 修改店铺信息数量
     */
    @Override
    public int updateBizShopInfo(BizShopInfo bizShopInfo) {
        if (StringUtils.isBlank(bizShopInfo.getUpdateBy())) {
            bizShopInfo.setUpdateBy(ShiroUtils.getLoginName());
        }
        bizShopInfo.setUpdateTime(DateUtils.getNowDate());
        return bizShopInfoMapper.updateBizShopInfo(bizShopInfo);
    }

    /**
     * 删除店铺信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺信息数量
     */
    @Override
    public int deleteBizShopInfoByIds(String ids) {
        Long[] shopIds = Convert.toLongArray(ids);
        for (Long id : shopIds) {
            checkAllowed(new BizShopInfo(id));
        }
        return bizShopInfoMapper.deleteBizShopInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除店铺信息信息
     *
     * @param id 店铺信息ID
     * @return 删除店铺信息数量
     */
    @Override
    public int deleteBizShopInfoById(Long id) {
        checkAllowed(new BizShopInfo(id));
        return bizShopInfoMapper.deleteBizShopInfoById(id);
    }

    /**
     * 检验是否允许操作
     * @param bizShopInfo
     */
    @Override
    public void checkAllowed(BizShopInfo bizShopInfo) {
        Checker.check(!ObjectUtils.isEmpty(bizShopInfo) && bizShopInfo.isSelfShop(), "不允许操作自营店");
    }

    /**
     * 店铺唯一校验
     * @param bizShopInfo
     * @return
     */
    @Override
    public String checkShopUnique(BizShopInfo bizShopInfo) {
        return bizShopInfoMapper.checkShopUnique(bizShopInfo) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 店铺信息列表
     * @param shopInfoDTO
     * @return
     */
    @Override
    public List<BizShopInfoDTO> selectJoinList(BizShopInfoDTO shopInfoDTO) {
        return bizShopInfoMapper.selectJoinList(shopInfoDTO);
    }

    /**
     * 根据条件查询店铺信息
     * @param shopInfoDTO
     * @return
     */
    @Override
    public BizShopInfoDTO selectJoinSingle(BizShopInfoDTO shopInfoDTO) {
        return bizShopInfoMapper.selectJoinSigle(shopInfoDTO);
    }

    /**
     * 查看店铺是否启用
     * @param deptId
     * @return
     */
    @Override
    public boolean checkEnable(Long deptId) {
        BizShopInfo bizShopInfo = selectBizShopInfoById(deptId);
        if (ObjectUtils.isEmpty(bizShopInfo)) {
            return false;
        }
        return Objects.equals(Constants.ENABLE, bizShopInfo.getStatus());
    }


    /**
     * 根据店铺编号查询店铺信息
     * @param shopCode
     * @return
     */
    @Override
    public BizShopInfo selectBizShopInfoByCode(String shopCode) {
        if (StringUtils.isBlank(shopCode)) {
            return null;
        }
        return bizShopInfoMapper.selectBizShopInfoByCode(shopCode);
    }
    /**
     * (app)查询店铺
     * @return
     */
    @Override
    public List<BizShopInfo> selectBizShopInfoForApp(String recommend,String name) {
        return bizShopInfoMapper.selectBizShopInfoForApp(recommend,name, AuditTypeEnum.SUCCESS.getType());
    }

    /**
     * (app)查询热门店铺
     * @return
     */
    @Override
    public List<BizShopInfo> selectBizShopInfoForAppByIshot(String ishot) {
        return bizShopInfoMapper.selectBizShopInfoForAppByIshot(ishot, AuditTypeEnum.SUCCESS.getType());
    }

    /**
     * 插入现金 到平台现金账户
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    @Override
    public int addCashToAccount(Long shopId, BigDecimal money){
        return bizShopInfoMapper.addCashToAccount(shopId,money);
    }

    /**
     * 插入佣金 到平台佣金账户
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    @Override
    public int addCommissionToAccount(Long shopId, BigDecimal money){
        return bizShopInfoMapper.addCommissionToAccount(shopId,money);
    }
    /**
     * 从平台现金账户中去除@退货
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    @Override
    public int subCashToAccount(Long shopId, BigDecimal money){
        return bizShopInfoMapper.subCashToAccount(shopId,money);
    }
    /**
     * 从平台佣金账户中去除@退货
     * @param shopId 店铺id
     * @param money 佣金
     * @return i
     */
    @Override
    public int subCommissionToAccount(Long shopId, BigDecimal money){
        return bizShopInfoMapper.subCommissionToAccount(shopId,money);
    }
    /**
     * 查询店铺推荐及优惠券信息
     * @return
     */
    @Override
    public List<ShopAndCouponInfo> selectJoinCouponList(List<Long> ids) {
        return bizShopInfoMapper.selectJoinCouponList( ids);
    }
    
    /**
     * 查询店铺推荐及优惠券信息
     * @return
     */
    @Override
    public List<ShopAndCouponInfo> selectJoinCouponListRemake(ShopInfoVo shopInfoVo){
        return bizShopInfoMapper.selectJoinCouponListRemake( shopInfoVo);
    }
}
