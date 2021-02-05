package com.zhzh.config;


import com.zhzh.interceptor.AdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *
 *
 * @ClassName: LoginConfig
 * @Description:拦截器配置
 * @author: xianyu
 * @date:  2020-07-07
 */
@Configuration
@Slf4j
public class LoginConfig implements WebMvcConfigurer {

    @Bean
    public AdminInterceptor adminInterceptor(){
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("进入拦截器)");
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(adminInterceptor());
        //所有路径都被拦截
        //registration.addPathPatterns("/**");
        //添加不拦截路径
        registration.excludePathPatterns(
                "/applogin",
                "/login",
                "/tokenError",
                "/common/**",
                "/**/*.html",
                "/**/*.js",
                "/**/*.css"
        );
    }
}