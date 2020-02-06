package xyz.wanghehe.community.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.wanghehe.community.model.User;

/**
 * @author Frog
 */
@Getter
@Setter
@ToString
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;

}
