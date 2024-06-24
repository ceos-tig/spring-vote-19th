package ceos.vote_be.member.dto;

import ceos.vote_be.member.domain.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberLoginResponseDto {
    private String loginId;
    private String password;
    private String username;
    private Part part;
}
