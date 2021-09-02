package board.noticeBoard.repository;

import board.noticeBoard.customizedRepo.CustomizedMemberRepository;
import board.noticeBoard.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>, CustomizedMemberRepository {
}
