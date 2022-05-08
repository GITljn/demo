package com.ljn.demo.security.handler;


import com.ljn.demo.security.common.R;
import com.ljn.demo.security.common.REnum;
import com.ljn.demo.security.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//        这里抛出的异常不会被全局异常处理器捕获
//        throw new SecurityException(REnum.AUTHENTICATION_ERROR);
        ResponseUtil.out(response, R.error().codeAndMsg(REnum.AUTHENTICATION_ERROR));
    }
}
