package xyz.wanghehe.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.wanghehe.community.utils.CookieUtils;
import xyz.wanghehe.community.utils.RandomStringUtils;

/**
 * @author Frog
 */
@Controller
public class LoginController {



    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.getCookie(cookies, "token");
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        request.getSession().removeAttribute("user");
        return "redirect:/";
    };

}
