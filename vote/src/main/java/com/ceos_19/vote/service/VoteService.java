package com.ceos_19.vote.service;

import com.ceos_19.vote.common.api.ApiResponseDto;
import com.ceos_19.vote.common.api.ErrorResponse;
import com.ceos_19.vote.common.api.ResponseUtils;
import com.ceos_19.vote.common.api.SuccessResponse;
import com.ceos_19.vote.common.enumSet.ErrorType;
import com.ceos_19.vote.common.exception.RestApiException;
import com.ceos_19.vote.domain.*;
import com.ceos_19.vote.dto.CreateVoteRequest;
import com.ceos_19.vote.repository.TopicRepository;
import com.ceos_19.vote.repository.UserRepository;
import com.ceos_19.vote.repository.VoteRepository;
import com.ceos_19.vote.repository.VotingOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ApiResponseDto<SuccessResponse> createVote(UserDetails userDetails, CreateVoteRequest createVoteRequest){

        System.out.println(userDetails);
        final User voter = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_USER));
        final Topic topic = topicRepository.findById(createVoteRequest.getTopicId())
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_TOPIC));
        final VotingOption votingOption = votingOptionRepository.findById(createVoteRequest.getVotingOptionId())
                .orElseThrow(() -> new RestApiException(ErrorType.NOT_FOUND_VOTINGOPTION));

        // 사용자의 Part 또는 Team을 기준으로 특정 Topic의 투표 가능 여부 확인
        switch (topic.getName()) {
            case "프론트엔드 파트장 투표":
                if (voter.getPart() != Part.FRONTEND) {
                    throw new RestApiException(ErrorType.INVALID_VOTINGOPTION_PART);
                }
                break;
            case "백엔드 파트장 투표":
                if (voter.getPart() != Part.BACKEND) {
                    throw new RestApiException(ErrorType.INVALID_VOTINGOPTION_PART);
                }
                break;
            case "데모데이 투표":
                String votingOptionName = votingOption.getName().toLowerCase();
                String voterTeam = voter.getTeam().toString().toLowerCase();
                if (votingOptionName.contains(voterTeam)) {
                    throw new RestApiException(ErrorType.INVALID_VOTINGOPTION_TEAM);
                }
                break;
        }

        // VotingOption의 topicId와 CreateVoteRequest의 topicId가 일치하는지 확인
        if (!votingOption.getTopic().getId().equals(createVoteRequest.getTopicId())) {
            throw new RestApiException(ErrorType.INVALID_VOTINGOPTION_TOPIC);
        }

        // 사용자가 이미 해당 Topic에 투표했는지 확인
        boolean alreadyVoted = voteRepository.existsByUserIdAndTopicId(voter.getId(), topic.getId());
        if (alreadyVoted) {
            throw new RestApiException(ErrorType.ALREADY_VOTED);
        }

        Vote vote = Vote.builder()
                .user(voter)
                .votingOption(votingOption)
                .build();

        votingOption.increaseVoteCount(vote);
        voteRepository.save(vote);

        return ResponseUtils.ok(SuccessResponse.of(HttpStatus.OK, "투표 성공"), ErrorResponse.builder().status(200).message("요청 성공").build());
    }

}
