package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.common.ErrorData;
import racingcar.common.exception.DuplicateResourceException;
import racingcar.common.exception.ResourceLengthException;
import racingcar.common.exception.ResourceMinRangeException;
import racingcar.dto.CarResponse;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RaceController.class)
@AutoConfigureMockMvc
class RaceControllerTest {

    @MockBean
    private RaceService raceService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("자동차 경주 성공")
    void plays_success() throws Exception {
        // given
        final RaceRequest raceRequest = new RaceRequest("두둠,져니", 10);
        final String request = objectMapper.writeValueAsString(raceRequest);
        final CarResponse carResponse = new CarResponse("두둠", 6);
        final RaceResponse raceResponse = RaceResponse.create(List.of("두둠"), List.of(carResponse));

        when(raceService.play(any()))
                .thenReturn(raceResponse);

        // when, then
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().is2xxSuccessful(),
                        jsonPath("$.winners").value("두둠"),
                        jsonPath("$.racingCars[0].name").value("두둠"),
                        jsonPath("$.racingCars[0].position").value(6));
    }

    @Test
    @DisplayName("자동차 경주 실패 - 자동차 이름에 빈 값이 들어온 경우")
    void plays_fail_car_name_blank_exception() throws Exception {
        final RaceRequest raceRequest = new RaceRequest("", 10);
        final String request = objectMapper.writeValueAsString(raceRequest);

        // when, then
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().is4xxClientError(),
                        jsonPath("$.message").value("자동차 이름은 비어있을 수 없습니다."));
    }

    @Test
    @DisplayName("자동차 경주 실패 - 경주 횟수가 0 이하의 값이 들어온 경우")
    void plays_fail_race_count_exception() throws Exception {
        // given
        final RaceRequest raceRequest = new RaceRequest("두둠,져니", 0);
        final String request = objectMapper.writeValueAsString(raceRequest);

        // when
        final ResourceMinRangeException resourceMinRangeException = new ResourceMinRangeException(new ErrorData<>(0));
        when(raceService.play(any()))
                .thenThrow(resourceMinRangeException);

        // then
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().is4xxClientError(),
                        jsonPath("$.message").value("0보다 큰 값을 입력해 주세요."));
    }

    @Test
    @DisplayName("자동차 경주 실패 - 자동차 이름에 중복된 이름이 들어왔을 경우")
    void plays_fail_car_name_duplicate_exception() throws Exception {
        // given
        final RaceRequest raceRequest = new RaceRequest("져니,져니", 2);
        final String request = objectMapper.writeValueAsString(raceRequest);

        // when
        when(raceService.play(any()))
                .thenThrow(DuplicateResourceException.class);

        // then
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().is4xxClientError(),
                        jsonPath("$.message").value("중복된 값을 입력할 수 없습니다."));
    }

    @Test
    @DisplayName("자동차 경주 실패 - 자동차 이름이 5글자를 초과하는 경우")
    void plays_fail_car_name_length_exceed_exception() throws Exception {
        // given
        final RaceRequest raceRequest = new RaceRequest("져니져니져니", 2);
        final String request = objectMapper.writeValueAsString(raceRequest);

        // when
        final ResourceLengthException exception = new ResourceLengthException(new ErrorData<>(5));
        when(raceService.play(any()))
                .thenThrow(exception);

        //  then
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().is4xxClientError(),
                        jsonPath("$.message").value("최대 5글자까지 입력할 수 있습니다."));
    }

    @Test
    @DisplayName("자동차 경주 결과 조회 성공")
    void raceResult_success() throws Exception {
        // given
        final List<CarResponse> carResponses = List.of(new CarResponse("두둠", 7),
                new CarResponse("져니", 10));
        final RaceResponse raceResponse = RaceResponse.create("져니", carResponses);
        final List<RaceResponse> raceResponses = List.of(raceResponse);

        // when
        when(raceService.getRaceResult())
                .thenReturn(raceResponses);

        // then
        mockMvc.perform(get("/plays"))
                .andExpectAll(status().is2xxSuccessful(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.[0].winners").value("져니"),
                        jsonPath("$.[0].racingCars[0].name").value("두둠"),
                        jsonPath("$.[0].racingCars[0].position").value(7),
                        jsonPath("$.[0].racingCars[1].name").value("져니"),
                        jsonPath("$.[0].racingCars[1].position").value(10));
    }
}
