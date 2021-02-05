package com.zhzh.util.model;

/**
 * @Author: yoyo
 * @Description: 错误码统一接口，各服务实现其接口，详见{@code RcsManageStatusCode}
 * @Date: 2019/12/9 17:37
 * @Version: 1.0
 */
public interface GlobalStatusCode {
    /**
     * 获取状态码
     * @return
     */
    int getStatusCode();

    /**
     * 获取消息
     * @return
     */
    String getMessage();
}
