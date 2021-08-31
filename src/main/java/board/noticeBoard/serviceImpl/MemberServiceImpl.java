package board.noticeBoard.serviceImpl;

import board.noticeBoard.dto.member.MemberDto;
import board.noticeBoard.entity.Member;
import board.noticeBoard.repository.MemberRepository;
import board.noticeBoard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     *
     * @param member
     */
    @Override
    @Transactional
    public void signUp(MemberDto member) {
        memberRepository.save(Member.builder()
                .id(member.getId())
                .pw(passwordEncoder.encode(member.getPw()))
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build());
    }
}
