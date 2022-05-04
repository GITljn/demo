# 通过两种方式减少controller中判断用户是否登录的代码
## 参数解析器
对含有满足要求的参数的接口进行拦截，返回参数需要的类型。无论是否登录请求都会到达controller，需要在controller中判断用户是否登录
## 拦截器
如果用户登录，就将用户状态信息借助ThreadLocal保存到线程的私有空间，否则请求不会到达controller
### 来源
seckill项目