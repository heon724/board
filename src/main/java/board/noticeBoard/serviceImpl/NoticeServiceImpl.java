package board.noticeBoard.serviceImpl;

import board.noticeBoard.dto.notice.NoticeCreateDto;
import board.noticeBoard.dto.notice.NoticeDto;
import board.noticeBoard.dto.notice.NoticeListDto;
import board.noticeBoard.dto.notice.NoticeUpdateDto;
import board.noticeBoard.entity.Notice;
import board.noticeBoard.repository.NoticeRepository;
import board.noticeBoard.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    NoticeRepository noticeRepository;



    /**
     * 게시글 전체 목록 조회
     *
     * @return
     */
    @Override
    public NoticeListDto noticeList() {
        List<Notice> noticeList = noticeRepository.findNoticeAll();
        List<NoticeDto> noticeDto = new ArrayList<>();

        for (Notice notice : noticeList) {
            NoticeDto noticeDTO = new NoticeDto();
            BeanUtils.copyProperties(notice, noticeDTO);
            noticeDto.add(noticeDTO);
        }

        return NoticeListDto.builder()
                .noticeList(noticeDto)
                .build();
    }


    /**
     * 게시글 등록
     *
     * @param notice
     * @param authentication
     * @return
     */
    @Transactional
    @Override
    public int createNotice(NoticeCreateDto notice, Authentication authentication) {
        Notice result = noticeRepository.save(Notice.builder()
                .title(notice.getTitle())
                .id(notice.getId())
                .content(notice.getContent())
                .createDate(notice.getCreateDate())
                .build());
        return 0;
    }

    @Override
    public NoticeListDto noticeSearchTitle(String title) {

        List<Notice> noticeList = noticeRepository.noticeSearchTitle(title);
        List<NoticeDto> noticeDto = new ArrayList<>();

        noticeList.forEach(notice -> {
            noticeDto.add(NoticeDto.builder()
                    .noticeSeq(notice.getNoticeSeq())
                    .title(notice.getTitle())
                    .id(notice.getId())
                    .content(notice.getContent())
                    .createDate(notice.getCreateDate())
                    .build());
        });

        return NoticeListDto.builder()
                .noticeList(noticeDto)
                .build();
    }

    /**
     * 게시글 삭제
     *
     * @param noticeSeq
     * @return
     */
    @Transactional
    @Override
    public int deleteNotice(int noticeSeq, Authentication authentication) {
        return noticeRepository.deleteNotice(noticeSeq);
    }


    /**
     * 게시글 수정
     *
     * @param notice
     * @return
     */
    @Transactional
    @Override
    public int updateNotice(NoticeUpdateDto notice, Authentication authentication) {
        return noticeRepository.updateNotice(notice);
    }
}