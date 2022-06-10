package com.ljn.demo.security.filter;


import com.ljn.demo.security.common.R;
import com.ljn.demo.security.common.REnum;
import com.ljn.demo.security.entity.UserDetailsImpl;
import com.ljn.demo.security.utils.JwtUtil;
import com.ljn.demo.security.utils.RedisUtil;
import com.ljn.demo.security.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 直接实现Filter，可能会多次执行该过滤器
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中得到token
        // 如果没有token，说明该接口可能不需要登录，可以先放行
        // 如果确实需要登录的话，后面的过滤器也会进行拦截
        String jwtToken = request.getHeader("token");
        if (!StringUtils.hasText(jwtToken)) {
            filterChain.doFilter(request, response);
            // 必须要有return，否则后面的过滤器执行完还会接着往下执行
            return;
        }
        if (!JwtUtil.checkToken(jwtToken)) {
            ResponseUtil.out(response, R.error().codeAndMsg(REnum.NEED_LOGIN));
            return;
        }
        String id = JwtUtil.getIdFromJwtToken(request);

        UserDetailsImpl userDetails = redisUtil.getCacheObject("login:"+id);
        if (userDetails == null) {
            // 这里抛出的异常是不会被统一异常捕获到的，因为请求还没达到controller
            // throw new RuntimeException("用户未登录");
            ResponseUtil.out(response, R.error().codeAndMsg(REnum.AUTHENTICATION_ERROR));
            return;
        }
        // 登录以前的认证需要两个参数的，登录以后的认证需要用三个参数的，三个参数的会将状态设置为已认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        // 一个请求对应一个SecurityContextHolder，内部有一个ThreadLocal
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
