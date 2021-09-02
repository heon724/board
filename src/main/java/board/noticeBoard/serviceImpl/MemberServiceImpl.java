package board.noticeBoard.serviceImpl;

import board.noticeBoard.component.JWTTokenComponent;
import board.noticeBoard.dto.member.MemberIdDupCheckDto;
import board.noticeBoard.dto.member.MemberSignUpDto;
import board.noticeBoard.entity.Member;
import board.noticeBoard.repository.MemberRepository;
import board.noticeBoard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenComponent jwtComp;

    /**
     * 회원가입
     *
     * @param member
     */
    @Override
    @Transactional
    public void signUp(MemberSignUpDto member) {
        memberRepository.save(Member.builder()
                .id(member.getId())
                .pw(passwordEncoder.encode(member.getPw()))
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build());
    }

    /**
     * 아이디 중복확인
     *
     * @param id
     * @return
     */

    @Override
    public MemberIdDupCheckDto memberIdDupCheck(String id) {
        String idDup = memberRepository.findByMemberId(id);

        if(idDup ==null) {
            return MemberIdDupCheckDto.builder()
                    .dupYn("N")
                    .build();
        }
        else {
            return MemberIdDupCheckDto.builder()
                    .dupYn("Y")
                    .build();
        }
    }
}
