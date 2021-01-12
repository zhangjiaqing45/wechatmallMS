package com.fante.project.weixin.core.templatemsg;

import com.fante.common.utils.StringUtils;
import com.google.common.collect.Lists;
import org.weixin4j.model.message.template.TemplateData;

import java.io.Serializable;
import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/2/22 11:35
 * @Author: Mr.Z
 * @Description:
 */
public class AfterSalesServiceTpl implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String DEF_COLOR = "#173177";

    /**
     * 模版ID: zU7FEeGLeBxQ1tYbBZ7cTaz7p628NeTldBKs0iiMjus
     * 标题:  售后服务处理进度提醒
     * 行业:  IT科技 - 互联网|电子商务
     * 详细内容:
     *      {{first.DATA}}
     *      服务类型：{{HandleType.DATA}}
     *      处理状态：{{Status.DATA}}
     *      提交时间：{{RowCreateDate.DATA}}
     *      当前进度：{{LogType.DATA}}
     *      {{remark.DATA}}
     */

    private String firstValue;
    private String firstColor;
    private String typeValue;
    private String typeColor;
    private String statusValue;
    private String statusColor;
    private String dateValue;
    private String dateColor;
    private String logValue;
    private String logColor;
    private String remarkValue;
    private String remarkColor;

    public AfterSalesServiceTpl() {
    }

    public AfterSalesServiceTpl(String firstValue, String typeValue, String statusValue, String dateValue, String logValue, String remarkValue) {
        this.firstValue = firstValue;
        this.typeValue = typeValue;
        this.statusValue = statusValue;
        this.dateValue = dateValue;
        this.logValue = logValue;
        this.remarkValue = remarkValue;
    }

    public AfterSalesServiceTpl(String firstValue, String firstColor, String typeValue, String typeColor, String statusValue, String statusColor, String dateValue, String dateColor, String logValue, String logColor, String remarkValue, String remarkColor) {
        this.firstValue = firstValue;
        this.firstColor = firstColor;
        this.typeValue = typeValue;
        this.typeColor = typeColor;
        this.statusValue = statusValue;
        this.statusColor = statusColor;
        this.dateValue = dateValue;
        this.dateColor = dateColor;
        this.logValue = logValue;
        this.logColor = logColor;
        this.remarkValue = remarkValue;
        this.remarkColor = remarkColor;
    }

    public List<TemplateData> toTplDataList() {
        List<TemplateData> datas = Lists.newArrayList();
        datas.add(new TemplateData("first", StringUtils.defaultString(getFirstValue()), StringUtils.defaultString(getFirstColor(), DEF_COLOR)));
        datas.add(new TemplateData("HandleType", StringUtils.defaultString(getTypeValue()), StringUtils.defaultString(getTypeColor(), DEF_COLOR)));
        datas.add(new TemplateData("Status", StringUtils.defaultString(getStatusValue()), StringUtils.defaultString(getStatusColor(), DEF_COLOR)));
        datas.add(new TemplateData("RowCreateDate", StringUtils.defaultString(getDateValue()), StringUtils.defaultString(getDateColor(), DEF_COLOR)));
        datas.add(new TemplateData("LogType", StringUtils.defaultString(getLogValue()), StringUtils.defaultString(getLogColor(), DEF_COLOR)));
        datas.add(new TemplateData("remark", StringUtils.defaultString(getRemarkValue()), StringUtils.defaultString(getRemarkColor(), DEF_COLOR)));
        return datas;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getFirstColor() {
        return firstColor;
    }

    public void setFirstColor(String firstColor) {
        this.firstColor = firstColor;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(String typeColor) {
        this.typeColor = typeColor;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getDateColor() {
        return dateColor;
    }

    public void setDateColor(String dateColor) {
        this.dateColor = dateColor;
    }

    public String getLogValue() {
        return logValue;
    }

    public void setLogValue(String logValue) {
        this.logValue = logValue;
    }

    public String getLogColor() {
        return logColor;
    }

    public void setLogColor(String logColor) {
        this.logColor = logColor;
    }

    public String getRemarkValue() {
        return remarkValue;
    }

    public void setRemarkValue(String remarkValue) {
        this.remarkValue = remarkValue;
    }

    public String getRemarkColor() {
        return remarkColor;
    }

    public void setRemarkColor(String remarkColor) {
        this.remarkColor = remarkColor;
    }
}
