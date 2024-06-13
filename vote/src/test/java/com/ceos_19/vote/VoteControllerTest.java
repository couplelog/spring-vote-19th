package com.ceos_19.vote;

import com.ceos_19.vote.common.enumSet.LoginType;
import com.ceos_19.vote.common.enumSet.UserRoleEnum;
import com.ceos_19.vote.common.security.UserDetailsImpl;
import com.ceos_19.vote.controller.VoteController;
import com.ceos_19.vote.domain.Part;
import com.ceos_19.vote.domain.Team;
import com.ceos_19.vote.domain.Topic;
import com.ceos_19.vote.domain.User;
import com.ceos_19.vote.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ceos_19.vote.dto.CreateVoteRequest;
import com.ceos_19.vote.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VoteController.class)
@AutoConfigureMockMvc
public class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    private User mockUser;
/*
    @BeforeEach
    void setUp() {
        // Mock User object
        mockUser = User.of(
                LoginType.NONE,
                "testuser",
                "testpw",
                UserRoleEnum.USER,
                Part.BACKEND,
                Team.커플로그,
                "test@gmail.com"
        );

        // Mock UserRepository behavior
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testCreateVoteWithMockUser() throws Exception {
        // Mocking voteService.createVote 메소드
        when(voteService.createVote(any(UserDetails.class), any(CreateVoteRequest.class))).thenReturn(1L);

        CreateVoteRequest request = new CreateVoteRequest(1L, 1L); // 예시 요청

        mockMvc.perform(MockMvcRequestBuilders.post("/api/votes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.voteId").value(1L)); // 응답이 voteId를 반환한다고 가정
    }

    @Test
    @WithUserDetails(value = "testuser", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void testCreateVoteWithUserDetails() throws Exception {
        // Mocking voteService.createVote 메소드
        when(voteService.createVote(any(UserDetails.class), any(CreateVoteRequest.class))).thenReturn(1L);

        CreateVoteRequest request = new CreateVoteRequest(1L, 1L); // 예시 요청

        mockMvc.perform(MockMvcRequestBuilders.post("/api/votes/")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // CSRF 토큰 추가
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.voteId").value(1L)); // 응답이 voteId를 반환한다고 가정
    }


 */

}