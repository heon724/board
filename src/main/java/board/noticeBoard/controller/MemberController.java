package board.noticeBoard.controller;

import board.noticeBoard.dto.member.MemberDto;
import board.noticeBoard.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"회원 게시판"})
@RestController
@RequestMapping("/member")

public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "회원가입", response = MemberDto.class)
    @PostMapping(value = "/signUp")
    public ResponseEntity<Integer> memberIdDupCheck(@ApiParam(value = "회원가입정보", required = true) @RequestBody MemberDto member) {
        memberService.signUp(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
