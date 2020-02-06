package xyz.wanghehe.community.service;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import xyz.wanghehe.community.dto.QuestionDTO;
import xyz.wanghehe.community.dto.QuestionPageDTO;
import xyz.wanghehe.community.model.Question;

/**
 * @author Frog
 */
@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Test
    public void testList() {
        QuestionPageDTO list = questionService.list(1, 10);
        Assertions.assertEquals(3, list.getTotalPage());
        Assertions.assertEquals(1, list.getCurrentPage());
        list.getQuestionDTOList().forEach(System.out::println);
    }

}
