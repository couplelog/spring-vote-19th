package com.ceos_19.vote.dto;

import com.ceos_19.vote.domain.VotingOption;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class VotingOptionDto {

    private Long id;
    private String name;

    public static VotingOptionDto of(VotingOption votingOption) {
        return new VotingOptionDto(
                votingOption.getId(),
                votingOption.getName()
        );
    }
}
