package com.ljn.demo.config.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 在Gateway中进行跨域处理就不需要在其他服务中单独处理了
 * 1.浏览器在跨域请求时会在正式通信之前发送一个预检请求，请求方式为options
 * 2.服务器返回允许跨域的源地址、请求头、请求方法
 * 3.浏览器向服务器发出正式的请求
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        // 跨域配置对象，存放允许的源地址、头、请求的方法
        CorsConfiguration config = new CorsConfiguration();
        // 允许任何主机访问（域名或IP+Port）
        config.addAllowedOrigin("*");
        // 允许请求头中携带任何key和value
        config.addAllowedHeader("*");
        // 允许任何方法（post、get等）
        config.addAllowedMethod("*");
        //允许携带cookie访问（跨域默认不允许携带cookie）
        config.setAllowCredentials(true);
        // 这些配置的有效时间
        config.setMaxAge(100000L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        // 配置拦截的请求路径（拦截所有请求）
        source.registerCorsConfiguration("/**", config);

        // spring提供的解决跨域的过滤器
        return new CorsWebFilter(source);
    }
}

