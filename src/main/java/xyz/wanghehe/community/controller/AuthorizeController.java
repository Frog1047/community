package xyz.wanghehe.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.wanghehe.community.dto.AccessTokenDTO;
import xyz.wanghehe.community.dto.GithubUser;
import xyz.wanghehe.community.mapper.UserMapper;
import xyz.wanghehe.community.model.User;
import xyz.wanghehe.community.provider.GithubProvider;

/**
 * @author Frog
 */
@Controller
public class AuthorizeController {

    private final GithubProvider githubProvider;
    private final UserMapper userMapper;
    private AccessTokenDTO accessTokenDTO;

    @Autowired
    public AuthorizeController(GithubProvider githubProvider, AccessTokenDTO accessTokenDTO,
        UserMapper userMapper) {
        this.githubProvider = githubProvider;
        this.accessTokenDTO = accessTokenDTO;
        this.userMapper = userMapper;
    }

    /**
     * github登录验证
     *
     * @param code github用于获取访问单元（access token）的临时码
     * @param state 不可猜测的随机字符串。它用于防止跨站点请求伪造攻击。
     * @return access token的值，该值用于访问github获取用户信息
     */
    @GetMapping("/callback")
    public String callback(
        @RequestParam(name = "code") String code,
        @RequestParam(name = "state") String state,
        HttpServletRequest request,
        HttpServletResponse response,
        Model model) {

        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);

        //登录
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);


        if (githubUser != null && !githubUser.isEmpty()) {
            //从数据库中尝试获取user
            User user = userMapper.selectByAccountId(String.valueOf(githubUser.getId()));
            if (user == null) {
                //新用户存入数据库
                user = User.createUserByGithub(githubUser);
                userMapper.insert(user);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.addCookie(new Cookie("token", user.getToken()));
        } else {
            model.addAttribute("errMsg", "登录失败");
        }

        return "redirect:/";
    }

}
