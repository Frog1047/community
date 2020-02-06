package xyz.wanghehe.community.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.wanghehe.community.mapper.QuestionMapper;
import xyz.wanghehe.community.model.Question;
import xyz.wanghehe.community.model.User;

/**
 * @author Frog
 */
@Controller
public class PublishController {

    QuestionMapper questionMapper;

    public PublishController(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublic(Question question, HttpServletRequest request, Model model) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        model.addAttribute("question", question);

        if (question.getTitle() == null || question.getTitle().isEmpty()) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if (question.getDescription() == null || question.getDescription().isEmpty()) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }

        if (question.getTag() == null || question.getTag().isEmpty()) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        question.setCreator(user.getId());

        long currentTimeMillis = System.currentTimeMillis();
        question.setGmtCreate(currentTimeMillis);
        question.setGmtModified(currentTimeMillis);

        questionMapper.insert(question);

        return "redirect:/";
    }

}
