package board.noticeBoard.customizedRepo;

import board.noticeBoard.dto.notice.NoticeUpdateDto;
import board.noticeBoard.entity.Notice;

import java.util.List;

public interface CustomizedNoticeRepository {
    List<Notice> findNoticeAll();
    List<Notice> noticeSearchTitle(String title);
    int updateNotice(NoticeUpdateDto noticeUpdateDto);
    int deleteNotice(int noticeSeq);
}
