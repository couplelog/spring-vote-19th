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

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.votingOption.topic = :topic")
    long countVotesByTopic(@Param("topic") Topic topic);

    @Query("SELECT v.id AS votingOptionId, v.name AS votingOptionName, v.vote_count AS votingOptionCount " +
            "FROM VotingOption v " +
            "WHERE v.topic = :topic " +
            "AND v.vote_count = (SELECT MAX(vo.vote_count) FROM VotingOption vo WHERE vo.topic = :topic)")
    List<VotingOptionCountResponse> findTopByTopicOrderByVoteCountDesc(@Param("topic") Topic topic);

    @Query("SELECT vo.id as votingOptionId, vo.name as votingOptionName, COUNT(v.id) as votingOptionCount " +
            "FROM VotingOption vo LEFT JOIN Vote v ON vo.id = v.votingOption.id " +
            "WHERE vo.topic.id = :topicId " +
            "GROUP BY vo.id, vo.name")
    List<VotingOptionCountResponse> findVotingOptionSummariesByTopicId(@Param("topicId") Long topicId);
}
