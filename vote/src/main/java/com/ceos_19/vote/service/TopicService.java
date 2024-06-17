package com.ceos_19.vote.service;

import com.ceos_19.vote.common.api.ApiResponseDto;
import com.ceos_19.vote.common.api.ErrorResponse;
import com.ceos_19.vote.common.api.ResponseUtils;
import com.ceos_19.vote.common.enumSet.ErrorType;
import com.ceos_19.vote.common.exception.RestApiException;
import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.dto.VotingOptionCountResponse;
import com.ceos_19.vote.repository.TopicRepository;
import com.ceos_19.vote.repository.VotingOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ceos_19.vote.dto.TopicResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;
    private final VotingOptionRepository votingOptionRepository;

    @Transactional(readOnly = true)
    public ApiResponseDto<List<TopicResponse>> getAllTopics(){

        List<Topic> topics = topicRepository.findAll();
        List<TopicResponse> topicResponses = new ArrayList<>();
        for (Topic topic : topics) {
            topicResponses.add(TopicResponse.of(topic));
        }

        return ResponseUtils.ok(topicResponses, ErrorResponse.builder().status(200).message("요청 성공").build());
    }

    @Transactional(readOnly = true)
    public ApiResponseDto<TopicResponse> getTopicById(Long id) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_TOPIC));

        return ResponseUtils.ok(TopicResponse.of(topic), ErrorResponse.builder().status(200).message("요청 성공").build());
    }

    @Transactional(readOnly = true)
    public ApiResponseDto<List<VotingOptionCountResponse>> getTopVotedOption(Long topicId) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_TOPIC));

        // 최소 투표 요구 수를 만족하는지 확인
        long totalVotes = votingOptionRepository.countVotesByTopic(topic);
        if (totalVotes < topic.getMinimumVotesRequired()) {
            throw new RestApiException(ErrorType.MINIMUM_VOTES_NOT_REACHED);
        }

        List<VotingOptionCountResponse> votingOptionCountResponses = votingOptionRepository.findTopByTopicOrderByVoteCountDesc(topic);

        return ResponseUtils.ok(votingOptionCountResponses, ErrorResponse.builder().status(200).message("요청 성공").build());
    }

    @Transactional(readOnly = true)
    public ApiResponseDto<List<VotingOptionCountResponse>> getCurrentResults(Long topicId){

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_TOPIC));

        List<VotingOptionCountResponse> currentResults = votingOptionRepository.findVotingOptionSummariesByTopicId(topic.getId());

        return ResponseUtils.ok(currentResults, ErrorResponse.builder().status(200).message("요청 성공").build());
    }

}
