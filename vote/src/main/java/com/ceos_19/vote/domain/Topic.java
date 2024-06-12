package com.ceos_19.vote.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;

    @Column(nullable = false, unique = true)       // 투표 주제
    private String name;

    @Column(nullable = false)
    private int minimumVotesRequired; // 최소 투표 요구 수

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VotingOption> votingOptions = new ArrayList<>();


    @Builder
    public Topic(String name, int minimumVotesRequired){
        this.name = name;
        this.minimumVotesRequired = minimumVotesRequired;
        votingOptions = new ArrayList<>();
    }

    public void addVotingOption(VotingOption votingOption){
        if(this.votingOptions != null){
            this.votingOptions.add(votingOption);
        }
    }

}
