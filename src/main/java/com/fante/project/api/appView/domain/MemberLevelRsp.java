package com.fante.project.api.appView.domain;

import java.io.Serializable;

/**
 * @author ftnet
 * @Description MemberLevelRsp
 * @CreatTime 2020/6/19
 */
public class MemberLevelRsp  implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean status; //
    private String msg; //":"操作成功",
    private String data; //":"1",
    private String data1; //":"oVUZ8t9ckXauvhSbO0Nn2EX3RpYU",
    private String data2; //":null,
    private String attributes; //":null

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
