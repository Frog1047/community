package xyz.wanghehe.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.wanghehe.community.dto.AccessTokenDTO;
import xyz.wanghehe.community.dto.GithubUser;
import xyz.wanghehe.community.provider.GithubProvider;

/**
 * @author Frog
 */
@Controller
public class AuthorizeController {

    private GithubProvider githubProvider;
    private AccessTokenDTO accessTokenDTO;

    @Autowired
    public AuthorizeController(GithubProvider githubProvider, AccessTokenDTO accessTokenDTO) {
        this.githubProvider = githubProvider;
        this.accessTokenDTO = accessTokenDTO;
    }

    /**
     * github登录验证
     * @param code github用于获取访问单元（access token）的临时码
     * @param state 不可猜测的随机字符串。它用于防止跨站点请求伪造攻击。
     * @return access token的值，该值用于访问github获取用户信息
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
        @RequestParam(name = "state") String state) {

        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);

        System.out.println(accessTokenDTO);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);
        return "index";
    }
}
