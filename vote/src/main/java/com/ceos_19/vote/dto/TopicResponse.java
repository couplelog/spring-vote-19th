package com.ceos_19.vote.dto;

import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.domain.VotingOption;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class TopicResponse {

    private Long id;
    private String name;
    private int minimumVotesRequired;
    List<VotingOption> votingOptions;


    public static TopicResponse of(Topic topic) {
        return new TopicResponse(
                topic.getId(),
                topic.getName(),
                topic.getMinimumVotesRequired(),
                topic.getVotingOptions()
        );
    }
}