package com.zhzh.util.otherUtil;

import com.zhzh.constants.Constants;
import com.zhzh.util.exception.GlobalException;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author : zhang sq
 * @date : 2020/8/21 11:52
 **/
public class PinYinUtil {

    /**
     * 根据汉字获取拼音 小写
     * @param name 汉字
     * @return 拼音 小写
     */
    public static String getPinyinLower(String name) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE );
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        return pinyin(name,defaultFormat);
    }

    /**
     * 根据汉字获取拼音 大写
     * @param name 汉字
     * @return 拼音 大写
     */
    public  static String getPinyinUpper(String name) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE );
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        return pinyin(name,defaultFormat);
    }

    /**
     * 获取汉字首字母(大写)
     */
    public static String initialPinyin(String name) {
        char[] charArray = name.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE );
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = new String[0];
                try {
                    hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                    throw new GlobalException(Constants.PARAM_ERROR,"根据汉字返回拼音报错");
                }
                if (hanyuPinyinStringArray != null) {
                    pinyin.append(hanyuPinyinStringArray[0].charAt(0));
                }
            }
        }
       return pinyin.toString();
    }

    //根据汉字返回拼音
    public static String pinyin(String name ,HanyuPinyinOutputFormat defaultFormat){
        char[] charArray = name.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = new String[0];
                try {
                    hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i],defaultFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                    throw new GlobalException(Constants.PARAM_ERROR,"根据汉字返回拼音报错");
                }
                String string =hanyuPinyinStringArray[0];
                pinyin.append(string);
            } else {
                pinyin.append(charArray[i]);
            }
        }
        return pinyin.toString();
    }
}
