package com.ljn.demo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class CookieUtil {

    /**
     * 得到Cookie的值, 不解码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值，可设置是否解码，一般有中文才解码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        if (request == null || cookieName == null) {
            throw new IllegalArgumentException("request和cookieName均不可为空");
        }
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 得到Cookie的值，使用指定的编码方式解码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        if (request == null || cookieName == null) {
            throw new IllegalArgumentException("request和cookieName均不可为空");
        }
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间，默认浏览器关闭即失效，不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1, "/");
    }

    /**
     * 设置Cookie的值 在指定时间内生效，不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, String path) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, path,false);
    }

    /**
     * 设置Cookie的值 不设置生效时间，可设置是否编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, "/", isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效，可设置是否编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, String path, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, path, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 指定编码类型
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, String path, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, path, encodeString);
    }

    /**
     * 删除Cookie(将value置空)带cookie域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, "/", false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                          String cookieName, String cookieValue, int cookieMaxage, String path, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                if (isEncode)
                    cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            // 1.设置cookie有效时间(秒)
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            // 2.设置cookie的域名
            if (null != request) {
                String domainName = getDomainName(request);
//                System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            // 3.设置cookie的路径
            cookie.setPath(path);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                          String cookieName, String cookieValue, int cookieMaxage, String path, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            // 1.设置cookie有效时间(秒)
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            // 2.设置cookie的域名
            if (null != request) {
                String domainName = getDomainName(request);
//                System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            // 3.设置cookie的路径
            cookie.setPath(path);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到cookie的域名
     */
    private static String getDomainName(HttpServletRequest request) {
        String domainName = null;
        // 通过request对象获取访问的url地址
        String serverName = request.getRequestURL().toString();
        if (serverName.equals("")) {
            domainName = "";
        } else {
            // 将url地下转换为小写
            serverName = serverName.toLowerCase();
            // 如果url地址是以http://开头  将http://截取
            if (serverName.startsWith("http://")) {
                serverName = serverName.substring(7);
            }
            int end = serverName.length();
            // 判断url地址是否包含"/"
            if (serverName.contains("/")) {
                //得到第一个"/"出现的位置
                end = serverName.indexOf("/");
            }

            // 截取
//            serverName = serverName.substring(0, end);
            domainName = serverName.substring(0, end);
            // 根据"."进行分割
//            final String[] domains = serverName.split("\\.");
//            int len = domains.length;
//            if (len > 3) {
//                // www.xxx.com.cn
//                domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
//            } else if (len <= 3 && len > 1) {
//                // xxx.com or xxx.cn
//                domainName = domains[len - 2] + "." + domains[len - 1];
//            } else {
//                domainName = serverName;
//            }
        }

        if (domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}
