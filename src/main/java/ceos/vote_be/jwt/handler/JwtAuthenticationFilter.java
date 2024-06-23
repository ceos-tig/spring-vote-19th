package ceos.vote_be.jwt.handler;

import ceos.vote_be.member.domain.UserRoleEnum;
import ceos.vote_be.member.dto.MemberDetailsImpl;
import ceos.vote_be.jwt.TokenProvider;
import ceos.vote_be.member.dto.MemberLoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final TokenProvider tokenProvider;

    public JwtAuthenticationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        setFilterProcessesUrl("/api/user/login");  // 로그인 URL 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            MemberLoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), MemberLoginRequestDto.class);     // 2.
            System.out.println("requestDto.getLoginId() = " + requestDto.getLoginId());
            System.out.println("requestDto.getPassword() = " + requestDto.getPassword());
            return getAuthenticationManager().authenticate(         // 3.
                    new UsernamePasswordAuthenticationToken(requestDto.getLoginId(), requestDto.getPassword(), null)    // 4.
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    // 로그인 성공 시
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws ServletException, IOException {
        log.info("로그인 성공 및 JWT 생성");
        String username = ((MemberDetailsImpl) authResult.getPrincipal()).getUsername();    // username
        String loginId = ((MemberDetailsImpl) authResult.getPrincipal()).getMember().getLoginId();
        UserRoleEnum role = ((MemberDetailsImpl) authResult.getPrincipal()).getUser().getRole();      // role

        String token = tokenProvider.createAccessToken(loginId, authResult);

        response.addHeader("Authorization", token);
    }

    // 로그인 실패 시
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        log.info("로그인 실패");
        log.error(failed.getMessage());
        response.setStatus(401);
    }
}
