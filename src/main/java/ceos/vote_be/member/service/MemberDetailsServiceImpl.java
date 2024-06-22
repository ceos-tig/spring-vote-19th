package ceos.vote_be.member.service;

import ceos.vote_be.member.domain.Member;
import ceos.vote_be.member.dto.MemberDetailsImpl;
import ceos.vote_be.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsServiceImpl implements UserDetailsService {		// 1.
    private final MemberRepository memberRepository;

    // DB 에 저장된 사용자 정보와 일치하는지 여부를 판단
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new UsernameNotFoundException("Not Found " + loginId));

        return new MemberDetailsImpl(member);
    }
}
