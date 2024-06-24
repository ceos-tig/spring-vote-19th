package ceos.vote_be.team.controller;

import ceos.vote_be.annotation.LoginUser;
import ceos.vote_be.global.response.ApiResponse;
import ceos.vote_be.leader.domain.Leader;
import ceos.vote_be.leader.dto.LeaderResponseDto;
import ceos.vote_be.member.domain.Member;
import ceos.vote_be.team.domain.Team;
import ceos.vote_be.team.dto.TeamResponseDto;
import ceos.vote_be.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("vote")
@CrossOrigin(originPatterns = "http://43.202.139.24/:3000")
public class TeamController {
    private final TeamService teamService;

//    @GetMapping("") // 득표순으로 팀 조회
//    public ResponseEntity<ApiResponse<List<TeamResponseDto>>> retrieveTeam() {
//        List<Team> teams = teamService.retrieveTeamByCount();
//        List<TeamResponseDto> teamResponseDtoList = new ArrayList<>();
//        for (Team team : teams) {
//            TeamResponseDto teamResponseDto = TeamResponseDto.createFromTeam(team);
//            teamResponseDtoList.add(teamResponseDto);
//        }
//        ApiResponse<List<TeamResponseDto>> response = ApiResponse.of(200, "득표순으로 정렬된 팀 리스트", teamResponseDtoList);
//        return ResponseEntity.status(200).body(response);
//    }
//
//    @GetMapping("/leader") // 득표순으로 파트장 조회
//    public ResponseEntity<ApiResponse<List<LeaderResponseDto>>> retrieveLeader() {
//        List<Leader> leaders = teamService.retrieveLeaderByCount();
//        List<LeaderResponseDto> leaderResponseDtoList = new ArrayList<>();
//        for (Leader leader : leaders) {
//            LeaderResponseDto leaderResponseDto = LeaderResponseDto.createFromLeader(leader);
//            leaderResponseDtoList.add(leaderResponseDto);
//        }
//        ApiResponse<List<LeaderResponseDto>> response = ApiResponse.of(200, "득표순으로 정렬된 파트장 리스트", leaderResponseDtoList);
//        return ResponseEntity.status(200).body(response);
//    }
//
//    @PostMapping("/{teamId}/votes") // 데모데이 투표
//    public ResponseEntity<ApiResponse<Integer>> voteTeam(@LoginUser Member member, @PathVariable("teamId") Long teamId) {
//        int count = teamService.voteTeam(member.getMemberId(), teamId);
//        ApiResponse<Integer> response = ApiResponse.of(200, "더해진 투표 수", count);
//        return ResponseEntity.status(200).body(response);
//    }
//
//    @PostMapping("/leader/{leaderId}/votes")
//    public ResponseEntity<ApiResponse<Integer>> leaderVote(@LoginUser Member member, @PathVariable("leaderId") Long leaderId){
//        int voteResult = teamService.leaderVote(member.getMemberId(), leaderId);
//        ApiResponse<Integer> response = ApiResponse.of(200, "더해진 투표 수", voteResult);
//        return ResponseEntity.status(200).body(response);
//    }


    @PostMapping("/fe")
    public ResponseEntity<ApiResponse<Map<String, Object>>> checkFeVote(@LoginUser Member member) {
        Map<String, Object> status = teamService.checkFeVote(member.getMemberId());
        ApiResponse<Map<String, Object>> response = ApiResponse.of(200, "FE 투표 상태 조회", status);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/fe-vote")
    public ResponseEntity<ApiResponse<Void>> voteFe(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String leaderName = request.get("leaderName");
        teamService.voteFe(username, leaderName);
        ApiResponse<Void> response = ApiResponse.of(200, "FE 투표 완료", null);
        return ResponseEntity.status(200).body(response);
    }


    @PostMapping("/be")
    public ResponseEntity<ApiResponse<Map<String, Object>>> checkBeVote(@LoginUser Member member) {
        Map<String, Object> status = teamService.checkBeVote(member.getMemberId());
        ApiResponse<Map<String, Object>> response = ApiResponse.of(200, "BE 투표 상태 조회", status);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/be-vote")
    public ResponseEntity<ApiResponse<Void>> voteBe(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String leaderName = request.get("leaderName");
        teamService.voteBe(username, leaderName);
        ApiResponse<Void> response = ApiResponse.of(200, "BE 투표 완료", null);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/team")
    public ResponseEntity<ApiResponse<Map<String, Object>>> checkTeamVote(@LoginUser Member member) {
        Map<String, Object> status = teamService.checkTeamVote(member.getMemberId());
        ApiResponse<Map<String, Object>> response = ApiResponse.of(200, "팀 투표 상태 조회", status);
        return ResponseEntity.status(200).body(response);
    }


    @PostMapping("/team-vote")
    public ResponseEntity<ApiResponse<Void>> voteTeam(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String teamName = request.get("teamName");
        teamService.voteTeam(username, teamName);
        ApiResponse<Void> response = ApiResponse.of(200, "Team 투표 완료", null);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/fe-result")
    public ResponseEntity<List<Map<String, Object>>> getFeResults() {
        List<Map<String, Object>> results = teamService.getFeResults();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/be-result")
    public ResponseEntity<List<Map<String, Object>>> getBeResults() {
        List<Map<String, Object>> results = teamService.getBeResults();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/team-result")
    public ResponseEntity<List<Map<String, Object>>> getTeamResults() {
        List<Map<String, Object>> results = teamService.getTeamResults();
        return ResponseEntity.ok(results);
    }
}
