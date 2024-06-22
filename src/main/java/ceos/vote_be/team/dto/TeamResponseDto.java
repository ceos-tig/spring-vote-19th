package ceos.vote_be.team.dto;

import ceos.vote_be.team.domain.Team;
import lombok.Getter;

@Getter
public class TeamResponseDto {
    private String name;
    private int voteCount;

    private TeamResponseDto(String name, int voteCount) {
        this.name = name;
        this.voteCount = voteCount;
    }

    // 정적 팩토리 메서드
    public static TeamResponseDto createFromTeam(Team team) {
        return new TeamResponseDto(
                team.getName(),
                team.getVoteCount());
    }
}
