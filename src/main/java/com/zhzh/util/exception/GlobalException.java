package com.zhzh.util.exception;


import com.zhzh.util.model.GlobalStatusCode;
import com.zhzh.util.model.ResultVO;

/**
 * Date:        2017年5月31日 下午2:53:36
 * Author:     cncbox
 * Version:     1.0.0.0
 * Description: 参数验证异常类
 */
public class GlobalException extends RuntimeException {
    
    private static final long serialVersionUID = -1178522679073184111L;
    
    private int statusCode;
    private String message;
    
    
    public GlobalException() {
        super();
    }

    public GlobalException(GlobalStatusCode status) {
        super();
        this.statusCode = status.getStatusCode();
        this.message = status.getMessage();
    }

    public GlobalException(GlobalStatusCode status,String... s) {
        super();
        this.statusCode = status.getStatusCode();
        this.message = status.getMessage();
        if(s!=null){
            for(String _s:s){
                this.message = this.message.replace("{}",_s);
            }
            //this.message = MessageFormatter.arrayFormat(message, s).getMessage();
        }
    }

    public GlobalException(GlobalStatusCode status,String s) {
        super();
        this.statusCode = status.getStatusCode();
        this.message = status.getMessage();
        if(s!=null&&s.equals("")){
            this.message=s;
        }
    }
    
    public <T> GlobalException(ResultVO<T> result) {
        this(result.getCode(),result.getMsg());
    }

    public GlobalException(int statusCode, String message,String... s) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        if(s!=null){
            for(String _s:s){
                this.message = this.message.replace("{}",_s);
            }
        }
    }

    public GlobalException(String message) {
        super();
        this.statusCode = 999;
        this.message = message;
    }

    public GlobalException(int statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

