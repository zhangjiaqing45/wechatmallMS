package com.fante.project.api.appView.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author ftnet
 * @Description ShopOfProductDatas
 * @CreatTime 2020/4/29
 */
public class ShopOfProductDatas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String shopId;
    private String pids;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }
}
