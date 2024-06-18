package com.ceos_19.vote.controller;

import com.ceos_19.vote.common.api.ApiResponseDto;
import com.ceos_19.vote.dto.TopicResponse;
import com.ceos_19.vote.dto.VotingOptionCountResponse;
import com.ceos_19.vote.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    /**
     * 모든 Topic 반환
     */
    @GetMapping
    public ApiResponseDto<List<TopicResponse>> getAllTopics(){

        return topicService.getAllTopics();
    }

    /**
     * 하나의 Topic 반환 by TopicId
     */
    @GetMapping("/{id}")
    public ApiResponseDto<TopicResponse> getTopic(@PathVariable("id") Long id
    ) {

        return topicService.getTopicById(id);
    }

    /**
     * 하나의 Topic 결과 계산 -> 최다득표 VotingOptions 반환
     * 정해진 모든 인원이 투표했을 때만 반환
     */
    @GetMapping("/{id}/top-voted-option")
    public ApiResponseDto<List<VotingOptionCountResponse>> getFinalResult(@PathVariable("id") Long id) {

        return topicService.getTopVotedOption(id);
    }

    /**
     * 하나의 Topic 결과 계산 -> 모든 VotingOption의 득표수 반환
     * 정해진 모든 인원이 투표하지 않아도 반환 => 현황을 확인할 수 있음
     */
    @GetMapping("/{id}/results")
    public ApiResponseDto<List<VotingOptionCountResponse>> getCurrentResult(@PathVariable("id") Long id) {

        return topicService.getCurrentResults(id);
    }

}
