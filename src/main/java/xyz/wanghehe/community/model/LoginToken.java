package xyz.wanghehe.community.model;

import java.util.Date;
import lombok.Data;

/**
 * @author Frog
 */
@Data
public class LoginToken {
    private Integer id;
    private Integer userId;
    private String token;
    private Date expired;
    private Integer status;
    private Long gmtCreate;
    private Long gmtModified;
}
