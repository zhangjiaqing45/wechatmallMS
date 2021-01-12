package com.fante.project.business.pmsProductAuditLog.service.impl;

import java.util.List;

import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.domain.PmsProductResult;
import com.fante.project.business.pmsProduct.mapper.PmsProductMapper;
import com.fante.project.business.pmsProductLog.mapper.PmsProductLogMapper;
import com.fante.project.system.user.domain.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.pmsProductAuditLog.mapper.PmsProductAuditLogMapper;
import com.fante.project.business.pmsProductAuditLog.domain.PmsProductAuditLog;
import com.fante.project.business.pmsProductAuditLog.service.IPmsProductAuditLogService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

import static com.fante.common.utils.DateUtils.getNowDate;

/**
 * 商品审核日志Service业务层处理
 *
 * @author fante
 * @date 2020-03-19
 */
@Service
public class PmsProductAuditLogServiceImpl implements IPmsProductAuditLogService {

    private static Logger log = LoggerFactory.getLogger(PmsProductAuditLogServiceImpl.class);

    @Autowired
    private PmsProductAuditLogMapper pmsProductAuditLogMapper;
    @Autowired
    private PmsProductMapper pmsProductMapper;

    /**
     * 查询商品审核日志
     *
     * @param id 商品审核日志ID
     * @return 商品审核日志
     */
    @Override
    public PmsProductAuditLog selectPmsProductAuditLogById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsProductAuditLogMapper.selectPmsProductAuditLogById(id);
    }

    /**
     * 查询商品审核日志列表
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 商品审核日志集合
     */
    @Override
    public List<PmsProductAuditLog> selectPmsProductAuditLogList(PmsProductAuditLog pmsProductAuditLog) {
        return pmsProductAuditLogMapper.getProductAuditLogList(pmsProductAuditLog);
    }

    /**
     * 查询商品审核日志数量
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 结果
     */
    @Override
    public int countPmsProductAuditLog(PmsProductAuditLog pmsProductAuditLog){
        return pmsProductAuditLogMapper.countPmsProductAuditLog(pmsProductAuditLog);
    }

    /**
     * 唯一校验
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 结果
     */
    @Override
    public String checkPmsProductAuditLogUnique(PmsProductAuditLog pmsProductAuditLog) {
        return pmsProductAuditLogMapper.checkPmsProductAuditLogUnique(pmsProductAuditLog) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增商品审核日志
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 新增商品审核日志数量
     */
    @Override
    public int insertPmsProductAuditLog(PmsProductAuditLog pmsProductAuditLog) {
        pmsProductAuditLog.setCreateTime(getNowDate());
        return pmsProductAuditLogMapper.insertPmsProductAuditLog(pmsProductAuditLog);
    }
    /**
     * 新增商品审核日志
     *
     * @param pmsProduct 商品审核日志
     * @return 新增商品审核日志数量
     */
    @Override
    public int addPmsProductAuditLog(PmsProduct pmsProduct) {
        PmsProduct product = new PmsProduct();
        product.setId(pmsProduct.getId());
        //不需要验证参数,在提交审核的时候已经验证
        List<PmsProductResult> results = pmsProductMapper.selectPmsProductShowList(product);
        Checker.check(ObjectUtils.isEmpty(results),"该商品不存在！");
        PmsProductResult result = results.get(0);
        //声明log
        PmsProductAuditLog log = new PmsProductAuditLog();
        //设置店铺信息
        log.setShopId(result.getShopId());
        log.setShopName(result.getShopName());
        //设置审核信息
        log.setPermission(AuditTypeEnum.WAIT.getDescribe());
        //设置商品信息
        log.setProductId(result.getId());
        log.setProductName(result.getName());
        //设置申请人的信息
        log.setCreateBy(ShiroUtils.getUserName());
        log.setCreateTime(getNowDate());
        //插入
        return pmsProductAuditLogMapper.insertPmsProductAuditLog(log);
    }
    /**
     * 修改商品审核日志
     *
     * @param pmsProduct 商品审核日志
     * @return 修改商品审核日志数量
     */
    @Override
    public int productAuditLog(PmsProduct pmsProduct) {
        //不许要验证参数,在审核审批的时候已经验证

        //获取最新的审核记录并写入审核结果
        PmsProductAuditLog log = new PmsProductAuditLog();
        log.setProductId(pmsProduct.getId());
        log.setPermission(AuditTypeEnum.WAIT.getDescribe());
        List<PmsProductAuditLog> logs = pmsProductAuditLogMapper.getProductAuditLogList(log);
        Checker.check(ObjectUtils.isEmpty(logs),"该商品没有审核记录！");

        log = logs.get(0);
        //设置审核信息
        log.setAuditMsg(pmsProduct.getAuditMsg());
        log.setPermission(AuditTypeEnum.get(pmsProduct.getVerifyStatus()).getDescribe());

        //设置审批人的信息
        log.setUpdateBy(ShiroUtils.getUserName());
        log.setUpdateTime(getNowDate());
        //更新
        return pmsProductAuditLogMapper.updatePmsProductAuditLog(log);
    }

    /**
     * 修改商品审核日志
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 修改商品审核日志数量
     */
    @Override
    public int updatePmsProductAuditLog(PmsProductAuditLog pmsProductAuditLog) {
        pmsProductAuditLog.setUpdateTime(getNowDate());
        return pmsProductAuditLogMapper.updatePmsProductAuditLog(pmsProductAuditLog);
    }

    /**
     * 删除商品审核日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品审核日志数量
     */
    @Override
    public int deletePmsProductAuditLogByIds(String ids) {
        return pmsProductAuditLogMapper.deletePmsProductAuditLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品审核日志信息
     *
     * @param id 商品审核日志ID
     * @return 删除商品审核日志数量
     */
    @Override
    public int deletePmsProductAuditLogById(Long id) {
        return pmsProductAuditLogMapper.deletePmsProductAuditLogById(id);
    }
}
