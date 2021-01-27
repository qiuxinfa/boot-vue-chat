package com.qxf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MySecurityConfig
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/1/20 21:13
 **/
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    //密码加密
    @Bean
    PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }

//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("123456"));
//    }

    //登录验证
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            // httpMethod options
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 放行 登录、登出、注册、验证码、检查用户名是否重复
            .antMatchers("/auth/**","/user/checkUsername").permitAll()
            // 文件相关
            .antMatchers("/api/file/**","/file").permitAll()
            // 放行 webSocket
            .antMatchers("/chat/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()//关闭csrf防御方便调试
            //没有认证时，返回401
            .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                @Override
                public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                    httpServletResponse.setStatus(401);
                }
            });
    }

}
