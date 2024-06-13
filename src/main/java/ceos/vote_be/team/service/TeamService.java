package ceos.vote_be.team.service;

import ceos.vote_be.global.code.ErrorCode;
import ceos.vote_be.global.exception.BusinessExceptionHandler;
import ceos.vote_be.member.domain.Member;
import ceos.vote_be.member.repository.MemberRepository;
import ceos.vote_be.team.domain.Team;
import ceos.vote_be.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public List<Team> retrieveTeamByCount() {
        return teamRepository.findAllByOrderByVoteCountDesc();
    }

    @Transactional
    public int voteTeam(Long memberId, Long teamId) { // 데모데이 투표 로직
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        if(member.getTeam().equals(team)){ // 내 팀에 투표는 불가능!
            throw new BusinessExceptionHandler("본인의 팀에는 투표 불가능",ErrorCode.BAD_REQUEST_ERROR);
        } else {
            int count = team.vote();
            teamRepository.save(team);
            return count;
        }
    }

    @Transactional
    public void leaderVote(Long teamId) {
        //
    }
}
