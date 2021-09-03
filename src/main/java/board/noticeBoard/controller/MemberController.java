package board.noticeBoard.controller;

import board.noticeBoard.dto.member.LoginResponseDto;
import board.noticeBoard.dto.member.MemberIdDupCheckDto;
import board.noticeBoard.dto.member.MemberLoginDto;
import board.noticeBoard.dto.member.MemberSignUpDto;
import board.noticeBoard.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"회원 게시판"})
@RestController
@RequestMapping("/member")

public class MemberController {
    Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "사용자아이디 중복확인", response = MemberIdDupCheckDto.class)
    @GetMapping(value = "/dupcheck-id/{id}")
    public MemberIdDupCheckDto memberIdDupCheck(@ApiParam(value = "사용자아이디", required = true) @PathVariable("id") String id) {
        return memberService.memberIdDupCheck(id);
    }

    @PostMapping(value = "/login")   // ResponseEntity: HTTP 요청/응답에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스
    public ResponseEntity<LoginResponseDto> login(@RequestBody MemberLoginDto member) {
        return memberService.login(member);
    }


    @ApiOperation(value = "회원가입", response = MemberSignUpDto.class)
    @PostMapping(value = "/signUp")
    public ResponseEntity<Integer> memberIdDupCheck(@ApiParam(value = "회원가입정보", required = true) @RequestBody MemberSignUpDto member) {
        memberService.signUp(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
