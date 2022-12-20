# 使用说明
## 认证过程
### 以下在登录接口中实现
1. 通过username和password创建一个UsernamePasswordAuthenticationToken
2. 在SecurityConfig中将AuthenticationManager对象注入spring容器，调用authenticate(authenticationToken)执行过滤器链返回认证结果，如果认证结果为空，则登录失败
3. 编写UserDetailsService实现类，实现loadUserByUsername函数，通过数据库查询得到用户的基本信息和权限信息，封装到UserDetails中返回即可
4. 从认证结果中得到登录用户的详细信息
5. 将用户的详细信息保存到redis中
6. 将用户的id、用户名保存到token中返回给前端

## 授权过程
### 以下在JwtAuthenticationFilter中实现
1. 从请求头中得到token
2. 从token中得到用户id
3. 通过用户id从redis中得到用户的详细信息
4. 将用户详细信息封装到UsernamePasswordAuthenticationToken中
5. 将UsernamePasswordAuthenticationToken放到安全上下文中

## 在SecurityConfig中添加配置
1. 配置权限管理
2. 配置鉴权(JwtAuthenticationFilter)过滤器
3. 配置认证或授权失败处理器
4. 密码加密方式
5. 跨域、跨站请求伪造、退出处理逻辑