package xyz.wanghehe.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.wanghehe.community.utils.CookieUtils;

/**
 * @author Frog
 */
@Controller
public class LoginController {

    @Value("${github.api.authorize_api}")
    private String authorizeApi;
    @Value("${github.access-token-param.client_id}")
    private String clientId;
    @Value("${github.access-token-param.redirect_uri}")
    private String redirectUrl;
    @Value("${github.api.scope}")
    private String scope;

    @GetMapping("/login")
    public String login(HttpServletResponse response, Model model) {

        String url = authorizeApi + "?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&scope=" + scope
            + "&state=1";
        return "redirect:"+url;
    }

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
