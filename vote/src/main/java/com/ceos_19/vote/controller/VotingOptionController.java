package com.ceos_19.vote.controller;

import com.ceos_19.vote.dto.VotingOptionResponse;
import com.ceos_19.vote.service.VotingOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<VotingOptionResponse>> getAllOptions() {

        return ResponseEntity.ok()
                .body(votingOptionService.getAllOptions());
    }

    /**
     * 하나의 투표 선택지 반환
     */
    @GetMapping("/{id}")
    public ResponseEntity<VotingOptionResponse> getOption(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(votingOptionService.getOption(id));
    }

    /**
     * 한 Topic의 모든 투표선택지 반환
     */
    @GetMapping("/topic/{id}")
    public ResponseEntity<List<VotingOptionResponse>> getOptionsByTopicId(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(votingOptionService.getOptionsByTopicId(id));
    }


}
