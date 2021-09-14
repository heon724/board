package board.noticeBoard.dto.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("게시글 등록")
public class NoticeCreateDto {
    @ApiModelProperty(value="제목", example="게시판 만들기")
    String title;

    @ApiModelProperty(value="이름", example="user001")
    String id;

    @ApiModelProperty(value="내용", example="boot로 게시판 만들기")
    String content;

    @ApiModelProperty(value="작성일", example="")
    LocalDateTime createDate;


}