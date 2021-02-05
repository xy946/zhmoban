package com.zhzh.util.otherUtil;


import com.alibaba.fastjson.JSONObject;
import com.zhzh.util.model.ResultVO;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class UserCommonsUtil {


    public static class MapUtils {


        /**
         * 拼接map
         */
        public static synchronized Map<String, Object> getParamMap(HttpServletRequest request) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            try {
                Map<String, String[]> paraMap = request.getParameterMap();
                Set<Entry<String, String[]>> set = paraMap.entrySet();
                Iterator<Entry<String, String[]>> it = set.iterator();
                while (it.hasNext()) {
                    String value = "";
                    Entry<String, String[]> entry = it.next();
                    for (String i : entry.getValue()) {
                        value = i;
                        break;
                    }
                    resultMap.put(entry.getKey(), value.trim());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultMap;
        }


        /**
         * 获取val
         */
        public static String getMapVal(Map<String, Object> paramMap, String key) {
            if (paramMap == null || key == null) {
                return "";
            }
            return paramMap.get(key) == null ? "" : paramMap.get(key).toString();
        }

    }


    public static class UuidUtil {
        /**
         * 获取uuid
         */
        public static String getUuid(int... maxLength) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            if (maxLength == null || maxLength.length == 0) {
                return uuid;
            }
            int size = maxLength[0] > 32 ? 32 : maxLength[0];
            uuid = uuid.substring(0, size);
            return uuid;
        }

        //根据指定长度生成纯数字的随机数
        public static String createData(int length) {
            StringBuilder sb = new StringBuilder();
            Random rand = new Random();
            for (int i = 0; i < length; i++) {
                sb.append(rand.nextInt(10));
            }
            String data = sb.toString();
            return data;
        }
    }

    /**
     * token工具类
     */
    public static class TokenUtil {

        //产生随机token
        public static String createToken(Integer userId) {
            String time ="userId="+userId +"-"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            return time + "-" + UuidUtil.getUuid(15);
        }

    }

        public static class Md5Util {

            private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
                    "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

            /**
             * 转换字节数组为16进制字串
             *
             * @param b 字节数组
             * @return 16进制字串
             */
            public static String byteArrayToHexString(byte[] b) {
                StringBuffer resultSb = new StringBuffer();
                for (int i = 0; i < b.length; i++) {
                    resultSb.append(byteToHexString(b[i]));
                }
                return resultSb.toString();
            }

        /**
         * 转换byte到16进制
         *
         * @param b
         * @return
         */
        private static String byteToHexString(byte b) {
            int n = b;
            if (n < 0) {
                n = 256 + n;
            }
            int d1 = n / 16;
            int d2 = n % 16;
            return hexDigits[d1] + hexDigits[d2];
        }

        /**
         * J 编码
         *
         * @param origin
         * @return
         */

        // MessageDigest 为 JDK 提供的加密类
        public static String MD5Encode(String origin) {
            origin = origin.trim();
            String resultString = null;
            try {
                resultString = new String(origin);
                MessageDigest md = MessageDigest.getInstance("md5");
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes("UTF-8")));
            } catch (Exception ex) {
            }
            return resultString;
        }

            public static void main(String[] args) {
                String s = "123456";
                System.out.println(Md5Util.MD5Encode(s));
            }

    }

    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static class EntityUtil{

        //实体类转map
        public static Map<String,Object> convertBeanToMap(Object bean) {
            Map<String,Object> returnMap = new HashMap<String, Object>();
            try{
                Class type = bean.getClass();
                BeanInfo beanInfo = Introspector.getBeanInfo(type);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    PropertyDescriptor descriptor = propertyDescriptors[i];
                    String propertyName = descriptor.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = descriptor.getReadMethod();
                        Object result = readMethod.invoke(bean, new Object[0]);
                        if (result != null) {
                            returnMap.put(propertyName, result);
                        } else {
                            returnMap.put(propertyName, "");
                        }
                    }
                }
                return returnMap;
            }catch (Exception e){
                e.printStackTrace();
                return returnMap;
            }
        }

        /**
         * set属性的值到Bean
         */
        public synchronized static <T> T setFieldValue(Object bean, Map <String, Object> valMap){
            //基类
            Class <?> cls = bean.getClass();
            //version
            Class <?> super_cls = bean.getClass().getSuperclass();

            // 取出bean里的所有方法
            Method[] base_methods = cls.getDeclaredMethods();
            Method[] super_methods = super_cls.getDeclaredMethods();


            Field[] base_fields = cls.getDeclaredFields();
            Field[] super_fields = super_cls.getDeclaredFields();

            //合并
            Method[] methods = concatAll(base_methods, super_methods);
            Field[] fields = concatAll(base_fields, super_fields);
            for(Field field : fields){
                try{
                    String fieldSetName = parMedName("set", field.getName());
                    if(!checkSetMet(methods, fieldSetName)){
                        continue;
                    }
                    Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
                    String fieldKeyName = field.getName();
                    String value = valMap.get(fieldKeyName) == null ?"" : valMap.get(fieldKeyName).toString();
                    if(null != value && !"".equals(value)){
                        String fieldType = field.getType().getSimpleName();
                        if("String".equals(fieldType)){
                            fieldSetMet.invoke(bean, value);
                        }else if("Date".equals(fieldType)){
                            Date temp = parseDate(value);
                            fieldSetMet.invoke(bean, temp);
                        }else if("Integer".equals(fieldType) || "int".equals(fieldType)){
                            Integer intval = Integer.parseInt(value);
                            fieldSetMet.invoke(bean, intval);
                        }else if("Long".equalsIgnoreCase(fieldType)){
                            Long temp = Long.parseLong(value);
                            fieldSetMet.invoke(bean, temp);
                        }else if("Double".equalsIgnoreCase(fieldType)){
                            Double temp = Double.parseDouble(value);
                            fieldSetMet.invoke(bean, temp);
                        }else if("Boolean".equalsIgnoreCase(fieldType)){
                            Boolean temp = Boolean.parseBoolean(value);
                            fieldSetMet.invoke(bean, temp);
                        }else if("BigDecimal".equalsIgnoreCase(fieldType)){
                            BigDecimal bigDecimal = new BigDecimal(value);
                            fieldSetMet.invoke(bean, bigDecimal);
                        }else if("TimeStamp".equalsIgnoreCase(fieldType)){
                            fieldSetMet.invoke(bean, Timestamp.valueOf(value));
                        }else{
                        }
                    }
                }catch(Exception e){
                    continue;
                }
            }
            return (T) bean;
        }

        /**
         * 获取属性名数组
         */
        public static String[] getFiledName(Object o){
            Field[] fields = o.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for(int i = 0; i < fields.length; i++){
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;
        }

        /**
         * 获取属性名,类型数组
         */
        public static Map <String, Object> getFiledNameType(Object o){
            Map <String, Object> map = new HashMap <String, Object>();
            Field[] fields = o.getClass().getDeclaredFields();
            for(int i = 0; i < fields.length; i++){
                map.put(fields[i].getName(), fields[i].getType());
            }
            return map;
        }

        public static String getFieldValueByFieldType(String fieldName, Object object){
            try{
                Field field = object.getClass().getField(fieldName);
                return field.getType().toString();
            }catch(Exception e){
                return "";
            }
        }


        /**
         * 返回参数放入request
         */
        public static void bean2Request(Object bean, HttpServletRequest req){
            // 基类
            Class <?> cls = bean.getClass();
            // version
            Class <?> super_cls = bean.getClass().getSuperclass();
            //
            // // 取出bean里的所有方法
            Method[] base_methods = cls.getDeclaredMethods();
            Method[] super_methods = super_cls.getDeclaredMethods();

            Field[] base_fields = cls.getDeclaredFields();
            Field[] super_fields = super_cls.getDeclaredFields();

            // 合并
            Method[] methods = concatAll(base_methods, super_methods);
            Field[] fields = concatAll(base_fields, super_fields);
            for(Field field : fields){
                try{
                    String fieldGetName = parMedName("get", field.getName());
                    if(!checkgetMet(methods, fieldGetName)){
                        continue;
                    }
                    Method fieldGetMet = cls.getMethod(fieldGetName);
                    String fieldKeyName = field.getName();
                    Object value = fieldGetMet.invoke(bean);
                    req.setAttribute(fieldKeyName, value);
                }catch(Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
        }


        /**
         * 字符串转实体类
         */
        public synchronized static <T> T toEntity(Object bean, String json){
            // JSON转换
            JSONObject jsonObj = JSONObject.parseObject(json);
            Object obj = JSONObject.toJavaObject(jsonObj, bean.getClass());
            return (T) obj;
        }


        private static boolean checkgetMet(Method[] methods, String fieldGetMet){
            for(Method met : methods){
                if(fieldGetMet.equals(met.getName())){
                    return true;
                }
            }
            return false;
        }

        /**
         * 判断是否存在某属性的 set方法
         *
         * @return boolean
         */
        public synchronized static boolean checkSetMet(Method[] methods, String fieldSetMet){
            for(Method met : methods){
                if(fieldSetMet.equals(met.getName())){
                    return true;
                }
            }
            return false;
        }

        /**
         * 格式化string为Date
         *
         * @return date
         */
        public synchronized static Date parseDate(String datestr){
            if(null == datestr || "".equals(datestr)){
                return null;
            }
            try{
                String fmtstr = null;
                if(datestr.indexOf(':') > 0){
                    fmtstr = "yyyy-MM-dd HH:mm:ss";
                }else{
                    fmtstr = "yyyy-MM-dd";
                }
                SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
                return sdf.parse(datestr);
            }catch(Exception e){
                return null;
            }
        }

        /**
         * 拼接在某属性的 set方法
         *
         * @return String
         */
        public synchronized static String parMedName(String par, String fieldName){
            if(null == fieldName || "".equals(fieldName)){
                return null;
            }
            int startIndex = 0;
            if(fieldName.charAt(0) == '_')
                startIndex = 1;
            return par + fieldName.substring(startIndex, startIndex + 1).toUpperCase() + fieldName.substring(startIndex + 1);
        }


        public static <T> T[] concatAll(T[] first, T[]... rest){
            int totalLength = first.length;
            for(T[] array : rest){
                totalLength += array.length;
            }
            T[] result = Arrays.copyOf(first, totalLength);
            int offset = first.length;
            for(T[] array : rest){
                System.arraycopy(array, 0, result, offset, array.length);
                offset += array.length;
            }
            return result;
        }

    }

    /**
     * 字符串是否为纯正整数
     * @param str  字符串
     * @return boolean true 是  false 不是
     */
    public static boolean isNumer(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str.replaceAll(" ","")).matches();
    }

    /**
     * 字符串转数组
     * @param ids  1,2
     * @return  [1,2]
     */
    public static Integer [] strTransformation(String ids){
        String [] id = ids.split(",");
        Integer [] newStr = new Integer[id.length];
        for (int i = 0; i < id.length; i++) {
            newStr[i] = Integer.valueOf(id[i]);
        }
        return newStr;
    }

    /**
     * 向目的URL发送post请求
     * @param url       目的url
     * @param params    发送的参数
     * @return  ResultVO
     */
    public static ResultVO sendPostRequest(String url ,MultiValueMap<String, String> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<ResultVO> response = client.exchange(url, method, requestEntity, ResultVO.class);
        return response.getBody();
    }
}






