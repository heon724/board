package board.noticeBoard.customizedRepo;

public interface CustomizedMemberRepository {
    String findByMemberId(String id);
}