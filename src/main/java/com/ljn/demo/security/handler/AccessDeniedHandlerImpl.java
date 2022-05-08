package com.ljn.demo.security.handler;

import com.ljn.demo.security.common.R;
import com.ljn.demo.security.common.REnum;
import com.ljn.demo.security.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
//        这里抛出的异常不会被全局异常处理器捕获
//        throw new SecurityException(REnum.AUTHORITY_ERROR);
        ResponseUtil.out(response, R.error().codeAndMsg(REnum.AUTHORITY_ERROR));
    }
}
