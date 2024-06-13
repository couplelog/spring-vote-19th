package com.ceos_19.vote.service;

import com.ceos_19.vote.common.api.ApiResponseDto;
import com.ceos_19.vote.common.api.ErrorResponse;
import com.ceos_19.vote.common.api.ResponseUtils;
import com.ceos_19.vote.common.enumSet.ErrorType;
import com.ceos_19.vote.common.exception.RestApiException;
import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.domain.VotingOption;
import com.ceos_19.vote.dto.VotingOptionResponse;
import com.ceos_19.vote.repository.TopicRepository;
import com.ceos_19.vote.repository.VotingOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VotingOptionService {

    private final VotingOptionRepository votingOptionRepository;
    private final TopicRepository topicRepository;

    @Transactional(readOnly = true)
    public ApiResponseDto<List<VotingOptionResponse>> getAllOptions() {

        List<VotingOption> votingOptions = votingOptionRepository.findAll();

        if (votingOptions.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_VOTINGOPTION);
        }

        return ResponseUtils.ok(votingOptions.stream().map(VotingOptionResponse::of).collect(Collectors.toList()),
                ErrorResponse.builder().status(200).message("요청 성공").build());
    }

    @Transactional(readOnly = true)
    public ApiResponseDto<VotingOptionResponse> getOption(Long id) {

        VotingOptionResponse votingOptionResponse = VotingOptionResponse.of(
                votingOptionRepository.findById(id)
                        .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_VOTINGOPTION)));

        return ResponseUtils.ok(votingOptionResponse, ErrorResponse.builder().status(200).message("요청 성공").build());
    }

    @Transactional(readOnly = true)
    public ApiResponseDto<List<VotingOptionResponse>> getOptionsByTopicId(Long id) {

        final Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_TOPIC));

        List<VotingOption> votingOptions = votingOptionRepository.findVotingOptionByTopic(topic);
        if (votingOptions.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_VOTINGOPTION);
        }

        return ResponseUtils.ok(votingOptions.stream().map(VotingOptionResponse::of).collect(Collectors.toList()),
                ErrorResponse.builder().status(200).message("요청 성공").build());
    }

}
