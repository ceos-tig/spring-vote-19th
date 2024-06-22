package ceos.vote_be.leader.repository;

import ceos.vote_be.leader.domain.Leader;
import ceos.vote_be.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaderRepository extends JpaRepository<Leader,Long> {
    public List<Leader> findAllByOrderByVoteCountDesc();
}
