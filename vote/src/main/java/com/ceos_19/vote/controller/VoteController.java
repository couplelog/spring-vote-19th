package com.ceos_19.vote.controller;

import com.ceos_19.vote.dto.CreateVoteRequest;
import com.ceos_19.vote.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    /**
     * User가 투표 요청
     */
    @PostMapping
    public ResponseEntity<Long> createVote (
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid final CreateVoteRequest createVoteRequest){

        final Long voteId = voteService.createVote(userDetails, createVoteRequest);

        return ResponseEntity.ok()
                .body(voteId);
    }




}