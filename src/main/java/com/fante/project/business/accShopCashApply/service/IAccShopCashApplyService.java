package com.fante.project.business.accShopCashApply.service;

import com.fante.project.business.accShopCashApply.domain.AccShopCashApply;
import com.fante.project.business.accShopCashApply.domain.AccShopCashApplyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺提现记录Service接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface IAccShopCashApplyService {
    /**
     * 查询店铺提现记录
     *
     * @param id 店铺提现记录ID
     * @return 店铺提现记录
     */
    public AccShopCashApply selectAccShopCashApplyById(Long id);

    /**
     * 查询店铺提现记录列表
     *
     * @param accShopCashApply 店铺提现记录
     * @return 店铺提现记录集合
     */
    public List<AccShopCashApply> selectAccShopCashApplyList(AccShopCashApply accShopCashApply);

    /**
     * 查询店铺提现记录数量
     *
     * @param accShopCashApply 店铺提现记录
     * @return 结果
     */
    public int countAccShopCashApply(AccShopCashApply accShopCashApply);

    /**
     * 唯一校验
     *
     * @param accShopCashApply 店铺提现记录
     * @return 结果
     */
    public String checkAccShopCashApplyUnique(AccShopCashApply accShopCashApply);

    /**
     * 新增店铺提现记录
     *
     * @param accShopCashApply 店铺提现记录
     * @return 新增店铺提现记录数量
     */
    public int insertAccShopCashApply(AccShopCashApply accShopCashApply);

    /**
     * 修改店铺提现记录
     *
     * @param accShopCashApply 店铺提现记录
     * @return 修改店铺提现记录数量
     */
    public int updateAccShopCashApply(AccShopCashApply accShopCashApply);

    /**
     * 批量删除店铺提现记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺提现记录数量
     */
    public int deleteAccShopCashApplyByIds(String ids);

    /**
     * 删除店铺提现记录信息
     *
     * @param id 店铺提现记录ID
     * @return 删除店铺提现记录数量
     */
    public int deleteAccShopCashApplyById(Long id);

    /**
     * 获取店铺可体现余额
     * @param shopId
     * @return
     */
    public BigDecimal getWithdrawable(Long shopId);

    /**
     * 店铺申请提现
     * @param shopId
     * @param money
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int applyCash(Long shopId, BigDecimal money);
    /**
     * 同意提现
     */
    @Transactional(rollbackFor = Exception.class)
    public int agree(Long id, String remark);

    /**
     * 拒绝提现
     */
    @Transactional(rollbackFor = Exception.class)
    public int refuse(Long id, String remark);

    /**
     * 申请审核
     */
    @Transactional(rollbackFor = Exception.class)
    public int applyAudit(Long id, String status, String remark);

    /**
     * 提现记录与店铺信息关联查询
     * @param dto
     * @return
     */
    public List<AccShopCashApplyDTO> selectJoinList(AccShopCashApplyDTO dto);
}
