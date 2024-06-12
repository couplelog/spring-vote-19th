package com.ceos_19.vote.service;

import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.domain.User;
import com.ceos_19.vote.domain.Vote;
import com.ceos_19.vote.domain.VotingOption;
import com.ceos_19.vote.dto.CreateVoteRequest;
import com.ceos_19.vote.repository.TopicRepository;
import com.ceos_19.vote.repository.UserRepository;
import com.ceos_19.vote.repository.VoteRepository;
import com.ceos_19.vote.repository.VotingOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VoteService {

    private final VoteRepository voteRepository;
    private final VotingOptionRepository votingOptionRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createVote(UserDetails userDetails, CreateVoteRequest createVoteRequest){

        final User voter = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User is not found"));
        final Topic topic = topicRepository.findById(createVoteRequest.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic is not found"));
        final VotingOption votingOption = votingOptionRepository.findById(createVoteRequest.getVotingOptionId())
                .orElseThrow(() -> new RuntimeException("Voting Option is not found"));

        topic.addVotingOption(votingOption);       // 투표수 증가

        Vote vote = Vote.builder()
                .user(voter)
                .votingOption(votingOption)
                .build();

        votingOption.addVote(vote);

        return voteRepository.save(vote)
                .getId();
    }




}
