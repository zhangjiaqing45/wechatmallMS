//package com.fante.framework.web.exception;
//
//import com.fante.framework.web.domain.AjaxResult;
//import com.google.common.base.Strings;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
///**
// * @program: Fante
// * @Date:
// * @Author: Mr.Z
// * @Description: 过滤器filter错误处理
// */
//
//@RestController
//public class ErrorController extends BasicErrorController {
//
//    public ErrorController() {
//        super(new DefaultErrorAttributes(), new ErrorProperties());
//    }
//
//    @Override
//    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
//        HttpStatus status = getStatus(request);
//        //自定义的错误信息类
//        //status.value():错误代码，
//        //body.get("message").toString()错误信息
//        AjaxResult ret = AjaxResult.error(status.value(), body.get("message").toString());
//        //TokenException Filter抛出的自定义错误类
//        if (!Strings.isNullOrEmpty((String) body.get("exception"))) {
//            body.put("status", HttpStatus.FORBIDDEN.value());
//            status = HttpStatus.FORBIDDEN;
//        }
//        return new ResponseEntity<Map<String, Object>>(ret, status);
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "error/500.html";
//    }
//
//}
//
