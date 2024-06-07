package com.ceos_19.vote.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class CreateVoteRequest {

    @NotNull(message = "투표 주제 아이디를 적어주세요.")
    private Long topicId;

    @NotNull(message = "투표 선택지 아이디를 적어주세요.")
    private Long votingOptionId;
}
