package board.noticeBoard.service;

import board.noticeBoard.dto.notice.NoticeCreateDto;
import board.noticeBoard.dto.notice.NoticeListDto;
import board.noticeBoard.dto.notice.NoticeUpdateDto;
import org.springframework.security.core.Authentication;

public interface NoticeService {
    NoticeListDto noticeList();
    int createNotice(NoticeCreateDto notice, Authentication authentication);
    NoticeListDto noticeSearchTitle(String title);
    int deleteNotice(int noticeSeq, Authentication authentication);
    int updateNotice(NoticeUpdateDto notice, Authentication authentication);
}
