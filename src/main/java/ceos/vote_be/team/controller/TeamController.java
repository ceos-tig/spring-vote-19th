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
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("vote")
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/fe")
    public ResponseEntity<ApiResponse<Map<String, Object>>> checkFeVote(@LoginUser Member member) {
        Map<String, Object> status = teamService.checkFeVote(member.getMemberId());
        ApiResponse<Map<String, Object>> response = ApiResponse.of(200, "FE 투표 상태 조회", status);
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(response);
    }

    @PostMapping("/fe-vote")
    public ResponseEntity<ApiResponse<Void>> voteFe(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String leaderName = request.get("leaderName");
        teamService.voteFe(username, leaderName);
        ApiResponse<Void> response = ApiResponse.of(200, "FE 투표 완료", null);
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(response);
    }

    @PostMapping("/be")
    public ResponseEntity<ApiResponse<Map<String, Object>>> checkBeVote(@LoginUser Member member) {
        Map<String, Object> status = teamService.checkBeVote(member.getMemberId());
        ApiResponse<Map<String, Object>> response = ApiResponse.of(200, "BE 투표 상태 조회", status);
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(response);
    }

    @PostMapping("/be-vote")
    public ResponseEntity<ApiResponse<Void>> voteBe(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String leaderName = request.get("leaderName");
        teamService.voteBe(username, leaderName);
        ApiResponse<Void> response = ApiResponse.of(200, "BE 투표 완료", null);
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(response);
    }

    @PostMapping("/team")
    public ResponseEntity<ApiResponse<Map<String, Object>>> checkTeamVote(@LoginUser Member member) {
        Map<String, Object> status = teamService.checkTeamVote(member.getMemberId());
        ApiResponse<Map<String, Object>> response = ApiResponse.of(200, "팀 투표 상태 조회", status);
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(response);
    }

    @PostMapping("/team-vote")
    public ResponseEntity<ApiResponse<Void>> voteTeam(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String teamName = request.get("teamName");
        teamService.voteTeam(username, teamName);
        ApiResponse<Void> response = ApiResponse.of(200, "Team 투표 완료", null);
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(response);
    }

    @GetMapping("/fe-result")
    public ResponseEntity<List<Map<String, Object>>> getFeResults() {
        List<Map<String, Object>> results = teamService.getFeResults();
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(results);
    }

    @GetMapping("/be-result")
    public ResponseEntity<List<Map<String, Object>>> getBeResults() {
        List<Map<String, Object>> results = teamService.getBeResults();
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(results);
    }

    @GetMapping("/team-result")
    public ResponseEntity<List<Map<String, Object>>> getTeamResults() {
        List<Map<String, Object>> results = teamService.getTeamResults();
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .body(results);
    }
}
