/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.configs;

import com.htt.filters.CustomAccessDeniedHandler;
import com.htt.filters.JwtAuthenticationTokenFilter;
import com.htt.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.htt.controllers",
    "com.htt.repository",
    "com.htt.service",
    "com.htt.components"})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeRequests().antMatchers("/api/courses/").permitAll();
        http.authorizeRequests().antMatchers("/api/teachers/**").permitAll();
        http.authorizeRequests().antMatchers("/api/categories/**").permitAll();
        http.authorizeRequests().antMatchers("/api/assignment/**").permitAll();
        http.authorizeRequests().antMatchers("/api/lesson-delete/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/courseRating/**").permitAll();
        http.authorizeRequests().antMatchers("/api/users/**").permitAll();
//       http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/comments/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/receipts/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.GET, "/api/receipt/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.GET, "/api/assignments/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.GET, "/api/lecturer/**").access("hasRole('ROLE_TEACHER')")
                .antMatchers(HttpMethod.GET, "/api/lessons/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.GET, "/api/essays/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.GET, "/api/score/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.GET, "/api/certification/**").access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.GET, "/api/notifications/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.GET, "/api/answerchoices/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/notifications/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/certification/**").access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/courseRating/**").access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/lecturer/**").access("hasRole('ROLE_TEACHER')")
                .antMatchers(HttpMethod.POST, "/api/choices/**").access("hasRole('ROLE_TEACHER')")
                .antMatchers(HttpMethod.POST, "/api/questions/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/answerchoices/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/essays/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/score/**").access("hasRole('ROLE_TEACHER') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.formLogin()
                .loginPage("/login") // Cung cấp trang đăng nhập tùy chỉnh
                .permitAll(); // Cho phép tất cả người dùng truy cập trang đăng nhập

        // Cấu hình các lỗi truy cập và xử lý ngoại lệ
        http.exceptionHandling()
                .accessDeniedPage("/403") // Trang lỗi 403 nếu người dùng không có quyền
                .authenticationEntryPoint(restServicesEntryPoint()) // Xử lý lỗi khi chưa đăng nhập
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Không duy trì trạng thái phiên

    }
}
