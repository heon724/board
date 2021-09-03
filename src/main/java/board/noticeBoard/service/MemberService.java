package board.noticeBoard.service;

import board.noticeBoard.dto.member.LoginResponseDto;
import board.noticeBoard.dto.member.MemberIdDupCheckDto;
import board.noticeBoard.dto.member.MemberLoginDto;
import board.noticeBoard.dto.member.MemberSignUpDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    public void signUp(MemberSignUpDto member);
    public MemberIdDupCheckDto memberIdDupCheck(String id);
    public ResponseEntity<LoginResponseDto> login(MemberLoginDto member);
}
