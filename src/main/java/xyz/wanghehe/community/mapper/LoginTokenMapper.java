package xyz.wanghehe.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import xyz.wanghehe.community.model.LoginToken;

public interface LoginTokenMapper {


    @Insert("insert into login_token(user_id, token, expired, status, gmt_create, gmt_modified)"
        + "values(#{userId}, #{token}, #{expired}, #{status}, #{gmtCreate}, #{gmtModified})")
    void insert(LoginToken loginToken);

    @Select("select * from login_token where token = #{token}")
    LoginToken selectByToken(String token);
}
