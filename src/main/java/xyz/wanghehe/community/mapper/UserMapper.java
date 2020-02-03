package xyz.wanghehe.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.wanghehe.community.model.User;

/**
 * @author Frog
 */
@Mapper
public interface UserMapper {


    /**
     * 将用户数据插入数据库
     * @param user
     */
    @Insert("INSERT INTO user(ACCOUNT_ID, NAME, TOKEN, GMT_CREATE, GMT_MODIFIED) "
        + "VALUES(#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);


    /**
     * 根据用户的account_id属性查询用户
     * @param accountId
     * @return
     */
    @Select("SELECT * FROM user WHERE account_id = #{accountId}")
    User selectByAccountId(String accountId);

    /**
     * 根据用户的token属性查询用户
     * @param token
     * @return
     */
    @Select("SELECT * FROM user WHERE token = #{token}")
    User selectByToken(String token);

}
