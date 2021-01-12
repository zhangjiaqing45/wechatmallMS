package com.fante.project.demo.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.redis.RedisUtil;
import com.fante.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Fante
 * @Date: 2019/12/23 14:41
 * @Author: Mr.Z
 * @Description: 集成Redis
 */
@Controller
@RequestMapping("/demo/integrate/redis")
public class DemoRedisController {

    private final String prefix = "demo/integrate/redis";

    private final String KEY_PREFIX = "demo:test::";

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/redis")
    public String redisPage(ModelMap modelMap) {
        return prefix + "/redis";
    }


    @RequestMapping("/redisSet")
    @ResponseBody
    public AjaxResult redisSet(TestUser user, long time) {
        user.validate();
        Checker.check(ObjectUtils.isEmpty(time), "未设置过期时间");
        redisUtil.set(KEY_PREFIX + user.getId(), user, time);
        return AjaxResult.success("设置成功");
    }


    @RequestMapping("/redisGet")
    @ResponseBody
    public AjaxResult redisGet(String id) {
        String key = KEY_PREFIX + id;
        Checker.checkOp(redisUtil.hasKey(key), "ID：[" + id + "]未在Redis缓存中");
        TestUser user = (TestUser) redisUtil.get(key);
        return AjaxResult.success().put("user", user);
    }

    @RequestMapping("/getRedisExpire")
    @ResponseBody
    public AjaxResult getRedisExpire(String id) {
        String key = KEY_PREFIX + id;
        long ex = redisUtil.getExpire(key);
        return AjaxResult.success().put("ex", ex);
    }

}


class TestUser {

    private String id;
    private String name;
    private int age;

    public TestUser() {
    }

    public TestUser(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void validate(){
        Checker.check(StringUtils.isBlank(getId()), "未填写ID");
        Checker.check(StringUtils.isBlank(getName()), "未填写姓名");
        Checker.check(ObjectUtils.isEmpty(age), "未填写年龄");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
