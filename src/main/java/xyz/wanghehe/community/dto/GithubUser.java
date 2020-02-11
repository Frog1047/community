package xyz.wanghehe.community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Frog
 */
@Getter
@Setter
@ToString
public class GithubUser {
    private String id;
    private String login;
    private String bio;
    private String name;
    private String avatarUrl;

    public boolean isEmpty() {
        return id == null;
    }

    public String getNameWithoutNull() {
        return name == null ? login : name;
    }
}
