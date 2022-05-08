package com.ljn.demo.security.temp;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljn.demo.security.common.R;
import com.ljn.demo.security.entity.User;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ljn
 * @since 2022-05-05
 */
public interface UserService extends IService<User> {

    R login(User user);

    R logout();
}
