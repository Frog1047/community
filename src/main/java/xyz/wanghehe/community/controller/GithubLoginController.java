package xyz.wanghehe.community.controller;

import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.wanghehe.community.dto.AccessTokenDTO;
import xyz.wanghehe.community.dto.GithubUser;
import xyz.wanghehe.community.provider.GithubProvider;
import xyz.wanghehe.community.service.GithubLoginService;

/**
 * @author Frog
 */
@Controller
public class GithubLoginController {

    @Value("${github.api.authorize_api}")
    private String authorizeApi;
    @Value("${github.access-token-param.client_id}")
    private String clientId;
    @Value("${github.access-token-param.redirect_uri}")
    private String redirectUrl;
    @Value("${github.api.scope}")
    private String scope;

    private final GithubProvider githubProvider;
    private AccessTokenDTO accessTokenDTO;
    private final GithubLoginService githubLoginService;

    @Autowired
    public GithubLoginController(GithubProvider githubProvider, AccessTokenDTO accessTokenDTO,
        GithubLoginService githubLoginService) {
        this.githubProvider = githubProvider;
        this.accessTokenDTO = accessTokenDTO;
        this.githubLoginService = githubLoginService;
    }

    @GetMapping("/githubAuth")
    public String githubAuth() {

        String url = authorizeApi + "?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&scope=" + scope
            + "&state=1";
        return "redirect:" + url;
    }

    @GetMapping("/callback")
    public String callback(
        @RequestParam(name = "code") String code,
        @RequestParam(name = "state") String state,
        HttpServletResponse response,
        Model model) {

        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);

        //登录
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null && !githubUser.isEmpty()) {
            Map<String, String> map = githubLoginService.login(githubUser, accessToken);

            Cookie cookie = new Cookie("token", map.get("token"));
            cookie.setMaxAge(60 * 60 * 24 * 10);
            response.addCookie(cookie);
        } else {
            model.addAttribute("errMsg", "登录失败");
        }

        return "redirect:/";
    }
}
