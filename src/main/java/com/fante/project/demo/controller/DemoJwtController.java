package com.fante.project.demo.controller;

import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.framework.jwt.enums.JwtSource;
import com.fante.framework.jwt.utils.JwtUtils;
import com.fante.framework.jwt.domain.JwtToken;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 集成JWT测试
 */
@Controller
@RequestMapping("/demo/jwt")
public class DemoJwtController extends BaseController {

    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping("testInfo")
    @ResponseBody
    public AjaxResult testInfo() {
        return AjaxResult.success("这是测试信息，不进行Token校验！");
    }


    @RequestMapping("login")
    @ResponseBody
    public AjaxResult login(String username, String password, String clientId) {
        TestJwtUser user = doLogin(username, password);
        if (ObjectUtils.isEmpty(user)) {
            return AjaxResult.error("模拟登录失败！");
        }
        clientId = StringUtils.isBlank(clientId) ? "auto:" + IdGenerator.nextId() : clientId;
        String token = jwtUtils.createToken(new JwtToken(user.getId(), clientId, JwtSource.PC.getType()));


        return AjaxResult.success("模拟登录成功！").put("token", token);
    }


    @RequestMapping("authInfo")
    @ResponseBody
    public AjaxResult authInfo() {
        return AjaxResult.success("这是登陆后显示的信息！")
                .put("userId", getTokenUserId())
                .put("clientId", getTokenClientId());
    }


    private TestJwtUser doLogin(String username, String password) {
        if (Objects.equals(username, "jwtadmin") && Objects.equals(password, "jwt123456")) {
            return new TestJwtUser(9527L, username, password);
        } else {
            return null;
        }
    }


}

class TestJwtUser {

    private long id;
    private String username;
    private String password;

    public TestJwtUser() {
    }

    public TestJwtUser(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
