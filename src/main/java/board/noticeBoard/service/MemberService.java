package board.noticeBoard.service;

import board.noticeBoard.dto.member.*;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    public void signUp(MemberSignUpDto member);
    public MemberPhoneDupCheckDto memberPhoneDupCheck(String phone);
    public MemberIdDupCheckDto memberIdDupCheck(String id);
    public ResponseEntity<LoginResponseDto> login(MemberLoginDto member);
    public FindIdDto findId(String name, String phone);
}
