package ceos.vote_be.member.dto;

import ceos.vote_be.member.domain.Part;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinRequestDto {
    private String loginId;
    private String password;
    private String email;
    private Part part;
    private String username;
    private Long teamId;
}
