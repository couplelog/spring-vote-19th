package com.ceos_19.vote.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
public class CreateVoteRequest {

    @NotNull(message = "투표 주제 아이디를 적어주세요.")
    private Long topicId;

    @NotNull(message = "투표 선택지 아이디를 적어주세요.")
    private Long votingOptionId;

    public CreateVoteRequest(Long topicId, Long votingOptionId) {
        this.topicId = topicId;
        this.votingOptionId = votingOptionId;
    }
}
