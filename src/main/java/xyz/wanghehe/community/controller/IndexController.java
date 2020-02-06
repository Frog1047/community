package xyz.wanghehe.community.controller;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.wanghehe.community.dto.QuestionDTO;
import xyz.wanghehe.community.dto.QuestionPageDTO;
import xyz.wanghehe.community.mapper.QuestionMapper;
import xyz.wanghehe.community.mapper.UserMapper;
import xyz.wanghehe.community.model.Question;
import xyz.wanghehe.community.model.User;
import xyz.wanghehe.community.service.QuestionService;
import xyz.wanghehe.community.utils.CookieUtils;

/**
 * @author Frog
 */
@Controller
public class IndexController {

    private final UserMapper userMapper;
    private QuestionService questionService;

    @Autowired
    public IndexController(UserMapper userMapper, QuestionService questionService) {
        this.userMapper = userMapper;
        this.questionService = questionService;
    }





    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") Integer limit) {
        //获取登录状态
        Cookie[] cookies = request.getCookies();
        Cookie token = CookieUtils.getCookie(cookies, "token");
        String sessionUserName = "user";
        if (token != null && (request.getSession().getAttribute(sessionUserName) == null)) {
            User user = userMapper.selectByToken(token.getValue());
            if (user != null) {
                request.getSession().setAttribute(sessionUserName, user);
            }
        }

        //返回问题列表
        QuestionPageDTO list = questionService.list(page, limit);
        model.addAttribute("page", list);

        return "index";
    }
}
