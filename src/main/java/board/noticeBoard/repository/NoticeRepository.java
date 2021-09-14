package board.noticeBoard.repository;

import board.noticeBoard.customizedRepo.CustomizedNoticeRepository;
import board.noticeBoard.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, CustomizedNoticeRepository {
}
