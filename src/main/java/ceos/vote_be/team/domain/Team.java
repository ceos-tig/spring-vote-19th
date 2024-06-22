package ceos.vote_be.team.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Team {
    @Id
    @GeneratedValue
    private Long teamId;

    private String name;
    private int voteCount = 0;

    public int vote() {
        this.voteCount += 1;
        return this.voteCount;
    }
}
