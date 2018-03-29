package com.zjj.baselibrary.util;

/**
 * Created by zhijinjin (951507056@qq.com)
 * on 2018/3/29.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public  static boolean isEmpty(String str){
        if(null == str || "".equals(str)){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 判断string是否为int值并且返回结果
     */
    public static int stringToInt(String str){
        if(!isEmpty(str)){
            Pattern pattern = Pattern.compile("^\\+{0,1}[1-9]\\d*");
            Matcher isNum = pattern.matcher(str.trim());
            if (isNum.matches()) {
                return Integer.parseInt(str.trim());
            }
        }
        return 0;
    }

    /**
     * 判断string是否为double值并且返回结果
     */
    public static double stringToDouble(String str){
        double ret = 0;
        if(!isEmpty(str)){
            try{
                ret = Double.parseDouble(str.trim());
            }catch(NumberFormatException ex){
                ret = 0;
            }
        }
        return ret;
    }

    /**
     * 验证是否是手机号码
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    // 替换、过滤特殊字符
    public static String stringFilter(String str){
        char[] c = str.toCharArray();
        for (int i = 0; i< c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }if (c[i]> 65280&& c[i]< 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static boolean isPicture(String key, String value){
        if(isEmpty(key) || isEmpty(value)){
            return false;
        }
        if("图片".equals(key)){
            return true;
        }
        int index = value.lastIndexOf(".");
        String end = "";
        if(index > 0){
            end = value.substring(index+1);
        }
        if("jpg".equals(end) || "png".equals(end) || "jpeg".equals(end)){
            return true;
        }
        return false;
    }
}

