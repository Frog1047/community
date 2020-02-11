package xyz.wanghehe.community.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.wanghehe.community.mapper.UserMapper;
import xyz.wanghehe.community.model.User;
import xyz.wanghehe.community.service.QuestionService;

/**
 * @author Frog
 */
@Controller
public class ProfileController {

    private QuestionService questionService;

    @Autowired
    public ProfileController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/profile")
    public String myQuestion(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        questionService.listByCreator(user);

        return "profile";
    }

}
