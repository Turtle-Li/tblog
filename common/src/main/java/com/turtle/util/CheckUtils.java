package com.turtle.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 * @author xzx19950624@qq.com
 * @date 2018年2月8日17:21:50
 */
public class CheckUtils {

    /**
     * 校验邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 校验手机号
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            String check = "\"^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\\\d{8})|(0\\\\d{2}-\\\\d{8})|(0\\\\d{3}-\\\\d{7})$\"";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @author lijiayu
     * @date 2020/1/20
     * @param password
     * @return boolean
     * @description 以字母开头，长度在6~18之间，只能包含字母、数字和下划线
     */
    public static boolean checkPassword(String password){
        boolean flag = false;
        try {
            String check = "^[a-zA-Z]\\w{5,17}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(password);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
