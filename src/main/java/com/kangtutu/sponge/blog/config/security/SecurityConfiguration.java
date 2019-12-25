package com.kangtutu.sponge.blog.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
@EnableWebSecurity //开启security自动配置
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //密码解析器注入到spring中，用于对密码进行加密解密处理
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 资源请求权限校验
     * authorizeRequests() 开启资源请求拦截
     * antMatchers() 填写需要拦截的资源
     * hasAnyAuthority() 访问对应的链接地址需要的用户权限
     * fullyAuthenticated() 表明所有资源都需要认证才能访问
     * csrf().disable() 关闭跨站请求
     * formLogin() 使用表单登录
     * httpBasic() 开启httpBasic的验证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/","/blog/**","/login").permitAll()
            .antMatchers("/bg/**").fullyAuthenticated()
            .and()
            .formLogin()
                //.usernameParameter("uname")//自定义表单中用户名的属性名
                //.passwordParameter("pwd")//自定义表单中密码的属性名
                //.loginPage("/")//跳转到自定义的登录表单
                //.loginProcessingUrl("/")//自定义登录请求的处理路径，不配置此值则默认使用loginPage()中配置的路径
                //.defaultSuccessUrl("/")//自定义登陆成功跳转到的页面
                //.failureUrl("/")//自定义登录失败跳转到的页面
            //.and()
            //.logout()//开启注销功能
                //.logoutSuccessUrl("/login")//注销成功后跳转到的页面
            //.and()
            //.rememberMe()//开启记住我功能
                //.rememberMeParameter("remember")//自定义表单中记住我的属性名
            .and()
            .csrf().disable();
    }


}
