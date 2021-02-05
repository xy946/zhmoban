package com.zhzh.util.exception;

import com.zhzh.util.model.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Date:        2017年6月1日 下午12:29:56
 * Author:      cncbox
 * Version:     1.0.0.0
 * Description: AOP 异常拦截类,使用时，需要在application中@Import({GlobalExceptionHandler.class})
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);

    private static final int UNKNOW_RESULT_CODE = 999;



    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public ResponseEntity<ResultVO> defaultErrorHandler(GlobalException e) {
        LOGGER.error("GlobalException:"+e.getMessage(), e);

        return ResponseEntity.ok(new ResultVO<>(e.getStatusCode(), e.getMessage()));
    }

    /**
     * 参数错误
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<String> defaultErrorHandler(HttpServletRequest req, HttpMessageNotReadableException e) throws Exception {
        LOGGER.error("HttpMessageNotReadableException:"+e.getMessage());

        return ResponseEntity.badRequest().body("params error");
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<ResultVO> defaultErrorHandler(Exception e) {
        LOGGER.error("Exception:"+e.getMessage(), e);

        return ResponseEntity.ok(new ResultVO(UNKNOW_RESULT_CODE, e.getMessage(), null));
    }


}

