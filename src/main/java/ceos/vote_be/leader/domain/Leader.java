package ceos.vote_be.leader.domain;

import ceos.vote_be.member.domain.Part;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Leader {
    @Id
    @GeneratedValue
    private Long leaderId;

    private String name;
    private int voteCount = 0;

    @Enumerated(EnumType.STRING)
    private Part part;

    public int vote() {
        this.voteCount += 1;
        return this.voteCount;
    }
}
