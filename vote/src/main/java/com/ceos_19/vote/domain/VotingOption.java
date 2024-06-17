package com.ceos_19.vote.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    //@JsonIgnore
    private Topic topic;

    @OneToMany(mappedBy = "votingOption")
    private List<Vote> votes;

    @Column
    private int vote_count = 0;

    @Builder
    public VotingOption(String name, Topic topic) {
        this.name = name;
        this.topic = topic;
    }

    public void increaseVoteCount(Vote vote) {
        if (votes != null) {
            votes.add(vote);
            vote_count++;
        }
    }

}
