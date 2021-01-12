package com.fante.project.business.smsHomeAdvertise.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/18 17:02
 * @Author: Mr.Z
 * @Description: 按照广告位置分类组装数据
 */
public class SmsHomeAdvertisePositionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String position;

    private List<SmsHomeAdvertise> advertises;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<SmsHomeAdvertise> getAdvertises() {
        return advertises;
    }

    public void setAdvertises(List<SmsHomeAdvertise> advertises) {
        this.advertises = advertises;
    }
}
