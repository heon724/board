package board.noticeBoard.service;

import board.noticeBoard.dto.member.MemberIdDupCheckDto;
import board.noticeBoard.dto.member.MemberSignUpDto;

public interface MemberService {

    public void signUp(MemberSignUpDto member);
    public MemberIdDupCheckDto memberIdDupCheck(String id);
}
