package com.ljn.demo.config.web;

import com.ljn.demo.user_login_vertify.User;
import com.ljn.demo.user_login_vertify.UserService;
import com.ljn.demo.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class UserLoginVerifyInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        User user = userService.getUserFromRedis(request, response, userTicket);
        if (user == null) {
            return false;
        }
        UserContext.setUser(user);
        return true;
    }
}
