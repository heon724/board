package board.noticeBoard.controller;

import board.noticeBoard.dto.member.*;
import board.noticeBoard.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"회원 게시판"})
@RestController
@RequestMapping("/member")

public class MemberController {
    Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "사용자 아이디 중복확인", response = DupCheckDto.class)
    @GetMapping(value = "/dupcheck-id/{id}")
    public DupCheckDto memberIdDupCheck(@ApiParam(value = "사용자아이디", required = true) @PathVariable("id") String id) {
        return memberService.memberIdDupCheck(id);
    }

    @ApiOperation(value = "핸드폰번호 중복 확인", response = DupCheckDto.class)
    @GetMapping(value = "/dupcheck-phone/{phone}")
    public DupCheckDto memberPhoneDupCheck(@ApiParam(value = "핸드폰번호", required = true) @PathVariable("phone") String phone) {
        return memberService.memberPhoneDupCheck(phone);
    }

    @ApiOperation(value = "로그인", response = LoginResponseDto.class)
    @PostMapping(value = "/login")   // ResponseEntity: HTTP 요청/응답에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto member) {
        return memberService.login(member);
    }


    @ApiOperation(value = "회원가입", response = MemberDto.class)
    @PostMapping(value = "/signUp")
    public ResponseEntity<Integer> memberIdDupCheck(@ApiParam(value = "회원가입정보", required = true) @RequestBody MemberDto member) {
        memberService.signUp(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "아이디 찾기")
    @GetMapping("/findId/{name}/{phone}")
    public FindIdDto findIdDto(@ApiParam(value = "이름", required = true) @PathVariable("name") String name,
                               @ApiParam(value = "휴대폰번호", required = true) @PathVariable("phone") String phone) {
        return memberService.findId(name, phone);
    }

    @ApiOperation(value = "내정보 보기")
    @GetMapping("/findMember")
    public MemberDto findMember(Authentication authentication) {
        return memberService.findMember(authentication);
    }

    @ApiOperation(value = "비밀번호 초기화")
    @PutMapping("/findPw")
    public int findPw(Authentication authentication) {
        return memberService.resetPassword(authentication);
    }

    @ApiOperation(value = "내 정보 수정")
    @PutMapping("/updateMember")
    public int updateMember(@RequestBody UpdateMemberDto member, Authentication authentication) {
        return memberService.updateMember(member, authentication);
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/deleteMember")
    public int deleteMember(Authentication authentication) {
        return memberService.deleteMember(authentication);
    }
}