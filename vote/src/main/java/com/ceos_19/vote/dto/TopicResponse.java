package com.ceos_19.vote.dto;

import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.domain.VotingOption;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class TopicResponse {

    private Long id;
    private String name;
    private int minimumVotesRequired;
    List<VotingOptionDto> votingOptionDto;

    public static TopicResponse of(Topic topic) {
        List<VotingOptionDto> votingOptionDtos = topic.getVotingOptions().stream()
                .map(VotingOptionDto::of)
                .collect(Collectors.toList());

        return new TopicResponse(
                topic.getId(),
                topic.getName(),
                topic.getMinimumVotesRequired(),
                votingOptionDtos
        );
    }

}