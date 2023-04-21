package racingcar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.MvcResult;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingResultResponse;
import racingcar.service.RacingCarService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebRacingCarController.class)
class WebRacingCarControllerTest {

    @MockBean
    RacingCarService racingCarService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    private List<RacingCarDto> racingCars;
    private List<String> winners;

    @BeforeEach
    void beforeEach() {
        winners = List.of("raon");
        racingCars = List.of(
                new RacingCarDto("glen", 4),
                new RacingCarDto("raon", 6)
        );
    }

    @Test
    @DisplayName("/plays로 POST 요청 시 HTTP 200 코드와 게임의 결과가 반환되어야 한다.")
    void racingGamePlay_success() throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest("glen,raon", 10);

        doReturn(new RacingResultResponse(winners, racingCars))
                .when(racingCarService)
                .playRacingGame(anyList(), anyInt());

        // expect
        MvcResult mvcResult = mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andReturn();

        RacingResultResponse racingResultResponse =
                objectMapper.readValue(contentToString(mvcResult), RacingResultResponse.class);

        assertThat(racingResultResponse.getRacingCars())
                .usingRecursiveComparison()
                .isEqualTo(racingCars);
        assertThat(racingResultResponse.getWinners())
                .usingRecursiveComparison()
                .isEqualTo(winners);
    }

    @ParameterizedTest
    @DisplayName("유효하지 않는 input이 들어오면 400이 반환되어야 한다.")
    @MethodSource("provideRacingCarRequest")
    void racingCarRequestValidation_fail(String names, int count) throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest(names, count);

        // when
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
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

    @Test
    @DisplayName("/plays로 GET 요청 시 HTTP 200 코드와 저장된 게임의 모든 결과가 반환되어야 한다.")
    void searchGameHistory_success() throws Exception {
        // given
        List<RacingResultResponse> racingResultResponses = new ArrayList<>();
        racingResultResponses.add(new RacingResultResponse(winners, racingCars));

        when(racingCarService.searchGameHistory())
                .thenReturn(racingResultResponses);

        // expect
        MvcResult mvcResult = mockMvc.perform(get("/plays")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<RacingResultResponse> responses =
                objectMapper.readValue(contentToString(mvcResult), new TypeReference<List<RacingResultResponse>>() {});

        assertThat(responses).usingRecursiveComparison().isEqualTo(racingResultResponses);
    }

    private String toJson(RacingCarRequest request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }

    private String contentToString(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }
}
