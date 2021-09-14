package board.noticeBoard.dto.notice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("게시글 정보")
public class NoticeDto {
    @ApiModelProperty(value = "게시글 순번", example = "1")
    int noticeSeq;

    @ApiModelProperty(value="제목", example="게시판 만들기")
    String title;

    @ApiModelProperty(value="아이디", example="user001")
    String id;

    @ApiModelProperty(value="내용", example="boot로 게시판 만들기")
    String content;

    @ApiModelProperty(value="작성일", example="")
    LocalDateTime createDate;


}