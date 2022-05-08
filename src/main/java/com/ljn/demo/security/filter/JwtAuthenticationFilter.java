package com.ljn.demo.security.filter;


import com.ljn.demo.security.entity.UserDetailsImpl;
import com.ljn.demo.security.utils.JwtUtil;
import com.ljn.demo.security.utils.RedisUtil;
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
    private RedisUtil redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String id = JwtUtil.getIdByJwtToken(request);
        if (!StringUtils.hasText(id)) {
            filterChain.doFilter(request, response);
            // 必须要有return，否则后面的过滤器执行完还会接着往下执行
            return;
        }
        UserDetailsImpl userDetails = redisCache.getCacheObject("login:"+id);
        if (userDetails == null) {
            throw new RuntimeException("用户未登录");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        // 一个请求对应一个SecurityContextHolder，内部有一个ThreadLocal
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
