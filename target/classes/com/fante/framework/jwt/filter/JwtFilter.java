package com.fante.framework.jwt.filter;

import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.spring.SpringUtils;
import com.fante.framework.jwt.enums.JwtEnum;
import com.fante.framework.jwt.utils.JwtUtils;
import com.fante.framework.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: JWT验证过滤器
 */
public class JwtFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private JwtUtils jwtUtils = SpringUtils.getBean(JwtUtils.class);

    private String jwtHeader;

    public JwtFilter(String jwtHeader) {
        this.jwtHeader = jwtHeader;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(jwtHeader);
        log.info("URI: {}, Token: {}", req.getRequestURI(), token);
        JwtEnum jwtEnum = jwtUtils.verifyToken(token);
        if (!jwtEnum.isPass()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            AjaxResult result = AjaxResult.error(AjaxResult.Type.JWTFAIL.value(), jwtEnum.getMsg());
            response.getWriter().print(BeanUtils.beanToString(result));
            return;
        }
        filterChain.doFilter(request, response);
    }
}
