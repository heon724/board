package board.noticeBoard.dto.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("게시글 수정")
public class NoticeUpdateDto {
    @ApiModelProperty(value = "게시글 순번", example = "1")
    int noticeSeq;

    @ApiModelProperty(value = "제목", example = "게시판 만들기")
    String title;

    @ApiModelProperty(value = "내용", example = "boot로 게시판 만들기")
    String content;
}