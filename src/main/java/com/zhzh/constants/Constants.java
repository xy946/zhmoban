package com.zhzh.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class Constants {

    //新增
    public final static String METHOD_ADD = "add";

    //查询单条
    public final static String METHOD_INFO = "info";

    //修改
    public final static String METHOD_UPDATE = "update";

    //条件分页查询List
    public final static String METHOD_LIST = "list";

    //删除
    public final static String METHOD_DEL = "del";

    //参数错误状态码
    public final static int PARAM_ERROR = 999;

    //redis  缓存时间
    public  final static int  REDIS_TIME_OUT = 60*30;

    //员工初始密码
    public final static String PASSWORD="123456";

    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String MM月dd日 = "MM月dd日";

    public final static String YYYY = "yyyy";

    /****************************************************************File相关********************************************************************************************************/
    //文件上传保存路径
    @Value("${file.path}")
    public String FILE_PATH ;

    //文件访问头地址
    @Value("${file.head.url}")
    public String FILE_HEAD_URL;

    /****************************************************************Ftp相关********************************************************************************************************/

    //账号
    @Value("${ftp.account}")
    public String FTP_ACCOUNT;

    //密码
    @Value("${ftp.password}")
    public String FTP_PASSWORD;

    //端口
    @Value("${ftp.port}")
    public int FTP_PORT;

    //ip地址
    @Value("${ftp.server}")
    public  String FTP_SERVERIP;

}
