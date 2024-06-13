package ceos.vote_be.team.repository;

import ceos.vote_be.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    public List<Team> findAllByOrderByVoteCountDesc();
}
