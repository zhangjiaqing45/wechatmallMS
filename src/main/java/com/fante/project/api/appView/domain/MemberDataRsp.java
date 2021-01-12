package com.fante.project.api.appView.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ftnet
 * @Description MemberLevelRsp
 * @CreatTime 2020/6/19
 */
public class MemberDataRsp implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean state; //
    private String msg; //":"操作成功",
    private Object data; //":"1",
    private Object data1; //":"oVUZ8t9ckXauvhSbO0Nn2EX3RpYU",
    private Object data2; //":null,
    private Map<String, Object> attributes;// 其他参数


    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData1() {
        return data1;
    }

    public void setData1(Object data1) {
        this.data1 = data1;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

}
