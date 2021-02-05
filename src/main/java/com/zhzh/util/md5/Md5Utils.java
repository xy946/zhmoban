package com.zhzh.util.md5;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @author : zhang sq
 * @date : 2020/8/28 16:36
 **/
public class Md5Utils {

    //加密
    public static String createMd5Code(String code) {
        return DigestUtils.md5Hex(code);
    }
    //加密+加盐
    public static String createMd5Code(String code,String salt) {
        return DigestUtils.md5Hex(code+salt);
    }

    //进行密码校验
    public static boolean checkPassword(String userCode, String dbCode) {
        if (dbCode.equals(userCode)) {
            return true;
        } else {
            return false;
        }
    }

    //获取随机盐值
    public static String getSalt() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return  uuid.substring(0, 5);
    }
}
