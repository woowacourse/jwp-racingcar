package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingResultResponse;
import racingcar.service.RacingCarService;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        List<String> winners = List.of("raon");
        List<RacingCarDto> racingCars = List.of(new RacingCarDto("glen", 4), new RacingCarDto("raon", 6));

        // when
        doReturn(1)
                .when(racingCarService)
                .playRacingGame(anyList(), anyInt());

        doReturn(new RacingResultResponse(winners, racingCars))
                .when(racingCarService)
                .obtainRacingResult(anyInt());

        // then
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners[0]").value("raon"))
                .andExpect(jsonPath("$.racingCars[*].name", containsInAnyOrder("glen", "raon")))
                .andExpect(jsonPath("$.racingCars[*].position", containsInAnyOrder(4, 6)));
    }

    @ParameterizedTest
    @DisplayName("유효하지 않는 input이 들어오면 400이 반한되어야 한다.")
    @MethodSource("provideRacingCarRequest")
    void racingCarRequestValidation_fail(String names, int count) throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest(names, count);

        // when
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideRacingCarRequest() {
        return Stream.of(
                Arguments.of(null, 10),
                Arguments.of(" ", 10),
                Arguments.of("", 10),
                Arguments.of("glen,raon", 0)
        );
    }
}
