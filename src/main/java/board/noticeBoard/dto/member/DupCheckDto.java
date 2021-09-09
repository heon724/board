package board.noticeBoard.dto.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor@AllArgsConstructor
@Builder
@ApiModel("아이디 중복확인")
public class DupCheckDto {
    @ApiModelProperty(value="중복체크", example="Y")
    private String dupYn;
}