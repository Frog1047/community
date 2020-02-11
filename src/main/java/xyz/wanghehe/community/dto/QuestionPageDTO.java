package xyz.wanghehe.community.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.wanghehe.community.model.Question;

/**
 * @author Frog
 */
@Getter
@Setter
@ToString
public class QuestionPageDTO {

    List<QuestionDTO> questionDTOList;
    Integer totalPage;
    Integer currentPage;

    public QuestionPageDTO(List<QuestionDTO> questionDTOList, Integer currentPage, Integer limit, Integer totalCount) {
        this.questionDTOList = questionDTOList;
        this.totalPage = (int) Math.ceil(totalCount / (double) limit);
        this.currentPage = currentPage;
    }

}
