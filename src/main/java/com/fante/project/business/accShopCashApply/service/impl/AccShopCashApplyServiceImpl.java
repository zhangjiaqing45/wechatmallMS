package com.fante.project.business.accShopCashApply.service.impl;

import com.fante.common.business.enums.AccCashApplyConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
import com.fante.project.business.accShopCashApply.domain.AccShopCashApply;
import com.fante.project.business.accShopCashApply.domain.AccShopCashApplyDTO;
import com.fante.project.business.accShopCashApply.mapper.AccShopCashApplyMapper;
import com.fante.project.business.accShopCashApply.service.IAccShopCashApplyService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺提现记录Service业务层处理
 *
 * @author fante
 * @date 2020-05-07
 */
@Service
public class AccShopCashApplyServiceImpl implements IAccShopCashApplyService {

    private static Logger log = LoggerFactory.getLogger(AccShopCashApplyServiceImpl.class);

    @Autowired
    private AccShopCashApplyMapper accShopCashApplyMapper;
    /**
     * 店铺信息Service接口
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;
    /**
     * 账户出入账记录Service接口
     */
    @Autowired
    private IAccAccountRecordService iAccAccountRecordService;

    /**
     * 查询店铺提现记录
     *
     * @param id 店铺提现记录ID
     * @return 店铺提现记录
     */
    @Override
    public AccShopCashApply selectAccShopCashApplyById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return accShopCashApplyMapper.selectAccShopCashApplyById(id);
    }

    /**
     * 查询店铺提现记录列表
     *
     * @param accShopCashApply 店铺提现记录
     * @return 店铺提现记录集合
     */
    @Override
    public List<AccShopCashApply> selectAccShopCashApplyList(AccShopCashApply accShopCashApply) {
        return accShopCashApplyMapper.selectAccShopCashApplyList(accShopCashApply);
    }

    /**
     * 查询店铺提现记录数量
     *
     * @param accShopCashApply 店铺提现记录
     * @return 结果
     */
    @Override
    public int countAccShopCashApply(AccShopCashApply accShopCashApply){
        return accShopCashApplyMapper.countAccShopCashApply(accShopCashApply);
    }

    /**
     * 唯一校验
     *
     * @param accShopCashApply 店铺提现记录
     * @return 结果
     */
    @Override
    public String checkAccShopCashApplyUnique(AccShopCashApply accShopCashApply) {
        return accShopCashApplyMapper.checkAccShopCashApplyUnique(accShopCashApply) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增店铺提现记录
     *
     * @param accShopCashApply 店铺提现记录
     * @return 新增店铺提现记录数量
     */
    @Override
    public int insertAccShopCashApply(AccShopCashApply accShopCashApply) {
        accShopCashApply.setCreateTime(DateUtils.getNowDate());
        return accShopCashApplyMapper.insertAccShopCashApply(accShopCashApply);
    }

    /**
     * 修改店铺提现记录
     *
     * @param accShopCashApply 店铺提现记录
     * @return 修改店铺提现记录数量
     */
    @Override
    public int updateAccShopCashApply(AccShopCashApply accShopCashApply) {
        accShopCashApply.setUpdateTime(DateUtils.getNowDate());
        return accShopCashApplyMapper.updateAccShopCashApply(accShopCashApply);
    }

    /**
     * 删除店铺提现记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺提现记录数量
     */
    @Override
    public int deleteAccShopCashApplyByIds(String ids) {
        return accShopCashApplyMapper.deleteAccShopCashApplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除店铺提现记录信息
     *
     * @param id 店铺提现记录ID
     * @return 删除店铺提现记录数量
     */
    @Override
    public int deleteAccShopCashApplyById(Long id) {
        return accShopCashApplyMapper.deleteAccShopCashApplyById(id);
    }

    /**
     * 获取店铺可体现余额
     * @param shopId
     * @return
     */
    @Override
    public BigDecimal getWithdrawable(Long shopId) {
        BizShopInfo shop = iBizShopInfoService.selectBizShopInfoById(shopId);
        Checker.check(ObjectUtils.isEmpty(shop), "店铺信息异常");
        BigDecimal withdraw = ObjectUtils.isEmpty(shop.getCash()) ? BigDecimal.ZERO : shop.getCash();

        // 如有需要继续处理（例如：单次最大提现金额、需留有多少余额等处理）

        return withdraw;
    }

    /**
     * 申请提现
     */
    @Override
    public int applyCash(Long shopId, BigDecimal money){
        Checker.check(ObjectUtils.isEmpty(shopId),"缺少店铺标识");
        Checker.check(ObjectUtils.isEmpty(money),"申请提现金额参数缺失");

        AccShopCashApply apply = new AccShopCashApply();
        apply.setShopId(shopId);
        apply.setMoney(money);
        apply.setStatus(AccCashApplyConst.AuditType.WAIT.getType());
        //插入提现申请
        int i = insertAccShopCashApply(apply);
        //修改店铺账户可提现余额 先减去
        i +=iBizShopInfoService.subCashToAccount(shopId, money);
        Checker.check(i!=2,"申请提现失败");
        return i;
    }
    /**
     * 同意提现
     */
    @Override
    public int agree(Long id, String remark){
        //查询
        AccShopCashApply apply = selectAccShopCashApplyById(id);
        Checker.check(ObjectUtils.isEmpty(apply),"该申请不存在");
        remark = StringUtils.isBlank(remark) ? "审核通过" : remark;
        //修改提现状态
        int i = applyAudit(id, AccCashApplyConst.AuditType.SUCCESS.getType(), remark);
        //插入出入账记录表
        AccAccountRecord update = new AccAccountRecord();
        update.setMoney(apply.getMoney().negate());
        update.setShopId(apply.getShopId());
        i +=iAccAccountRecordService.subAccountRecordOfCash(update);
        Checker.check(i!=2,"同意提现操作失败！");
        return i;
    }

    /**
     * 拒绝提现
     */
    @Override
    public int refuse(Long id, String remark){
        //查询
        AccShopCashApply apply = selectAccShopCashApplyById(id);
        Checker.check(ObjectUtils.isEmpty(apply),"该申请不存在");
        //修改提现状态
        int i = applyAudit(id, AccCashApplyConst.AuditType.FAIL.getType(), remark);
        //返回店铺余额账户
        i+=iBizShopInfoService.addCashToAccount(apply.getShopId(),apply.getMoney());
        Checker.check(i!=2,"拒绝提现失败");
        return i;
    }

    /**
     * 申请审核
     * @param id
     * @param status
     * @return
     */
    @Override
    public int applyAudit(Long id, String status, String remark) {
        return accShopCashApplyMapper.applyAudit(id, status, remark);
    }

    /**
     * 提现记录与店铺信息关联查询
     * @param dto
     * @return
     */
    @Override
    public List<AccShopCashApplyDTO> selectJoinList(AccShopCashApplyDTO dto) {
        return accShopCashApplyMapper.selectJoinList(dto);
    }
}
