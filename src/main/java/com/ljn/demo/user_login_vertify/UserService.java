package com.ljn.demo.user_login_vertify;

import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljn
 * @since 2022-04-28
 */
public interface UserService extends IService<User> {

    User getUserFromRedis(HttpServletRequest request, HttpServletResponse response, String userTicket);
}
