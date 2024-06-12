package com.ceos_19.vote;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.ceos_19.vote.domain.VotingOption;
import com.ceos_19.vote.dto.TopicResponse;
import com.ceos_19.vote.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;

    @Test
    public void getAllTopics_ReturnsOkAndTopics() throws Exception {
        VotingOption votingOption1 = new VotingOption(1L, "리사");

        TopicResponse topic1 = new TopicResponse(1L, "파트장 투표", 10, );
        TopicResponse topic2 = new TopicResponse(2L, "데모데이 투표", 20);
        List<TopicResponse> topics = Arrays.asList(topic1, topic2);

        when(topicService.getAllTopics()).thenReturn(topics);

        mockMvc.perform(get("/topics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(topic1.getId()))
                .andExpect(jsonPath("$[0].name").value(topic1.getName()))
                .andExpect(jsonPath("$[0].description").value(topic1.getDescription()))
                .andExpect(jsonPath("$[1].id").value(topic2.getId()))
                .andExpect(jsonPath("$[1].name").value(topic2.getName()))
                .andExpect(jsonPath("$[1].description").value(topic2.getDescription()));
    }
}
