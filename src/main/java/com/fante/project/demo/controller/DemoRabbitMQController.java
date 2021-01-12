package com.fante.project.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.fante.project.demo.sender.TestDirectMulSender;
//import com.fante.project.demo.sender.TestDirectSender;
//import com.fante.project.demo.sender.TestFanoutSender;
//import com.fante.project.demo.sender.TestTopicSender;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 集成RabbitMq测试
 */
@Controller
@RequestMapping("/demo/integrate/rabbitmq")
public class DemoRabbitMQController {

    //@Autowired
    //TestDirectSender testDirectSender;
    //@Autowired
    //TestDirectMulSender testDirectMulSender;
    //@Autowired
    //TestTopicSender testTopicSender;
    //@Autowired
    //TestFanoutSender testFanoutSender;

    private final String prefix = "demo/integrate/rabbitmq";

    @RequestMapping("rabbitmq")
    public String rabbitmqPage() {
        return prefix + "/rabbitmq";
    }



    ///**
    // * 直连交换机-单人消费
    // * @return
    // */
    //@RequestMapping("direct")
    //@ResponseBody
    //public AjaxResult direct() {
    //    testDirectSender.send();
    //    return AjaxResult.success();
    //}
    //
    ///**
    // * 直连交换机-多人消费
    // * @return
    // */
    //@RequestMapping("directMul")
    //@ResponseBody
    //public AjaxResult directMul() {
    //    for (int i = 0; i < 100; i++) {
    //        testDirectMulSender.send(i);
    //    }
    //    return AjaxResult.success();
    //}
    //
    ///**
    // * 主题交换机
    // * @return
    // */
    //@RequestMapping("topic")
    //@ResponseBody
    //public AjaxResult topic() {
    //    testTopicSender.send();
    //    return AjaxResult.success();
    //}
    //
    ///**
    // * 扇形交换机
    // * @return
    // */
    //@RequestMapping("fanout")
    //@ResponseBody
    //public AjaxResult fanout() {
    //    testFanoutSender.send();
    //    return AjaxResult.success();
    //}

}
