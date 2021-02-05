package com.zhzh.util.otherUtil;

import com.zhzh.util.model.ResultVO;
import com.zhzh.util.model.StatusCode;

/**
 * Author xianyu
 * Date  2020-08-18 14:00
 */
public class serviceUtils {

    /**
     * 返回模板
     * @param num  受影响行数
     * @return
     */
    public static ResultVO returnTempl(int num){
        return (num==0?new ResultVO(StatusCode.DATA_ERROR):new ResultVO(StatusCode.RESULT_SUCCESS));
    }

    /**
     * 返回模板
     * @param num   受影响的行数
     * @param code
     * @param message
     * @return
     */
    public static ResultVO returnTempl(int num,int code,String message){
        return (num==0?new  ResultVO(StatusCode.RESULT_SUCCESS):new ResultVO(code,message));
    }

}
