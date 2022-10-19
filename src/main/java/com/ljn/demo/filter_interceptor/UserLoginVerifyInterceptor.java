package com.ljn.demo.filter_interceptor;

import com.ljn.demo.user_login_vertify.User;
import com.ljn.demo.user_login_vertify.UserService;
import com.ljn.demo.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class UserLoginVerifyInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    // controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        User user = userService.getUserFromRedis(request, response, userTicket);
        if (user == null) {
            return false;
        }
        UserContextHolder.setUser(user);
        return true;
    }

    // controller处理完成之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    // 模板引擎之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.removeUser();
    }
}
