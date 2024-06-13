package com.ceos_19.vote.repository;

import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.domain.VotingOption;
import com.ceos_19.vote.dto.VotingOptionCountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VotingOptionRepository extends JpaRepository<VotingOption, Long> {

    List<VotingOption> findVotingOptionByTopic(Topic topic);

    @Query("SELECT v FROM VotingOption v WHERE v.topic = :topic ORDER BY v.vote_count DESC")
    Optional<VotingOption> findTopByTopicOrderByVoteCountDesc(@Param("topic") Topic topic);

    @Query("SELECT vo.id as votingOptionId, vo.name as votingOptionName, COUNT(v.id) as votingOptionCount " +
                "FROM Vote v JOIN v.votingOption vo JOIN vo.topic t " +
                "WHERE t.id = :topicId " +
                "GROUP BY vo.id, vo.name")
    List<VotingOptionCountResponse> findVotingOptionSummariesByTopicId(@Param("topicId") Long topicId);
}
