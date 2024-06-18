package ceos.vote_be.team.service;

import ceos.vote_be.global.code.ErrorCode;
import ceos.vote_be.global.exception.BusinessExceptionHandler;
import ceos.vote_be.leader.domain.Leader;
import ceos.vote_be.leader.repository.LeaderRepository;
import ceos.vote_be.member.domain.Member;
import ceos.vote_be.member.domain.Part;
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
    private final LeaderRepository leaderRepository;

    public List<Team> retrieveTeamByCount() {
        return teamRepository.findAllByOrderByVoteCountDesc();
    }

    public List<Leader> retrieveLeaderByCount() {
        return leaderRepository.findAllByOrderByVoteCountDesc();
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
    public int leaderVote(Long memberId, Long leaderId) { // 파트장 투표 로직
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        Leader leader = leaderRepository.findById(leaderId).orElseThrow(() -> new BusinessExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
        if (member.getPart().equals(leader.getPart())) { // 투표하는 사람과 파트장, 둘의 파트가 같아야 투표 가능
            int vote = leader.vote();
            leaderRepository.save(leader);
            return vote;
        } else {
            throw new BusinessExceptionHandler("파트가 달라서 투표 불가능",ErrorCode.BAD_REQUEST_ERROR);
        }
    }
}
