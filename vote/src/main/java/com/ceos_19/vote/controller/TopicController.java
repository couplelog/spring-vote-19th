package com.ceos_19.vote.controller;

import com.ceos_19.vote.domain.VotingOption;
import com.ceos_19.vote.dto.TopicResponse;
import com.ceos_19.vote.dto.VotingOptionCountResponse;
import com.ceos_19.vote.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TopicResponse>> getAllTopics(){

        return ResponseEntity.ok(topicService.getAllTopics());
    }

    /**
     * 하나의 Topic 반환 by TopicId
     */
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopic(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(topicService.getTopicById(id));
    }

    /**
     * 하나의 Topic 결과 계산 -> 하나의 VotingOption 반환
     * 모든 인원이 투표했을 때만 반환
     */
    @GetMapping("/{id}/top-voted-option")
    public ResponseEntity<VotingOption> getFinalResult(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(topicService.getTopVotedOption(id));
    }

    /**
     * 하나의 Topic 결과 계산 -> 모든 VotingOption 반환
     * 투표 인원수 확인 X
     */
    @GetMapping("/{id}/results")
    public ResponseEntity<List<VotingOptionCountResponse>> getCurrentResult(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(topicService.getCurrentResults(id));
    }


}
