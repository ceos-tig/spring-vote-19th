package ceos.vote_be.member.domain;

import ceos.vote_be.team.domain.Team;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String loginId;
    private String password;
    private String email;
    private String username;

    private Integer isVoted;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public void vote() {
        this.isVoted = 1;
        System.out.println("voted");
    }
}
