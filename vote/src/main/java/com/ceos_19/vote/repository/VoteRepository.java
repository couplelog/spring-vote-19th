package com.ceos_19.vote.repository;

import com.ceos_19.vote.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT COUNT(v) > 0 FROM Vote v WHERE v.user.id = :userId AND v.votingOption.topic.id = :topicId")
    boolean existsByUserIdAndTopicId(@Param("userId") Long userId, @Param("topicId") Long topicId);
}
