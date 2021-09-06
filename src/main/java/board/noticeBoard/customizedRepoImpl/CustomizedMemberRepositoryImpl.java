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
        return jpaQueryFactory.select(member.id)
                .from(member)
                .where(member.id.eq(id))
                .fetchFirst();
    }

    @Override
    public String findByMemberPhone(String phone) {
        return jpaQueryFactory.select(member.phone)
                .from(member)
                .where(member.phone.eq(phone))
                .fetchFirst();
    }

    @Override
    public String findByNameAndPhone(String name, String phone) {
        return jpaQueryFactory.select(member.id)
                .from(member)
                .where(member.name.eq(name)
                        .and(member.phone.eq(phone)))
                .fetchFirst();

    }
}
