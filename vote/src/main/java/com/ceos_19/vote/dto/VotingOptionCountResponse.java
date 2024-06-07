package com.ceos_19.vote.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class VotingOptionCountResponse {

    private Long votingOptionId;
    private int votingOptionCount;

    public static VotingOptionCountResponse of(Long votingOptionId, int votingOptionCount){
        return new VotingOptionCountResponse(
                votingOptionId,
                votingOptionCount
        );
    }
}
