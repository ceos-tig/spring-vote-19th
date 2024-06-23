package ceos.vote_be.leader.repository;

import ceos.vote_be.leader.domain.Leader;
import ceos.vote_be.member.domain.Part;
import ceos.vote_be.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaderRepository extends JpaRepository<Leader,Long> {
    public List<Leader> findAllByOrderByVoteCountDesc();

    Optional<Leader> findByName(String leaderName);
    //List<Leader> findByPartOrderByVoteCountDescNameAsc(String part);
    List<Leader> findByPartOrderByVoteCountDescNameAsc(Part part);

}
