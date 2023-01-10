package com.Shiroha.coronavirus.config;

import com.Shiroha.coronavirus.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private MyAccessDenied myAccessDenied;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/index,/to").permitAll()
                .antMatchers("/manager/**/","/patient/**","/touch/**","/cure/**","/dead/**","/supplyManage","/harea/**").hasRole("Admin");

        http.exceptionHandling().accessDeniedHandler(myAccessDenied);

        //开启自动配置的登录功能
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login");
        //关闭csrf保护功能
        http.csrf().disable();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        //开启记住我功能
    }

    //定制认证页面
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //连接数据库。
        auth.authenticationProvider(authenticationProvider());
    }

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(myUserDetailService);
        return  authenticationProvider;
    }
}
