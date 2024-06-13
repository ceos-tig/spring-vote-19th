package ceos.vote_be.team.controller;

import ceos.vote_be.annotation.LoginUser;
import ceos.vote_be.global.response.ApiResponse;
import ceos.vote_be.member.domain.Member;
import ceos.vote_be.team.domain.Team;
import ceos.vote_be.team.dto.TeamResponseDto;
import ceos.vote_be.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("") // 득표순으로 팀 조회
    public ResponseEntity<ApiResponse<List<TeamResponseDto>>> retrieveTeam() {
        List<Team> teams = teamService.retrieveTeamByCount();
        List<TeamResponseDto> teamResponseDtoList = new ArrayList<>();
        for (Team team : teams) {
            TeamResponseDto teamResponseDto = TeamResponseDto.createFromTeam(team);
            teamResponseDtoList.add(teamResponseDto);
        }
        ApiResponse<List<TeamResponseDto>> response = ApiResponse.of(200, "득표순으로 정렬된 팀 리스트", teamResponseDtoList);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/{teamId}/votes") // 데모데이 투표
    public ResponseEntity<ApiResponse<Integer>> voteTeam(@LoginUser Member member, @PathVariable("teamId") Long teamId) {
        int count = teamService.voteTeam(member.getMemberId(), teamId);
        ApiResponse<Integer> response = ApiResponse.of(200, "더해진 투표 수", count);
        return ResponseEntity.status(200).body(response);
    }

//    @PostMapping("/leader/{teamId}/votes")
//    public ResponseEntity<ApiResponse<Integer>> leaderVote(@PathVariable("teamId") Long teamId){
//
//    }
}
