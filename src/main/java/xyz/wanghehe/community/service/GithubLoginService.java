package xyz.wanghehe.community.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.wanghehe.community.dto.GithubUser;
import xyz.wanghehe.community.mapper.LoginTokenMapper;
import xyz.wanghehe.community.mapper.UserAuthsMapper;
import xyz.wanghehe.community.mapper.UserMapper;
import xyz.wanghehe.community.model.AuthType;
import xyz.wanghehe.community.model.LoginToken;
import xyz.wanghehe.community.model.User;
import xyz.wanghehe.community.model.UserAuths;
import xyz.wanghehe.community.provider.GithubProvider;

/**
 * @author Frog
 */
@Service
public class GithubLoginService {

    private final LoginTokenMapper loginTokenMapper;
    private final UserMapper userMapper;
    private final UserAuthsMapper userAuthsMapper;

    @Autowired
    public GithubLoginService(LoginTokenMapper loginTokenMapper, UserMapper userMapper,
        UserAuthsMapper userAuthsMapper) {
        this.loginTokenMapper = loginTokenMapper;
        this.userMapper = userMapper;
        this.userAuthsMapper = userAuthsMapper;
    }


    public Map<String, String> login(GithubUser githubUser, String accessToken) {
        //从数据库中尝试获取userAuths
        UserAuths userAuths = userAuthsMapper.select(AuthType.GITHUB.getValue(), githubUser.getId());
        User user = null;
        String token = null;
        if (userAuths == null) {
            //新用户存入数据库
            user = User.createUserByGithub(githubUser);
            userMapper.insert(user);
            addUserAuths(githubUser, user.getId(), accessToken);
        } else {
            //老用户查出来
            user = userMapper.selectById(userAuths.getUserId());
        }
        token = addLoginToken(user.getId());
        Map<String, String> map = new HashMap<>(1);
        map.put("token", token);
        return map;
    }

    private String addLoginToken(int userId) {
        LoginToken loginToken = new LoginToken();
        loginToken.setUserId(userId);

        Date now = new Date();
        //设置10天
        now.setTime(now.getTime() + 1000L * 60L * 60L * 24L * 10L);
        loginToken.setExpired(now);

        //0表示无效，1表示有效
        loginToken.setStatus(1);

        String token = UUID.randomUUID().toString();
        loginToken.setToken(token);

        long millis = System.currentTimeMillis();
        loginToken.setGmtCreate(millis);
        loginToken.setGmtModified(millis);
        loginTokenMapper.insert(loginToken);
        return token;
    }

    private void addUserAuths(GithubUser githubUser, int userId, String accessToken) {
        UserAuths userAuths = new UserAuths();
        userAuths.setUserId(userId);
        userAuths.setIdentityType(AuthType.GITHUB.getValue());
        userAuths.setIdentifier(githubUser.getId());
        userAuths.setCredential(accessToken);
        long millis = System.currentTimeMillis();
        userAuths.setGmtCreate(millis);
        userAuths.setGmtModified(millis);
        userAuthsMapper.insert(userAuths);
    }


}
