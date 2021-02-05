package com.zhzh.util.model;

/**
 * Date:        2020/08/01
 * Author:      yzc
 * Version:     1.0.0.0
 * Description: TO DO
 */
public enum StatusCode implements GlobalStatusCode {

    RESULT_SUCCESS(200, "SUCCESS"),
    DATA_ERROR(500 , "数据错误"),
    QUERY_PARAM_ERROR(201 , "参数错误"),
    UPLOAD_FILE_ERROR(202 , "上传文件失败"),
    REPEAT_ERROR(204 , "用户已存在"),
    REPETITION_ERROR(205 , "类型和名字已存在"),
    TOKEN_ERROR(401 , "token异常"),
    REDIS_ERROR(207 , "redis异常"),
    OPERA_DATA_NULL(208,"操作数据不存在"),
    DATA_NULL(209,"查询数据为空"),
    SYSTEM_EXCEPTION(999, "系统异常");
    private int statusCode;
    private String message;

    StatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
