package xyz.wanghehe.community.model;

import lombok.Data;

/**
 * @author Frog
 */
@Data
public class UserAuths {
    private Integer id;
    private Integer userId;
    private String identityType;
    private String identifier;
    private String credential;
    private Long gmtCreate;
    private Long gmtModified;
}
