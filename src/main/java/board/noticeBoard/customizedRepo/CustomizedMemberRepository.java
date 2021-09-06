package board.noticeBoard.customizedRepo;

public interface CustomizedMemberRepository {
    String findByMemberId(String id);
    String findByMemberPhone(String phone);
    String findByNameAndPhone(String name, String phone);
}