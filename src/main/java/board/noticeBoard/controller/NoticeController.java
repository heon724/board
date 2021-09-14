package board.noticeBoard.controller;

import board.noticeBoard.dto.notice.NoticeCreateDto;
import board.noticeBoard.dto.notice.NoticeListDto;
import board.noticeBoard.dto.notice.NoticeUpdateDto;
import board.noticeBoard.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"게시판"})
@RestController
@RequestMapping("/notice")
public class NoticeController {
    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "게시글 전체 목록 조회", response = NoticeListDto.class)
    @GetMapping(value = "/list")
    public NoticeListDto noticeList() {
        return noticeService.noticeList();
    }

    @ApiOperation(value = "게시글 등록")
    @PostMapping(value = "/create")
    public int createNotice(@ApiParam("게시글 등록") @RequestBody NoticeCreateDto notice, Authentication authentication) {
        return noticeService.createNotice(notice,authentication);
    }


    @ApiOperation(value = "게시글 제목 검색")
    @GetMapping(value = "/searchTitle/{title}")
    public NoticeListDto noticeSearchTitle(@ApiParam(value = "게시글 제목") @PathVariable(value = "title") String title ){
        return noticeService.noticeSearchTitle(title);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping(value = "/delete/{noticeSeq}")
    public int deleteNotice(@ApiParam(value = "게시글 번호") @PathVariable(value = "noticeSeq") int noticeSeq, Authentication authentication) {
        return noticeService.deleteNotice(noticeSeq, authentication);
    }

    @ApiOperation(value = "게시글 수정")
    @PutMapping(value = "/update")
    public int updateNotice(@ApiParam(value = "게시글 번호") @RequestBody NoticeUpdateDto notice, Authentication authentication) {
        return noticeService.updateNotice(notice, authentication);
    }


}