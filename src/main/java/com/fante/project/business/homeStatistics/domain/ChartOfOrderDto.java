package com.fante.project.business.homeStatistics.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ftnet on 2020/5/11
 */
public class ChartOfOrderDto implements Serializable {
    private static final long serialVersionUID = 1L;

    // 日期
    private String date;
    // 总额
    private String total;
    // 总量
    private String num;

    public ChartOfOrderDto() {
    }

    public ChartOfOrderDto(String date, String total, String num) {
        this.date = date;
        this.total = total;
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
