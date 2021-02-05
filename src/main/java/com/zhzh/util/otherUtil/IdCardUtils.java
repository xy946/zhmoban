package com.zhzh.util.otherUtil;

import java.util.regex.Pattern;

/**
 * 简易身份证效验
 * Author xiaoyu
 * Date  2020-08-18 14:00
 */
public class IdCardUtils {


    /**
     * 身份证基础验证
     * @param idCard
     * @return
     */
    public static boolean isCardId(String idCard){
        return Pattern.matches("^[1-9]\\d{5}(19|([22]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",idCard);
    }

    /**
     * 根据身份证获取性别
     * @param idCard
     * @return
     */
    public static int getIdSex(String idCard){
        int sex = 2;
        if (Integer.parseInt(idCard.substring(16).substring(0, 1)) % 2 == 0) {
            sex = 1;
        } else {
            sex = 0;
        }
        return sex;
    }
}
