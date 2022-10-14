package com.ljn.demo.spring_security.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class VerificationCodeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 只有登录请求才判断
        if (request.getServletPath().equals("处理登录请求的路径")) {
            String verifyCode = request.getParameter("verifyCode");
            if (verifyCode == null || verifyCode.equalsIgnoreCase("真实验证码")) {
                // 响应错误信息
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
