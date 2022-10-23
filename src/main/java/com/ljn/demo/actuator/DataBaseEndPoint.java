package com.ljn.demo.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 监控数据库的端点
@Component
// id为端点的名称
// 访问路径: ip:port/context path/acturator/database
// 可以通过security配置访问权限
@Endpoint(id = "database")
public class DataBaseEndPoint {
    @Autowired
    private DataSource dataSource;

    // 接收get请求
    @ReadOperation
    @ResponseBody
    public R testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return R.success().msg("数据库连接成功");
        } catch (SQLException throwables) {
            return R.error().msg("数据库连接失败");
        }
    }
}
