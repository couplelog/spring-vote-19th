package com.ceos_19.vote.dto;

import com.ceos_19.vote.domain.Part;
import com.ceos_19.vote.domain.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String name;
    private String username;
    private String password;
    private String email;
    private Part part;
    private Team team;
    private Boolean role;


}
