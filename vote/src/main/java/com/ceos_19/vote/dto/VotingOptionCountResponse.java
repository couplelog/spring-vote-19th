package com.ceos_19.vote.dto;

public interface VotingOptionCountResponse {
    Long getVotingOptionId();
    String getVotingOptionName();
    int getVotingOptionCount();
}
