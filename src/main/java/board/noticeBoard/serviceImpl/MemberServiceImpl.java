package board.noticeBoard.serviceImpl;

import board.noticeBoard.component.JWTTokenComponent;
import board.noticeBoard.dto.member.LoginResponseDto;
import board.noticeBoard.dto.member.MemberIdDupCheckDto;
import board.noticeBoard.dto.member.MemberLoginDto;
import board.noticeBoard.dto.member.MemberSignUpDto;
import board.noticeBoard.entity.Member;
import board.noticeBoard.exception.NoticeBoardException;
import board.noticeBoard.repository.MemberRepository;
import board.noticeBoard.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

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

        if (idDup == null) {
            return MemberIdDupCheckDto.builder()
                    .dupYn("N")
                    .build();
        } else {
            return MemberIdDupCheckDto.builder()
                    .dupYn("Y")
                    .build();
        }
    }

    /**
     * 로그인
     *
     * @param member
     * @return
     */

    @Override
    public ResponseEntity<LoginResponseDto> login(MemberLoginDto member) {
        Member memberEntity = memberRepository.findById(member.getId())
                .orElseThrow(() -> new NoticeBoardException(HttpStatus.BAD_REQUEST,
                        "error.user.notfound.user.valid.E0001"));

        if (!passwordEncoder.matches(member.getPw(), memberEntity.getPw())) {
            throw new NoticeBoardException(HttpStatus.UNAUTHORIZED,
                    "error,user.login.fail.userpw.E0002");
        }

        return ResponseEntity.ok(LoginResponseDto.builder()
                .accessToken(jwtComp.createToken(memberEntity.getId(), memberEntity.getRoles()))
                .member(LoginResponseDto.Member.builder()
                        .id(memberEntity.getId())
                        .name(memberEntity.getName())
                        .nickname(memberEntity.getNickname())
                        .email(memberEntity.getEmail())
                        .phone(memberEntity.getPhone())
                        .build())
                .build());
    }
}
