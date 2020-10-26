package com.lk.util;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.List;

/**
 * @Description IP相关工具类.
 * @Date 2020/7/8
 * @Author lk
 */
public final class IpUtil {
    /**
     * 验证是否是ip地址.
     * @param str 待验证字符串
     * @return boolean 验证结果
     **/
    public static boolean isIp(final String str) {
        if (Strings.isNullOrEmpty(str)) {
            return false;
        }

        if (!str.contains(".")) {
            return false;
        }

        // IP地址需满足123.123.123.123格式
        if (!str.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            return false;
        }

        final List<String> list = Splitter.on(".").splitToList(str);

        for (String string : list) {
            final int value = Integer.parseInt(string);
            if (value < 255 && value > 0) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * 获取IP地址.
     * @param request 请求对象
     * @return IP地址
     **/
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;

        try {
            ipAddress = request.getHeader("x-forwarded-for");

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy_Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL_Proxy_Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inetAddress = null;
                    try {
                        inetAddress = InetAddress.getLocalHost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ipAddress = inetAddress.getHostAddress();
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
}
