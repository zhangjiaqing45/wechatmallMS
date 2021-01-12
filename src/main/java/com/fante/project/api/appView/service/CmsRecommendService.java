package com.fante.project.api.appView.service;

import com.fante.common.business.enums.SmsRecommendConst;
import com.fante.project.api.appView.domain.CmsRecommendReq;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProductDTO;
import com.fante.project.business.smsHomeRecommendProduct.service.ISmsHomeRecommendProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:56
 * @Author: Mr.Z
 * @Description: 前端推荐商品相关处理服务
 */
@Service
public class CmsRecommendService {

    private static Logger log = LoggerFactory.getLogger(CmsRecommendService.class);

    @Autowired
    ISmsHomeRecommendProductService smsHomeRecommendProductService;

    /**
     * 推荐商品列表
     * @param req
     * @return
     */
    public List<SmsHomeRecommendProductDTO> recommendProducts(CmsRecommendReq req) {
        SmsRecommendConst.ProductType productType = SmsRecommendConst.ProductType.get(req.getType());
        if (ObjectUtils.isEmpty(productType)) {
            log.error("推荐商品类型不存在");
            return Collections.emptyList();
        }
        return smsHomeRecommendProductService.selectAppJoinList(req);
    }

}
