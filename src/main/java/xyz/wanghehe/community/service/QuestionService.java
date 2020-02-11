package xyz.wanghehe.community.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.wanghehe.community.dto.QuestionDTO;
import xyz.wanghehe.community.dto.QuestionPageDTO;
import xyz.wanghehe.community.mapper.QuestionMapper;
import xyz.wanghehe.community.mapper.UserMapper;
import xyz.wanghehe.community.model.Question;
import xyz.wanghehe.community.model.User;

/**
 * @author Frog
 */
@Service
public class QuestionService {

    private final UserMapper userMapper;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(UserMapper userMapper, QuestionMapper questionMapper) {
        this.userMapper = userMapper;
        this.questionMapper = questionMapper;
    }

    public QuestionPageDTO list(Integer page, Integer limit) {
        List<Question> questions = questionMapper.list(new RowBounds(limit * (page - 1), limit));

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        return new QuestionPageDTO(questionDTOList, page, limit, questionMapper.getCount());
    }

    public void listByCreator(User user) {
    }
}
