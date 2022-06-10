package com.ljn.demo.security.temp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ljn.demo.security.common.R;
import com.ljn.demo.security.common.REnum;
import com.ljn.demo.security.entity.User;
import com.ljn.demo.security.entity.UserDetailsImpl;
import com.ljn.demo.security.utils.JwtUtil;
import com.ljn.demo.security.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ljn
 * @since 2022-05-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    // 在配置类中已经创建了AuthenticationManager对象
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public R login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication == null) {
            throw new RuntimeException("登录失败");
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String userId = userDetails.getUser().getId().toString();
        String userName = userDetails.getUser().getUserName();
        redisUtil.setCacheObject("login:"+userId, userDetails);
        String token = JwtUtil.createJwtToken(userId, userName);
        return R.success().codeAndMsg(REnum.LOGIN_SUCCESS).data(token);
    }

    @Override
    public R logout() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = userDetails.getUser().getId().toString();
        boolean flag = redisUtil.deleteObject("login:"+id);
        if (flag) {
            return R.success().codeAndMsg(REnum.LOGOUT_SUCCESS);
        }
        return R.success().codeAndMsg(REnum.LOGOUT_ERROR);
    }
}
