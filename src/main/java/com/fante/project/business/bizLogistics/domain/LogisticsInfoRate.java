package com.fante.project.business.bizLogistics.domain;
/**
 * @ClassName LogisticsInfoRate
 * @Description 物流信息进度
 *              用于解析进度数组
 *              [{
 * 			"time": "2018-03-09 11:59:26",
 * 			"status": "【石家庄市】快件已在【长安三部】 签收,签收人: 本人,感谢使用中通快递,期待再次为您服务!"
 *                }, {
 * 			"time": "2018-03-09 09:03:10",
 * 			"status": "【石家庄市】 快件已到达 【长安三部】（0311-85344265）,业务员 容晓光（13081105270） 正在第1次派件, 请保持电话畅通,并耐心等待"
 *        }]
 * @Author LiuQingyu
 * @Date 2020-02-19 18:35
 * @Version 1.0
 **/
public class LogisticsInfoRate {
    private String time;

    private String status;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
