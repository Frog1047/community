package xyz.wanghehe.community.mapper;

import org.apache.ibatis.annotations.Insert;
import xyz.wanghehe.community.model.Question;

/**
 * @author Frog
 */
public interface QuestionMapper {

    /**
     * 插入问题数据
     * @param question
     */
    @Insert("INSERT INTO question(title, description, gmt_create, gmt_modified, creator, tag)"
        + "VALUES(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator.id}, #{tag})")
    void insert(Question question);

}
