package com.ai.wxy.frame.springboot.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ai.wxy.frame.springboot.security.MyPasswordEncoder;
import com.ai.wxy.frame.springboot.security.UserDetailsServiceImpl;
@Configuration
public class WebSecurityConf extends WebSecurityConfigurerAdapter{
    @Value("spring.security.secret")
    private String pwdSecret;
    @Bean
    UserDetailsService cutomUserService(){
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //super.configure(auth);
        auth.userDetailsService(cutomUserService()).passwordEncoder(new MyPasswordEncoder(pwdSecret));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http
        .authorizeRequests()
        .antMatchers("/hello","/login","/login2","/home","/").permitAll()
        .antMatchers("/user/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .failureUrl("/login?error")
        .and()
        .logout().permitAll();
    }
    
    
}
