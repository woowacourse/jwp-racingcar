package racingcar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import racingcar.controller.dto.TrackRequest;
import racingcar.exception.CustomException;
import racingcar.mapper.TrackRequestMapper;
import racingcar.mapper.TrackResponseMapper;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.service.RacingService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RacingWebController.class)
class RacingWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingService racingService;

    @DisplayName("정상 요청이 오면 상태코드 OK 반환")
    @Test
    void postRequestSuccess() throws Exception {
        final TrackRequest trackRequest = TrackRequestMapper.of("gray,hoy,logan", "10");
        final Cars mockData = Cars.from(List.of(Car.of("gray", 5), Car.of("hoy", 5), Car.of("logan", 6)));

        when(racingService.play(any()))
                .thenReturn(TrackResponseMapper.from(mockData));

        performPost("/plays", trackRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners", is("logan")))
                .andExpect(jsonPath("$.racingCars", hasSize(3)));
    }

    @DisplayName("잘못된 이름 요청이 오면 상태코드 Bad Request 반환")
    @ParameterizedTest
    @CsvSource(value = {"gray.hoy.logan:자동차 이름은 문자와 숫자만 가능합니다.",
            "gra@,ho@,l@gan:자동차 이름은 문자와 숫자만 가능합니다.",
            "grayyy,hoy,logan:자동차 이름은 다섯 글자 이하여야 합니다.",
            "hoy,hoy,hoy:중복된 차 이름이 존재합니다.",
            ":참여자 이름을 입력해야합니다.",
            " , :비어있는 자동차 이름이 존재합니다."}, delimiter = ':')
    void postRequestFailWithWrongName(final String names, final String exceptionMessage) throws Exception {
        final TrackRequest trackRequest = TrackRequestMapper.of(names, "10");

        when(racingService.play(any()))
                .thenThrow(new CustomException(exceptionMessage));

        performPost("/plays", trackRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(exceptionMessage)));
    }

    @DisplayName("잘못된 시도 횟수 요청이 오면 상태코드 Bad Request 반환")
    @ParameterizedTest
    @CsvSource(value = {"two:시도 횟수는 숫자만 입력 가능합니다.",
            "0:시도 횟수는 1 이상 100 이하여야 합니다.",
            "101:시도 횟수는 1 이상 100 이하여야 합니다."}, delimiter = ':')
    void postRequestFailWithWrongTrialTimes(final String trialTime, final String exceptionMessage) throws Exception {
        final TrackRequest trackRequest = TrackRequestMapper.of("gray,hoy,logan", trialTime);

        when(racingService.play(any()))
                .thenThrow(new CustomException(exceptionMessage));

        performPost("/plays", trackRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(exceptionMessage)));
    }

    @DisplayName("게임 이력 조회 요청이 들어오면 상태코드 OK 반환")
    @Test
    void getRequestSuccess() throws Exception {
        mockMvc.perform(get("/plays"))
                .andExpect(status().isOk());
    }

    private ResultActions performPost(final String path, final TrackRequest trackRequest) throws Exception {
        return mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(makeBodyData(trackRequest)));
    }

    private String makeBodyData(final TrackRequest trackRequest) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(trackRequest);
    }
}
