package racingcar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.domain.Car;
import racingcar.service.WebRaceService;

@WebMvcTest(WebRacingCarController.class)
public class WebRacingCarControllerSliceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private WebRaceService webRaceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("자동차 이름, 시도 횟수가 담긴 요청 바디를 받아서 게임 결과를 응답 바디에 반환한다.")
    void registerRaceResult() throws Exception {
        GameInfoRequest gameInfoRequest = new GameInfoRequest("성하,토니", 5);
        String request = objectMapper.writeValueAsString(gameInfoRequest);

        RaceResultResponse response = RaceResultResponse.create("토니",
                List.of(new Car("성하", 3),
                        new Car("토니", 4)));

        when(webRaceService.saveRaceResult(any(GameInfoRequest.class))).thenReturn(1);
        when(webRaceService.createRaceResult(1)).thenReturn(response);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.winners").value("토니"))
                .andExpect(jsonPath("$.racingCars[0].name").value("성하"))
                .andExpect(jsonPath("$.racingCars[0].position").value(3))
                .andExpect(jsonPath("$.racingCars[1].name").value("토니"))
                .andExpect(jsonPath("$.racingCars[1].position").value(4));
    }

    @Test
    @DisplayName("모든 게임 조회 결과를 응답 바디에 반환한다.")
    void searchAllRaceResult() throws Exception {
        List<Car> cars1 =
                List.of(new Car("토니", 5),
                        new Car("성하", 3));
        List<Car> cars2 =
                List.of(new Car("우르", 4),
                        new Car("빙봉", 2));

        when(webRaceService.searchAllRaceResult())
                .thenReturn(List.of(RaceResultResponse.create("토니", cars1),
                        RaceResultResponse.create("우르", cars2)));

        mockMvc.perform(get("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].winners").value("토니"))
                .andExpect(jsonPath("$[0].racingCars[0].name").value("토니"))
                .andExpect(jsonPath("$[0].racingCars[0].position").value(5))
                .andExpect(jsonPath("$[0].racingCars[1].name").value("성하"))
                .andExpect(jsonPath("$[0].racingCars[1].position").value(3))
                .andExpect(jsonPath("$[1].racingCars[0].name").value("우르"))
                .andExpect(jsonPath("$[1].racingCars[0].position").value(4))
                .andExpect(jsonPath("$[1].racingCars[1].name").value("빙봉"))
                .andExpect(jsonPath("$[1].racingCars[1].position").value(2));
    }
}
