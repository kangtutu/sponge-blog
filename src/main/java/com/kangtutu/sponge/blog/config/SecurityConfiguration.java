package com.kangtutu.sponge.blog.config;

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
            //.antMatchers("/blog/**").hasAnyAuthority("CALL")
            .antMatchers("/**").fullyAuthenticated()
            .and()
            //.formLogin()
                .httpBasic()
            .and()
            .csrf().disable();

    }

    //认证信息相关
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("kangtutu").password(passwordEncoder().encode("123456")).authorities("ALL");
    }
}
