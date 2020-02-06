package xyz.wanghehe.community.mapper;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wanghehe.community.model.Question;

/**
 * @author Frog
 */
@SpringBootTest
public class QuestionMapperTest {


    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void testList() {
        List<Question> list = questionMapper.list(new RowBounds(0, 10));
        System.out.println(list.toString());
    }

    @Test
    public void testGetCount() {
        System.out.println(questionMapper.getCount());
    }

}
