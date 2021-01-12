package com.fante.project.api.appView.service;

import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import com.fante.project.business.pmsIntegralLog.service.IPmsIntegralLogService;
import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
import com.fante.project.business.pmsIntegralProduct.service.IPmsIntegralProductService;
import com.fante.project.business.pmsIntegralProductCategory.domain.PmsIntegralProductCategory;
import com.fante.project.business.pmsIntegralProductCategory.service.IPmsIntegralProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ftnet
 * @Description CmsIntegralDisplayService
 * @CreatTime 2020/4/30
 */
@Service
public class CmsIntegralDisplayService {
    private static Logger log = LoggerFactory.getLogger(CmsIntegralDisplayService.class);
    /**
     * 积分分类
     */
    @Autowired
    private IPmsIntegralProductCategoryService iPmsIntegralProductCategoryService;
    /**
     * 积分商品
     */
    @Autowired
    private IPmsIntegralProductService iPmsIntegralProductService;
    /**
     * 积分记录
     */
    @Autowired
    private IPmsIntegralLogService iPmsIntegralLogService;
    /**
     * (app)积分商品分类查询
     * @return
     */
    public  List<PmsIntegralProductCategory>  getCategoryForNavInPlatform() {
        return iPmsIntegralProductCategoryService.getPmsIntegralProductCategoryForDisplay();
    }

    /**
     * （app）获取积分商品列表
     * @param categoryId
     * @param name
     * @return
     */
    public List<PmsIntegralProduct> getIntProductList(Long categoryId, String name) {
        return iPmsIntegralProductService.getPmsIntegralProductListForDisplay(categoryId,name);
    }
    /**
     * （app）获取积分商品详情
     * @param id
     * @return
     */
    public PmsIntegralProduct detail(Long id) {
        return iPmsIntegralProductService.getPmsIntegralProductDetailById(id);
    }

    /**
     * (app) 获取会员积分记录
     * @param log
     * @return
     */
    public List<PmsIntegralLog> record(PmsIntegralLog log) {
        return iPmsIntegralLogService.getMemberList(log);
    }
}
