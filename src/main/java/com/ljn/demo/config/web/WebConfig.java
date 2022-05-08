package com.ljn.demo.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

// 添加mvc配置
@Configuration
// 开启后，默认的mvc不再生效，统一在这里配置，不要开启
// @EnableWebMvc
/**
 * 主要配置包括：参数解析器、拦截器、资源处理器、跨域处理器
 */
public class WebConfig implements WebMvcConfigurer {
    /*
    @Autowired
    private UserArgumentResolver userArgumentResolver;
    @Autowired
    private UserLoginVerifyInterceptor userLoginVerifyInterceptor;

     */
    // 添加允许跨域的路径，解决springboot跨域，与CrossOrgin注解类似
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }


    // 添加参数解析器，会对含有满足条件的参数的接口(函数)进行拦截
    /*
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

     */

    // 添加拦截器，在请求到达controller之前执行
    /*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginVerifyInterceptor)
                .excludePathPatterns("/login/toLogin")
                .excludePathPatterns("/login/doLogin")
                // 不能使用"/static/**"
                .excludePathPatterns("/bootstrap/**",
                        "/img/**", "/jquery-validation/**", "/js/**", "/layer/**");
    }

     */

    /*
    // 添加资源处理器，路径匹配，寻找资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 匹配路径，满足该模式的路径会到下面的位置找
        registry.addResourceHandler("/**")
                // 资源的地址
                .addResourceLocations("classpath:static/");
    }
     */
}
