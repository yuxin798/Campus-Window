//package com.campuswindow.config;
//
//import com.campuswindow.utils.CustomMd5PasswordEncoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Collections;
//
///**
// * Security配置类
// * @author 尹稳健~
// * @version 1.0
// * @time 2023/1/31
// */
//@Configuration
///**
// * @EnableWebSecurity是开启SpringSecurity的默认行为
// */
//@EnableWebSecurity
//public class SecurityConfig {
//
//    /**
//     * 密码明文加密方式配置
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new CustomMd5PasswordEncoder();
//    }
//
//    /**
//     * 获取AuthenticationManager（认证管理器），登录时认证使用
//     * @param authenticationConfiguration
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return  http
//                // 基于 token，不需要 csrf
//                .csrf().disable()
//                // 开启跨域以便前端调用接口
//                .cors().and()
//                .authorizeRequests()
//                // 指定某些接口不需要通过验证即可访问。登录接口肯定是不需要认证的
//                .antMatchers("/admin/system/index/login").permitAll()
//                // 静态资源，可匿名访问
//                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**","/doc.html").permitAll()
//                // 这里意思是其它所有接口需要认证才能访问
//                .anyRequest().authenticated()
//                .and()
//                // 基于 token，不需要 session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                // cors security 解决方案
//                .cors().configurationSource(corsConfigurationSource())
//                .and()
//                .build();
//    }
//
//    /**
//     * 配置跨源访问(CORS)
//     * @return
//     */
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//}
