package board.noticeBoard.customizedRepoImpl;

import board.noticeBoard.customizedRepo.CustomizedMemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static board.noticeBoard.entity.QMember.member;

public class CustomizedMemberRepositoryImpl implements CustomizedMemberRepository {
    final private JPAQueryFactory jpaQueryFactory;

    public CustomizedMemberRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public String findByMemberId(String id) {
        return  jpaQueryFactory.select(member.id)
                .from(member)
                .where(member.id.eq(id))
                .fetchFirst();
    }
}
