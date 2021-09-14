package board.noticeBoard.dto.notice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("게시글 목록")
public class NoticeListDto {

    @ApiModelProperty(value = "게시글 목록", example = "")
    List<NoticeDto> noticeList;
}