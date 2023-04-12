package racingcar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.StartGameRequestDto;
import racingcar.service.RacingCarService;

@ExtendWith(MockitoExtension.class)
public class RacingGameControllerTest {

    @InjectMocks
    RacingGameController racingGameController;

    @Mock
    RacingCarService racingCarService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
    }

    @Test
    @DisplayName("게임 시작 요청을 처리하면 상태코드 200을 반환한다..")
    void returns_ok_when_processing_request() throws Exception {
        // given
        StartGameRequestDto req = new StartGameRequestDto("jay,turtle", 3);

        // when & then
        mockMvc.perform(
                post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))
        ).andExpect(status().isOk());
    }
}
