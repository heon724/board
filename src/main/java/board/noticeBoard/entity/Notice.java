package board.noticeBoard.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "notice")
@Builder
@ApiModel("게시글")
public class Notice {

    @Id
    @Column(name = "notice_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "게시글 순번", example = "1")
    private int noticeSeq;

    @Column(nullable = false)
    @ApiModelProperty(value = "제목", example = "게시판 만들기")
    private String title;

    @Column(nullable = false)
    @ApiModelProperty(value = "id", example = "user001")
    private String id;

    @Column(nullable = false)
    @ApiModelProperty(value = "내용", example = "boot로 게시판 만들기")
    private String content;

    @CreatedDate
    @Column(nullable = false)
    @ApiModelProperty(value="작성일", example="")
    private LocalDateTime createDate;


}
