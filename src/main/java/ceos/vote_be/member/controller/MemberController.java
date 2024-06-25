package ceos.vote_be.member.controller;

import ceos.vote_be.annotation.LoginUser;
import ceos.vote_be.member.domain.Member;
import ceos.vote_be.member.dto.MemberJoinRequestDto;
import ceos.vote_be.member.dto.MemberLoginRequestDto;
import ceos.vote_be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
//@CrossOrigin(originPatterns = "http://43.202.139.24:3000")
@CrossOrigin(originPatterns = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public void joinMember(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        memberService.joinMember(memberJoinRequestDto);
    }

    @PostMapping("/login")
    public void loginMember(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {

    }

    @GetMapping("/info")
    public void loginUserInfo(@LoginUser Member member) {
        System.out.println("member.getLoginId() = " + member.getLoginId());
        System.out.println("member.getUsername() = " + member.getUsername());
        System.out.println("member.getEmail() = " + member.getEmail());
    }
}
