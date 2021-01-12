package com.fante.project.business.homeStatistics.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by ftnet on 2020/5/11
 */
public class ChartDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> xAxis;
    private Map<String, List<String>> series;

    private int maxOrderCount;
    private BigDecimal maxOrderMoney;
    public ChartDto() {
    }

    public ChartDto(List<String> xAxis, Map<String, List<String>> series, int maxOrderCount, BigDecimal maxOrderMoney) {
        this.xAxis = xAxis;
        this.series = series;
        this.maxOrderCount = maxOrderCount;
        this.maxOrderMoney = maxOrderMoney;
    }

    public int getMaxOrderCount() {
        return maxOrderCount;
    }

    public void setMaxOrderCount(int maxOrderCount) {
        this.maxOrderCount = maxOrderCount;
    }

    public BigDecimal getMaxOrderMoney() {
        return maxOrderMoney;
    }

    public void setMaxOrderMoney(BigDecimal maxOrderMoney) {
        this.maxOrderMoney = maxOrderMoney;
    }

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public Map<String, List<String>> getSeries() {
        return series;
    }

    public void setSeries(Map<String, List<String>> series) {
        this.series = series;
    }

}
