package com.fante.project.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fante.common.utils.DateUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: Fante
 * @Date: 2019/12/22 15:55
 * @Author: Mr.Z
 * @Description: 集成WebSocket
 */
@Controller
@RequestMapping("/demo/integrate/websocket")
public class DemoWebSocketController extends BaseController {

    private String prefix = "demo/integrate/websocket";

    @Autowired
    SimpMessagingTemplate template;

    @GetMapping("/stomp")
    public String webSocketPage(ModelMap modelMap) {
        modelMap.put("user", getSysUser());
        return prefix + "/stomp";
    }

    /**
     * 广播推送消息
     * 不需要定时任务，注释：@Scheduled(fixedRate = 10000)
     */
    //@Scheduled(fixedRate = 10000)
    public void sendTopicMessage() {
        template.convertAndSend("/topic/getResponse", new TestInfo(DateUtils.getTime(), "当前时间："));
    }

    /**
     * 服务端接收客户端发来的消息并广播
     * @param topic
     * @param headers
     * @param payload
     */
    @MessageMapping("/endPoint")
    @SendTo("/topic/greetings")
    public void greeting(@Header("topic") String topic, @Headers Map<String, Object> headers, @Payload String payload) {
        System.out.println("connected successfully....");
        System.out.println(topic);
        System.out.println("--------------");
        System.out.println(headers);
        System.out.println("--------------");
        System.out.println(payload);
        JSONObject json = JSON.parseObject(payload);
        template.convertAndSend("/topic/greetings", new TestInfo(DateUtils.getTime(), json.getString("name")));
    }

    /**
     * 向指定用户发送消息
     * 本例使用的是部门ID，属于这个部门的人员都会收到消息
     * 如使用用户ID，则只有这个用户可以收到消息
     * @param deptId
     * @return
     */
    @RequestMapping("/sendToDept")
    @ResponseBody
    public AjaxResult sendByDept(Long deptId) {
        template.convertAndSendToUser(String.valueOf(deptId), "/queue/getResponse", new TestInfo(DateUtils.getTime(), "收到消息啦，部门：[" + deptId + "]接收"));
        return AjaxResult.success("已向部门ID：" + deptId + "向发送消息");
    }



}

class TestInfo {

    private String time;

    private String msg;

    public TestInfo() {
    }

    public TestInfo(String msg) {
        this.msg = msg;
    }

    public TestInfo(String time, String msg) {
        this.time = time;
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}