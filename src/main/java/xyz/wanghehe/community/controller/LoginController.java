package xyz.wanghehe.community.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.wanghehe.community.provider.GithubProvider;

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
    @Value("${github.scope}")
    private String scope;

    @GetMapping("/login")
    public String login(HttpServletResponse response, Model model) {

        String url = authorizeApi + "?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&scope=" + scope
            + "&state=1";
        return "redirect:"+url;
    }

}
