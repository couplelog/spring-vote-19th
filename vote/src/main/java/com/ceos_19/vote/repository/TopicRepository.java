package com.ceos_19.vote.repository;

import com.ceos_19.vote.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
