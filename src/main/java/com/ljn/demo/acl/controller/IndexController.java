package com.ljn.demo.acl.controller;

import com.alibaba.fastjson.JSONObject;

import com.ljn.demo.acl.common.R;
import com.ljn.demo.acl.service.IndexService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 从安全上下文中获取用户信息
     */
    @GetMapping("info")
    public R info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取当前登录用户所具有的菜单列表
     * @return
     */
    @GetMapping("menu")
    public R getMenu(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return R.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }

}
