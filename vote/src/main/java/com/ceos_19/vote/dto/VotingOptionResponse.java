package com.ceos_19.vote.dto;

import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.domain.Vote;
import com.ceos_19.vote.domain.VotingOption;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class VotingOptionResponse {

    private Long id;
    private String name;
    //private Topic topic;          // Infinite recursion
    //private List<Vote> votes;
    private int voteCount;

    public static VotingOptionResponse of(VotingOption votingOption) {
       return new VotingOptionResponse(
               votingOption.getId(),
               votingOption.getName(),
               //votingOption.getTopic(),
               //votingOption.getVotes(),
               votingOption.getVote_count()
       );
    }

}
