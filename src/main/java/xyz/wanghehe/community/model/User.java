package xyz.wanghehe.community.model;

import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.wanghehe.community.dto.GithubUser;

/**
 * @author Frog
 */
@Getter
@Setter
@ToString
public class User {

    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private String avatarUrl;
    private  String bio;
    private Long gmtCreate;
    private Long gmtModified;

    public static User createUserByGithub(GithubUser githubUser) {
        User user = new User();
        user.accountId = String.valueOf(githubUser.getId());
        user.name = githubUser.getNameWithoutNull();
        user.token = UUID.randomUUID().toString();
        user.gmtCreate = System.currentTimeMillis();
        user.gmtModified = user.gmtCreate;
        user.avatarUrl = githubUser.getAvatarUrl();
        user.bio = githubUser.getBio();
        return user;
    }
}
