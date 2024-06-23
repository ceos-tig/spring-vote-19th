package ceos.vote_be.leader.dto;

import ceos.vote_be.leader.domain.Leader;
import ceos.vote_be.team.domain.Team;
import ceos.vote_be.team.dto.TeamResponseDto;
import lombok.Getter;

@Getter
public class LeaderResponseDto {
    private String name;
    private int voteCount;


    private LeaderResponseDto(String name, int voteCount) {
        this.name = name;
        this.voteCount = voteCount;
    }

    // 정적 팩토리 메서드
    public static LeaderResponseDto createFromLeader(Leader leader) {
        return new LeaderResponseDto(
                leader.getName(),
                leader.getVoteCount());
    }
}
