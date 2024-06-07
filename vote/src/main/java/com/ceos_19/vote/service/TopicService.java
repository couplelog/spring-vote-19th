package com.ceos_19.vote.service;

import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.domain.VotingOption;
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
    public List<TopicResponse> getAllTopics(){

        List<Topic> topics = topicRepository.findAll();
        List<TopicResponse> topicResponses = new ArrayList<>();
        for (Topic topic : topics) {
            topicResponses.add(TopicResponse.of(topic));
        }

        return topicResponses;
    }

    @Transactional(readOnly = true)
    public TopicResponse getTopicById(Long id) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic is not found"));

        return TopicResponse.of(topic);
    }

    @Transactional(readOnly = true)
    public VotingOption getTopVotedOption(Long topicId) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic is not found"));
        List<VotingOption> votingOptions = votingOptionRepository.findVotingOptionByTopic(topic);

        int totalVotes = votingOptions.stream().mapToInt(VotingOption::getVoteCount).sum();
        if (totalVotes < topic.getMinimumVotesRequired()) {
            throw new RuntimeException("Not enough votes to show results");
        }

        return votingOptionRepository.findTopByTopicOrderByVoteCountDesc(topic)
                .orElseThrow(() -> new RuntimeException("No voting options found for this topic"));
    }

    @Transactional(readOnly = true)
    public List<VotingOptionCountResponse> getCurrentResults(Long topicId){

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic is not found"));
        List<VotingOption> votingOptions = votingOptionRepository.findVotingOptionByTopic(topic);

        return votingOptionRepository.findVotingOptionSummariesByTopicId(topicId);
    }

}
