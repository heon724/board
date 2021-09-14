package board.noticeBoard.serviceImpl;

import board.noticeBoard.component.JWTTokenComponent;
import board.noticeBoard.dto.member.*;
import board.noticeBoard.entity.Member;
import board.noticeBoard.exception.NoticeBoardException;
import board.noticeBoard.repository.MemberRepository;
import board.noticeBoard.service.MemberService;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Locale;

@Service
public class MemberServiceImpl implements MemberService {
    Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenComponent jwtComp;

    @Autowired
    MessageSource messageSource;

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


    /**
     * 아이디 중복확인
     *
     * @param id
     * @return
     */

    @Override
    public DupCheckDto memberIdDupCheck(String id) {
        String idDup = memberRepository.findByMemberId(id);

        if (idDup == null) {
            return DupCheckDto.builder()
                    .dupYn("N")
                    .build();
        } else {
            return DupCheckDto.builder()
                    .dupYn("Y")
                    .build();
        }
    }

    /**
     * 이메일 중복확인
     *
     * @param phone
     * @return
     */
    @Override
    public DupCheckDto memberPhoneDupCheck(String phone) {
        String phoneDup = memberRepository.findByMemberPhone(phone);

        if (phoneDup == null) {
            return DupCheckDto.builder()
                    .dupYn("N")
                    .build();
        } else {
            return DupCheckDto.builder()
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
    public ResponseEntity<LoginResponseDto> login(LoginDto member) {
        Member memberEntity = memberRepository.findById(member.getId())
                .orElseThrow(() -> new NoticeBoardException(HttpStatus.BAD_REQUEST,
                        messageSource.getMessage("error.user.notfound.user.valid.E0001", null, Locale.KOREA)));


        if (!passwordEncoder.matches(member.getPw(), memberEntity.getPw())) {
            throw new NoticeBoardException(HttpStatus.UNAUTHORIZED,
                    messageSource.getMessage("error.user.login.fail.userpw.E0002", null, Locale.KOREA));
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

    /**
     * 아이디찾기
     *
     * @param name
     * @param phone
     * @return
     */

    @Override
    public FindIdDto findId(String name, String phone) {
        return FindIdDto.builder()
                .id(memberRepository.findByNameAndPhone(name, phone))
                .build();
    }


    /**
     * 내정보 보기
     *
     * @param authentication
     * @return
     */
    @Override
    public MemberDto findMember(Authentication authentication) {
        Member userDetails = (Member) authentication.getPrincipal();

        return MemberDto.builder()
                .id(userDetails.getId())
                .name(userDetails.getName())
                .email(userDetails.getEmail())
                .nickname(userDetails.getNickname())
                .phone(userDetails.getPhone())
                .build();
    }

    /**
     * 비밀번호 찾기(초기화)
     * 초기화 규칙 : 아이디 앞3자리 + 휴대전화 뒷 4자리 + !
     *
     * @param authentication
     * @return
     */
    @Transactional
    @Override
    public int resetPassword(Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();

        return memberRepository.saveResetPw(member.getId(),
                passwordEncoder.encode(member.getId().substring(0, 3)
                        + member.getPhone().substring(member.getPhone().length() - 4) + "!"));
    }
    /**
     * 내 정보 수정
     *
     * @param member
     * @param authentication
     * @return
     */
    @Transactional
    @Override
    public int updateMember(UpdateMemberDto member, Authentication authentication) {
        Member userDetails = (Member) authentication.getPrincipal();

        // 사용자 이름
        if (StringUtils.isBlank(member.getName())) {
            throw new NoticeBoardException(HttpStatus.BAD_REQUEST,
                    messageSource.getMessage("error.user.notfound.user.valid.E0003",
                            new String[]{"사용자 이름"}, Locale.KOREA));
        }

        // 핸드폰 번호 중복 확인
        if (!StringUtils.isBlank(member.getPhone())) {
            if (memberRepository.findByMemberPhone(member.getPhone()) != null
                    && !member.getPhone().equals(userDetails.getPhone())) {
                throw new NoticeBoardException(HttpStatus.CONFLICT,
                        messageSource.getMessage("error.user.dublication.user.phone.E0004",
                                new String[]{member.getPhone()}, Locale.KOREA));
            }
        } else { // 핸드폰 번호
            throw new NoticeBoardException(HttpStatus.BAD_REQUEST,
                    messageSource.getMessage("error.user.notfound.user.valid.E0003",
                            new String[]{"휴대전화"}, Locale.KOREA));
        }

        // 이메일
        if (StringUtils.isBlank(member.getName())) {
            throw new NoticeBoardException(HttpStatus.BAD_REQUEST,
                    messageSource.getMessage("error.user.notfound.user.valid.E0003",
                            new String[]{"이메일"}, Locale.KOREA));
        }

        // 닉네임
        if (StringUtils.isBlank(member.getName())) {
            throw new NoticeBoardException(HttpStatus.BAD_REQUEST,
                    messageSource.getMessage("error.user.notfound.user.valid.E0003",
                            new String[]{"닉네임"}, Locale.KOREA));
        }

        return memberRepository.saveMember(UpdateMemberDto.builder()
                .name(member.getName())
                .phone(member.getPhone())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build(), userDetails.getId());
    }

    /**
     * 회원 탈퇴
     *
     * @param authentication
     * @return
     */
    @Transactional
    @Override
    public int deleteMember(Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();

        return memberRepository.deleteMember(member.getId());
    }

}