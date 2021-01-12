package com.fante.project.business.pmsProductLog.service.impl;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.constant.Constants;
import com.fante.common.exception.BusinessException;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProductLog.domain.PmsProductLog;
import com.fante.project.business.pmsProductLog.domain.PmsProductLogParam;
import com.fante.project.business.pmsProductLog.mapper.PmsProductLogMapper;
import com.fante.project.business.pmsProductLog.service.IPmsProductLogService;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.system.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 商品操作日志Service业务层处理
 *
 * @author fante
 * @date 2020-03-21
 */
@Service
public class PmsProductLogServiceImpl implements IPmsProductLogService {

    private static Logger log = LoggerFactory.getLogger(PmsProductLogServiceImpl.class);

    @Autowired
    private PmsProductLogMapper pmsProductLogMapper;


    /**
     * 查询商品操作日志
     *
     * @param id 商品操作日志ID
     * @return 商品操作日志
     */
    @Override
    public PmsProductLog selectPmsProductLogById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsProductLogMapper.selectPmsProductLogById(id);
    }

    /**
     * 查询商品操作日志列表
     *
     * @param pmsProductLog 商品操作日志
     * @return 商品操作日志集合
     */
    @Override
    public List<PmsProductLog> selectPmsProductLogList(PmsProductLog pmsProductLog) {
        return pmsProductLogMapper.getPmsProductLogList(pmsProductLog);
    }

    /**
     * 查询商品操作日志数量
     *
     * @param pmsProductLog 商品操作日志
     * @return 结果
     */
    @Override
    public int countPmsProductLog(PmsProductLog pmsProductLog){
        return pmsProductLogMapper.countPmsProductLog(pmsProductLog);
    }

    /**
     * 唯一校验
     *
     * @param pmsProductLog 商品操作日志
     * @return 结果
     */
    @Override
    public String checkPmsProductLogUnique(PmsProductLog pmsProductLog) {
        return pmsProductLogMapper.checkPmsProductLogUnique(pmsProductLog) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }
    /**
     * 新增商品操作日志
     *
     * @param pmsProductLog 商品操作日志
     * @return 新增商品操作日志数量
     */
    @Override
    public int insertPmsProductLog(PmsProductLog pmsProductLog) {
        return pmsProductLogMapper.insertPmsProductLog(pmsProductLog);
    }
    /**
     * 新增商品操作日志
     *
     * @param productLogParam 商品操作日志
     * @return 新增商品操作日志数量
     */
    @Override
    public int insertPmsProductLog(PmsProductLogParam productLogParam) {
        //获取参数
        PmsProduct product = productLogParam.getProduct();
        PmsSkuStock sku = productLogParam.getSku();
        ProductAttributeCategoryConst.ActivityEnum action = productLogParam.getAction();
        Checker.check(ObjectUtils.isEmpty(action)||ObjectUtils.isEmpty(product),"参数缺失,操作失败！");
        PmsProductLog insert = new PmsProductLog();
        User user = ShiroUtils.getSysUser();
        //店铺信息
        insert.setShopId(product.getShopId());
        //商品信息
        insert.setProductId(product.getId());
        insert.setProductName(product.getName());
        //动作
        insert.setAction(action.getDescribe());
        //记录修改值
        String newValue = productLogParam.getNewValue();
        String oldValue = "";

        switch (action) {
            case ORIGINALPRICE:
                //修改市场价
                oldValue = product.getOriginalPrice().toString();
                break;
            case STOCK:
                //修改sku库存
                Checker.check(ObjectUtils.isEmpty(sku),"参数缺失,操作失败！");
                //sku信息
                insert.setSkuId(sku.getId());
                insert.setSkuSpData(sku.getSpData());
                oldValue = String.valueOf(sku.getStock());
                break;
            case PRICE:
                //修改主页显示价格
                oldValue = product.getPrice().toString();
                break;
            case SORT:
                //修改商品排序
                oldValue =  String.valueOf(product.getSort());
                break;
            case SKUPRICE:
                //修改sku的价格
                insert.setSkuId(sku.getId());
                insert.setSkuSpData(sku.getSpData());
                oldValue = sku.getPrice().toString();
                break;
            case LOWSTOCK:
                //修改sku的预警库存
                insert.setSkuId(sku.getId());
                insert.setSkuSpData(sku.getSpData());
                oldValue = String.valueOf(sku.getLowStock());
                break;
            case COMMISSION:
                //修改佣金  需要商品关联查询 佣金表 获取佣金
                break;
            default:
                log.error("日志操作类型异常");
        }
        //设置新旧值
        insert.setNewValue(newValue);
        insert.setOldValue(oldValue);
        //创建时间和创建人
        insert.setCreateBy(user.getLoginName());
        insert.setCreateTime(DateUtils.getNowDate());
        //生成描述信息:
        // 例如:张三 在 2020年8月8日 12点12分13秒 对商品[美的冰(箱型号:m-d1280)] 执行了[删除/新增/编辑/下架/上架/导出/修改库存/提交审核]操作.
        // 值:由20改为80.
        String describe = insert.getCreateBy()+"在"+
                DateUtils.parseDateToStr("yyyy年MM月dd日 hh时mm分ss秒",insert.getCreateTime()) +
                "对商品["+insert.getProductName()+
                (StringUtils.isEmpty(insert.getSkuSpData())?"":"("+insert.getSkuSpData()+")")+"]"+
                "执行了["+insert.getAction()+"]的操作."+
                (!StringUtils.isEmpty(insert.getOldValue())?
                "值由‘"+insert.getOldValue()+"’改为‘"+insert.getNewValue()+"’":StringUtils.EMPTY);
        insert.setDescription(describe);
        return pmsProductLogMapper.insertPmsProductLog(insert);
    }

    /**
     * 修改商品操作日志
     *
     * @param pmsProductLog 商品操作日志
     * @return 修改商品操作日志数量
     */
    @Override
    public int updatePmsProductLog(PmsProductLog pmsProductLog) {
        pmsProductLog.setUpdateTime(DateUtils.getNowDate());
        return pmsProductLogMapper.updatePmsProductLog(pmsProductLog);
    }

    /**
     * 删除商品操作日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品操作日志数量
     */
    @Override
    public int deletePmsProductLogByIds(String ids) {
        return pmsProductLogMapper.deletePmsProductLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品操作日志信息
     *
     * @param id 商品操作日志ID
     * @return 删除商品操作日志数量
     */
    @Override
    public int deletePmsProductLogById(Long id) {
        return pmsProductLogMapper.deletePmsProductLogById(id);
    }
}
