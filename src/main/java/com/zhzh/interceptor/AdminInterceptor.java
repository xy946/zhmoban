package com.zhzh.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName: AdminInterceptor
 * @Description:拦截器
 * @author: xianyu
 * @date: 2020-07-07
 */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //验证token
       // log.info("执行了AdminInterceptor的preHandle方法)");
        try {
            //不存在重定向错误页面
            String token = request.getHeader("Authorization");
            if(1==1){
                return true;
            }
            if (StringUtils.isEmpty(token)) {
                response.sendRedirect(request.getContextPath() + "/tokenError");
                return false;
            } else {
                // 过期验证
                Boolean exists = redisTemplate.hasKey(token);
                if (!exists) {
                    //过期或者不存在token时 返回error
                    log.error("token超时)");
                    response.sendRedirect(request.getContextPath() + "/tokenError");
                    return false;
                }else {
                    //重置token时间
                    Object value = redisTemplate.opsForValue().get(token);
                    redisTemplate.opsForValue().set(token, value, 30*60, TimeUnit.SECONDS);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}