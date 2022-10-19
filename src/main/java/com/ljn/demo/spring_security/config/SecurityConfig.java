package com.ljn.demo.spring_security.config;

import com.ljn.demo.spring_security.entity.User;
import com.ljn.demo.spring_security.entity.UserDetailsImpl;
import com.ljn.demo.spring_security.filter.JwtAuthenticationFilter;
import com.ljn.demo.spring_security.filter.VerificationCodeFilter;
import com.ljn.demo.spring_security.temp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Objects;

@Configuration
// 开启方法上的安全注解（方法前判断权限和方法后判断权限）
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private VerificationCodeFilter verificationCodeFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // 认证管理
    // AuthenticationManager: 认证的核心接口
    // AuthenticationManagerBuilder: 用于构建AuthenticationManager对象的工具类
    // ProviderManager: AuthenticationManager的默认实现类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 内置的认证规则
        // 获得用户以及加密的方式
        // auth.userDetailsService().passwordEncoder()

        // 自定义认证规则
        // 自定义认证规则可以考虑用户的状态(如禁用)
        // AuthenticationProvider: ProviderManager持有一组AuthenticationProvider,
        // 每个AuthenticationProvider负责一种认证类型(账号密码登录、第三方登录、指纹登录、刷脸登录)
        // 委托模式: ProviderManager将认证过程委托给AuthenticationProvider
        auth.authenticationProvider(new AuthenticationProvider() {
            // Authentication: 用于封装认证信息的接口(用户名、密码等), 不同的实现类代表不同的认证信息
            // UserServiceImpl中Authentication authentication = authenticationManager.authenticate(authenticationToken)
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String username = authentication.getName();
                // 因为不一定是使用密码登录，所以获取的是凭证信息
                String password = (String) authentication.getCredentials();
                User user = userService.findUserByUsername(username);
                // 1. 判断用户是否存在
                if (Objects.isNull(user)) {
                    // 后面有专门的filter捕获到异常
                    throw new UsernameNotFoundException("用户名不存在");
                }
                // 2. 判断用户状态是否正常
                if (user.getStatus().equals("0")) {
                    throw new RuntimeException("用户状态异常");
                }
                // 3. 判断密码是否正确, 判断前要对密码进行加密处理
                if (!user.getPassword().equals(passwordEncoder.encode(password))) {
                    throw new BadCredentialsException("密码错误");
                }
                // 将用户信息和权限信息放入UserDetails实现类中
                UserDetailsImpl userDetails = new UserDetailsImpl();
                userDetails.setUser(user);
                // userDetails.setPermissions();

                // 登录以前的认证需要两个参数的，登录以后的认证需要用三个参数的，
                // 三个参数的会将状态设置为已认证，因此是否传入凭证均可
                return new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getPassword(), userDetails.getAuthorities());
            }

            // 当前的AuthenticationProvider支持的认证类型
            @Override
            public boolean supports(Class<?> authentication) {
                // UsernamePasswordAuthenticationToken: 是Authentication的一种实现类，封装的认证信息包含用户名密码等
                return UsernamePasswordAuthenticationToken.class.equals(authentication);
            }
        });
    }

    // 权限管理
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf，关闭之后security不会验证前端传来的csrf令牌
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 配置访问的权限，访问具体路径需要的权限或者角色一般是通过注解设置的@hasAuthority
                //.antMatchers("").permitAll()
                // 必须匿名访问
                .antMatchers("/user/login").anonymous()
                //.antMatchers("").hasRole("")
                //.antMatchers("").hasAnyRole("")
                //.antMatchers("").hasAuthority("")
                //.antMatchers("").hasAnyAuthority("")
                // 除上面外的所有请求，认证之后可以访问
                .anyRequest().authenticated();

        // 配置过滤器
        // 将认证过滤器放到UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加检查验证码的过滤器
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理器
        http.exceptionHandling()
                // 认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                // 授权失败处理器
                .accessDeniedHandler(accessDeniedHandler);
        // 允许跨域
        http.cors();
        // 默认拦截/logout，关闭默认的logout就不会拦截
        http.logout().disable();
    }


    // 指定密码加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 将AuthenticationManager(认证链的起始对象)交给spring容器
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
