package ceos.vote_be.member.service;

import ceos.vote_be.global.code.ErrorCode;
import ceos.vote_be.global.exception.BusinessExceptionHandler;
import ceos.vote_be.member.domain.Member;
import ceos.vote_be.member.domain.UserRoleEnum;
import ceos.vote_be.member.dto.MemberJoinRequestDto;
import ceos.vote_be.member.dto.MemberLoginRequestDto;
import ceos.vote_be.member.repository.MemberRepository;
import ceos.vote_be.team.domain.Team;
import ceos.vote_be.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void joinMember(MemberJoinRequestDto memberRequestDto) {
        if (!validateDuplicateLoginId(memberRequestDto)) {
            throw new BusinessExceptionHandler("사용할 수 없는 아이디 입니다.",ErrorCode.BAD_REQUEST_ERROR);
        }
        if (!validateDuplicateEmail(memberRequestDto)) {
            throw new BusinessExceptionHandler("해당 이메일로 회원가입한 정보가 존재합니다.",ErrorCode.BAD_REQUEST_ERROR);
        }
        Team team = teamRepository.findById(memberRequestDto.getTeamId()).orElseThrow(() -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        Member member = Member.builder()
                .username(memberRequestDto.getUsername())
                .email(memberRequestDto.getEmail())
                .loginId(memberRequestDto.getLoginId())
                .part(memberRequestDto.getPart())
                .password(bCryptPasswordEncoder.encode(memberRequestDto.getPassword()))
                .team(team)
                .isVoted(0)
                .role(UserRoleEnum.USER)
                .build();
        memberRepository.save(member);
    }

    @Transactional
    public void loginMember(MemberLoginRequestDto memberLoginRequestDto) {
        Optional<Member> findMember = memberRepository.findByLoginId(memberLoginRequestDto.getLoginId());
        if (findMember.isPresent()) { // 해당 로그인 아이디를 가진 사용자가 있다면
            if (findMember.get().getPassword().equals(memberLoginRequestDto.getPassword())) { // 비밀번호가 일치한다면
                // Login
            } else {
                System.out.println(findMember.get().getPassword());
                throw new BusinessExceptionHandler(ErrorCode.NOT_VALID_ERROR);
            }
        }
    }

    public boolean validateDuplicateLoginId(MemberJoinRequestDto memberJoinRequestDto) {
        Optional<Member> findMember = memberRepository.findByLoginId(memberJoinRequestDto.getLoginId());
        if (findMember.isPresent()) { // 이미 있는 아이디
            return false;
        }
        return true;
    }

    public boolean validateDuplicateEmail(MemberJoinRequestDto memberJoinRequestDto) {
        Optional<Member> findMember = memberRepository.findByEmail(memberJoinRequestDto.getEmail());
        if (findMember.isPresent()) { // 이미 있는 이메일
            return false;
        }
        return true;
    }

    @Transactional
    public void vote(Member member) {
        if (member.getIsVoted() != 0) {
            throw new BusinessExceptionHandler("이미 투표하였습니다.", ErrorCode.BAD_REQUEST_ERROR);
        }
        member.vote();
        memberRepository.save(member);
    }

}
