package racingcar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.StartGameRequestDto;
import racingcar.service.RacingCarService;

@WebMvcTest(RacingGameController.class)
public class RacingGameControllerTest {

    @MockBean
    RacingCarService racingCarService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("게임 시작 요청을 처리하면 상태코드 200을 반환한다.")
    void returns_ok_when_processing_request() throws Exception {
        // given
        StartGameRequestDto req = new StartGameRequestDto("jay,turtle", 3);

        // when & then
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }
}
