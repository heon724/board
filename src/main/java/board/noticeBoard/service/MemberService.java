package board.noticeBoard.service;

import board.noticeBoard.dto.member.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface MemberService {

    DupCheckDto memberIdDupCheck(String id);
    DupCheckDto memberPhoneDupCheck(String phone);
    void signUp(MemberDto member);
    ResponseEntity<LoginResponseDto> login(LoginDto member);
    FindIdDto findId(String name, String phone);
    MemberDto findMember(Authentication authentication);
    int resetPassword(Authentication authentication);
    int updateMember(UpdateMemberDto member, Authentication authentication);
    int deleteMember(Authentication authentication);
}

