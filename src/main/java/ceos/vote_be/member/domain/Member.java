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

    private Integer isFEVoted;
    private Integer isBEVoted;
    private Integer isTeamVoted;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public void voteFE() {
        this.isFEVoted = 1;
//        System.out.println("voted");
    }

    public void voteBE() {
        this.isBEVoted = 1;
//        System.out.println("voted");
    }

    public void voteTeam() {
        this.isTeamVoted = 1;
//        System.out.println("voted");
    }
}
