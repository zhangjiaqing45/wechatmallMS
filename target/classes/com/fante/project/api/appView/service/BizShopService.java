package com.fante.project.api.appView.service;

import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:56
 * @Author: wz
 * @Description: 店铺
 */
@Service
public class BizShopService {

    private static Logger log = LoggerFactory.getLogger(BizShopService.class);
    @Autowired
    private IBizShopInfoService iBizShopInfoService;

    /**
     * 查询店铺
     * @return
     */
    public List<BizShopInfo> list(String recommend,String name) {
        return iBizShopInfoService.selectBizShopInfoForApp(recommend,name);
    }

    /**
     * 查询热门店铺
     * @return
     */
    public List<BizShopInfo> listByIshot(String ishot) {
        return iBizShopInfoService.selectBizShopInfoForAppByIshot(ishot);
    }

    /**
     * 店铺信息
     * @param shopId
     * @return
     */
    public BizShopInfo get(Long shopId) {
        return iBizShopInfoService.selectBizShopInfoById(shopId);
    }

}
