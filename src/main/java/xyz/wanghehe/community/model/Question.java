package xyz.wanghehe.community.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Frog
 */
@Setter
@Getter
@ToString
public class Question {

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
}
