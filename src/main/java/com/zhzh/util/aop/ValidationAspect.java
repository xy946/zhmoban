package com.zhzh.util.aop;


import com.zhzh.constants.Constants;
import com.zhzh.util.exception.GlobalException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Before("execution(* com.zhzh..*.controller.*Controller.*(..)) && args(.., bindingResult)")
    public void validateAccount(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            StringBuffer errorMsg = new StringBuffer("参数错误：");
            for (int i = 0; i < objectErrors.size(); i++) {
                ObjectError error = objectErrors.get(i);
                String name = "";
                /* 对象级别的错误，无须显示对象名；
                 * 字段级别的错误，则显示字段名 */
                if (error instanceof FieldError) {
                    name = ((FieldError) error).getField();
                }
                String message = error.getDefaultMessage();
                errorMsg.append(String.format("%s%s;", name, message));
            }
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            LOGGER.info(errorMsg.toString());
            throw new GlobalException(Constants.PARAM_ERROR, errorMsg.toString());
        }
    }

}
