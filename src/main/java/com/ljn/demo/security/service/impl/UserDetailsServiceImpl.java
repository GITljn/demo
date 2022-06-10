package com.ljn.demo.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.ljn.demo.security.entity.User;
import com.ljn.demo.security.entity.UserDetailsImpl;
import com.ljn.demo.security.temp.MenuMapper;
import com.ljn.demo.security.temp.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

// 交给spring容器之后，security自己可以找到，无需配置（也有配置的地方）
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在");
        }
//        List<String> authorities = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> authorities = menuMapper.getPermissionsByUserId(user.getId());
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUser(user);
        userDetails.setPermissions(authorities);

        return userDetails;
    }
}
