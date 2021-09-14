package board.noticeBoard.customizedRepoImpl;

import board.noticeBoard.customizedRepo.CustomizedNoticeRepository;
import board.noticeBoard.dto.notice.NoticeUpdateDto;
import board.noticeBoard.entity.Notice;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static board.noticeBoard.entity.QNotice.notice;


public class CustomizedNoticeRepositoryImpl implements CustomizedNoticeRepository {
    final private JPAQueryFactory jpaQueryFactory;

    public CustomizedNoticeRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public List<Notice> findNoticeAll() {
        return jpaQueryFactory.selectFrom(notice)
                .orderBy(notice.noticeSeq.desc())
                .fetch();
    }

    @Override
    public List<Notice> noticeSearchTitle(String title) {
        return jpaQueryFactory.selectFrom(notice)
                .where(notice.title.contains(title))
                .orderBy(notice.noticeSeq.desc())
                .fetch();
    }

    @Override
    public int updateNotice(NoticeUpdateDto noticeUpdateDto) {
        return (int) jpaQueryFactory.update(notice)
                .set(notice.content, noticeUpdateDto.getContent())
                .set(notice.title, noticeUpdateDto.getTitle())
                .where(notice.noticeSeq.eq(noticeUpdateDto.getNoticeSeq()))
                .execute();
    }

    @Override
    public int deleteNotice(int noticeSeq) {
        return (int) jpaQueryFactory.delete(notice)
                .where(notice.noticeSeq.eq(noticeSeq))
                .execute();
    }
}
