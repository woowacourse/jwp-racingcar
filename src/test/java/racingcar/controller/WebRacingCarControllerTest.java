package racingcar.controller;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doReturn;
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
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.service.RacingCarService;

@WebMvcTest(WebRacingCarController.class)
class WebRacingCarControllerTest {

    @MockBean
    RacingCarService racingCarService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("/plays로 POST 요청 시 HTTP 200 코드와 게임의 결과가 반환되어야 한다.")
    void racingGamePlay_success() throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest("glen,raon", 10);

        // when
        doReturn(1)
                .when(racingCarService)
                .playRacingGame(anyList(), anyInt());

        doReturn(List.of("raon"))
                .when(racingCarService)
                .findWinners(anyInt());

        doReturn(List.of(new RacingCarDto("glen", 4), new RacingCarDto("raon", 6)))
                .when(racingCarService)
                .findRacingCars(anyInt());

        // then
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners[0]").value("raon"))
                .andExpect(jsonPath("$.racingCars[*].name", containsInAnyOrder("glen", "raon")))
                .andExpect(jsonPath("$.racingCars[*].position", containsInAnyOrder(4, 6)));
    }
}
