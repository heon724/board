package board.noticeBoard.customizedRepo;

import board.noticeBoard.dto.member.UpdateMemberDto;

public interface CustomizedMemberRepository {
    String findByMemberId(String id);
    String findByMemberPhone(String phone);
    String findByNameAndPhone(String name, String phone);
    int saveResetPw(String id, String pw);
    int saveMember(UpdateMemberDto updateMember, String id);
    int deleteMember(String id);

}