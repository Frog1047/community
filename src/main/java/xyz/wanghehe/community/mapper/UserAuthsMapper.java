package xyz.wanghehe.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.wanghehe.community.model.UserAuths;

/**
 * @author Frog
 */
public interface UserAuthsMapper {



    @Insert("insert into user_auths(user_id, identity_type, identifier, credential, gmt_create, gmt_modified)"
        + "values(#{userId}, #{identityType}, #{identifier}, #{credential}, #{gmtCreate}, #{gmtModified})")
    void insert(UserAuths userAuths);

    @Select("select * from user_auths where identity_type = #{identityType} and identifier = #{identifier}")
    UserAuths select(@Param("identityType") String identityType, @Param("identifier") String identifier);

}
