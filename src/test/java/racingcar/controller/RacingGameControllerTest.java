package racingcar.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameResponse;
import racingcar.service.RacingGameService;

@WebMvcTest(RacingGameController.class)
public class RacingGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RacingGameService racingGameService;

    @Test
    @DisplayName("자동차 경주를 실행하고 결과를 반환한다.")
    public void plays() throws Exception {
        RacingGameRequest request = new RacingGameRequest("현구막,박스터", 10);
        RacingGameResponse expectedResponse = new RacingGameResponse(
                List.of("현구막"),
                List.of(new CarDto("현구막", 10, true), new CarDto("박스터", 7, false))
        );
        String requestString = objectMapper.writeValueAsString(request);
        when(racingGameService.play(any())).thenReturn(expectedResponse);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestString))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners", is("현구막")))
                .andExpect(jsonPath("$.racingCars", hasSize(2)));
    }
}
