package com.ceos_19.vote.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VotingOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "votingOption_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "votingOption")
    private List<Vote> votes;

    @Column
    private int voteCount = 0;


    public void addVote(Vote vote) {
        if (votes != null) {
            votes.add(vote);
            voteCount++;
        }
    }


}
