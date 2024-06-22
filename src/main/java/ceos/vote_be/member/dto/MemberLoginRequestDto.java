package ceos.vote_be.member.dto;

import lombok.Getter;

@Getter
public class MemberLoginRequestDto {
    private String loginId;
    private String password;
}
