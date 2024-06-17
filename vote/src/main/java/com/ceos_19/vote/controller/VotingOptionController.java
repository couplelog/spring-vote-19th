package com.ceos_19.vote.controller;

import com.ceos_19.vote.common.api.ApiResponseDto;
import com.ceos_19.vote.dto.VotingOptionResponse;
import com.ceos_19.vote.service.VotingOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votingoptions")
public class VotingOptionController {

    private final VotingOptionService votingOptionService;

    /**
     * 모든 투표 선택지 반환
     */
    @GetMapping
    public ApiResponseDto<List<VotingOptionResponse>> getAllOptions() {

        return votingOptionService.getAllOptions();
    }

    /**
     * 하나의 투표 선택지 반환
     */
    @GetMapping("/{id}")
    public ApiResponseDto<VotingOptionResponse> getOption(@PathVariable("id") Long id) {

        return votingOptionService.getOption(id);
    }

    /**
     * 한 Topic의 모든 투표선택지 반환
     */
    @GetMapping("/topics/{id}")
    public ApiResponseDto<List<VotingOptionResponse>> getOptionsByTopicId(@PathVariable("id") Long id) {

        return votingOptionService.getOptionsByTopicId(id);
    }


}
