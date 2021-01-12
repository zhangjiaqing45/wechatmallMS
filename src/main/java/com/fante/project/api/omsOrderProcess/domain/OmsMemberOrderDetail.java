package com.fante.project.api.omsOrderProcess.domain;

import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;

import java.util.List;

/**
 * @author ftnet
 * @Description OmsMemberOrderDetail
 * @CreatTime 2020/5/4
 */
public class OmsMemberOrderDetail extends OmsOrder {
    private List<OmsOrderItem> itemList;
    private String shopName;


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<OmsOrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OmsOrderItem> itemList) {
        this.itemList = itemList;
    }
}
