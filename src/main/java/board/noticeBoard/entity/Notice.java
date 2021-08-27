package board.noticeBoard.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "notice")
@Builder
@ApiModel("챌린저스 모집 게시글")
public class Notice {

    @Id
    @Column(name = "notice_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value="게시글 순번", example="1")
    private int noticeSeq;

    @Column(nullable = false)
    @ApiModelProperty(value="제목", example="코딩하실분")
    private String title;

    @Column(nullable = false)
    @ApiModelProperty(value="아이디", example="user001")
    private String id;

    @Column(nullable = false)
    @ApiModelProperty(value="내용", example="코딩 스터디 할 사람 신청해주세요")
    private String content;





}
