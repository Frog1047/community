package xyz.wanghehe.community.utils;

import javax.servlet.http.Cookie;

/**
 * @author Frog
 */
public class CookieUtils {

    public static Cookie getCookie(Cookie[] cookies, String name) {
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

}
