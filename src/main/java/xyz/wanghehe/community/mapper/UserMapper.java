package xyz.wanghehe.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import xyz.wanghehe.community.model.User;

/**
 * @author Frog
 */
public interface UserMapper {


    /**
     * 将用户数据插入数据库
     *
     * @param user user
     */
    @SelectKey(before = false, resultType = Integer.class, keyColumn = "id",
        keyProperty = "id", statement = "SELECT IDENTITY();")
    @Insert("INSERT INTO user(NAME, GMT_CREATE, GMT_MODIFIED, avatar_url, bio) "
        + "VALUES(#{name}, #{gmtCreate}, #{gmtModified}, #{avatarUrl}, #{bio})")
    void insert(User user);

    /**
     * 根据用户的id属性查询用户
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(Integer id);

}
