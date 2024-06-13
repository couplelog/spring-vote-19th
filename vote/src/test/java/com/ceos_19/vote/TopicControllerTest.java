package com.ceos_19.vote;

import com.ceos_19.vote.dto.TopicResponse;
import com.ceos_19.vote.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;

    /**
     * 모든 Topic 반환하는지 확인
     */
   /* @Test
    @WithMockUser(username = "user", roles = "USER")
    public void getAllTopics() throws Exception {        //topic, votingoption 양방향 참조 주의

        List<TopicResponse> topicResponses = new ArrayList<>();
        topicResponses.add(new TopicResponse(1L, "파트장 투표", 10, new ArrayList<>()));
        topicResponses.add(new TopicResponse(2L, "데모데이 투표", 20, new ArrayList<>()));

        given(topicService.getAllTopics()).willReturn(topicResponses);

        // when/then
        mockMvc.perform(get("/api/topics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("파트장 투표"))
                .andExpect(jsonPath("$[0].minimumVotesRequired").value(10))
                .andExpect(jsonPath("$[0].votingOptionDto").isArray())  // votingOptionDto가 배열인지 확인
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("데모데이 투표"))
                .andExpect(jsonPath("$[1].minimumVotesRequired").value(20))
                .andExpect(jsonPath("$[1].votingOptionDto").isArray()); // votingOptionDto가 배열인지 확인
    }*/

    /**
     * topic id별로 반환
     */
   /* @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getOneTopic() throws Exception {
        // given
        Long topicId = 1L;
        TopicResponse topicResponse = new TopicResponse(topicId, "파트장 투표", 10, new ArrayList<>());
        given(topicService.getTopicById(topicId)).willReturn(topicResponse);

        // when/then
        mockMvc.perform(get("/api/topics/{id}", topicId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(topicId))
                .andExpect(jsonPath("$.name").value("파트장 투표"))
                .andExpect(jsonPath("$.minimumVotesRequired").value(10));
    }
*/
}