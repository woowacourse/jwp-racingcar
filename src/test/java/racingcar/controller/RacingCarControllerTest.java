package racingcar.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.service.RacingCarService;

@WebMvcTest
class RacingCarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingCarService racingCarService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 컨트롤러_테스트() throws Exception {
        PlayResponse response = PlayResponse.of(
                "car2",
                List.of(new Car("car1"), new Car("car2")));

        given(racingCarService.play(any(PlayRequest.class)))
                .willReturn(response);

        PlayRequest playRequest = new PlayRequest("car1,car2", 4);
        String request = objectMapper.writeValueAsString(playRequest);

        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("winners").value("car2"))
                .andExpect(jsonPath("racingCars", hasSize(2)))
                .andDo(print());
    }
}
