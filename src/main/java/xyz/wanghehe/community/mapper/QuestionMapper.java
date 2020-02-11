package xyz.wanghehe.community.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import xyz.wanghehe.community.model.Question;

/**
 * @author Frog
 */
public interface QuestionMapper {

    /**
     * 插入问题数据
     * @param question question
     */
    @Insert("INSERT INTO question(title, description, gmt_create, gmt_modified, creator, tag)"
        + "VALUES(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void insert(Question question);


    /**
     * 返回所有数据
     * @param rowBounds 分页对象
     * @return return
     */
    @Select("SELECT * FROM question ORDER BY gmt_modified desc")
    List<Question> list(RowBounds rowBounds);


    @Select("SELECT count(id) FROM question")
    Integer getCount();
}
