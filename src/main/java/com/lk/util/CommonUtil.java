package com.lk.util;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 通用工具类.
 * @Date 2020/7/7
 * @Author lk
 */
public final class CommonUtil {

    /**
     * 验证是否是手机号或座机号.
     * @param str 待验证字符串
     * @return boolean 验证结果
     **/
    public static boolean isMobileOrPhone(final String str) {
        if (isMobile(str)) {
            return true;
        }
        return isPhone(str);
    }

    /**
     * 验证是否手机号码.
     * @param mobile 手机号
     * @return boolean 验证结果
     **/
    public static boolean isMobile(final String mobile) {
        // 匹配规则
        final Pattern pattern = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
        final Matcher matcher = pattern.matcher(mobile);

        return matcher.matches();
    }

    /**
     * 验证是否座机电话号码.
     * @param phone 电话号码
     * @return boolean 验证结果
     **/
    public static boolean isPhone(final String phone) {
        // 匹配规则
        // 带区号
        final Pattern pattern = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
        // 不带区号
        final Pattern pattern2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");

        Matcher matcher = null;
        boolean result = false;

        if (phone.length() > 9) {
            matcher = pattern.matcher(phone);
            result = matcher.matches();
        } else {
            matcher = pattern2.matcher(phone);
            result = matcher.matches();
        }
        return result;
    }
}
