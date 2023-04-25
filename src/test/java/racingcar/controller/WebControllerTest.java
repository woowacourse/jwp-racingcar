package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import racingcar.controller.dto.GameStartRequest;
import racingcar.controller.dto.CarStateResponse;
import racingcar.controller.dto.GameResultReponse;
import racingcar.service.RacingCarService;

import java.util.List;

@WebMvcTest(WebController.class)
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingCarService racingCarService;

    @DisplayName("/plays GET 요청 시, 전체 게임에 대하여 승자와 결과를 200 상태 코드로 반환한다.")
    @Test
    void searchAllHistories() throws Exception {
        List<GameResultReponse> gameResultResponses = List.of(
                new GameResultReponse("망고",
                        List.of(new CarStateResponse("망고", 7),
                                new CarStateResponse("루카", 5))),
                new GameResultReponse("소니",
                        List.of(new CarStateResponse("소니", 20),
                                new CarStateResponse("현구막", 15)))
        );
        //mocking
        Mockito.when(racingCarService.searchAllGame()).thenReturn(gameResultResponses);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/plays"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].winners").value("망고"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].racingCars[0].name").value("망고"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].racingCars[0].position").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].racingCars[1].name").value("루카"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].racingCars[1].position").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].winners").value("소니"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].racingCars[0].name").value("소니"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].racingCars[0].position").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].racingCars[1].name").value("현구막"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].racingCars[1].position").value(15));
    }

    @DisplayName("/plays POST 요청 시, 정상 작동 후, 승자와 결과를 200 상태 코드로 반환한다.")
    @Test
    void play() throws Exception {
        //given
        GameStartRequest gameStartRequest = new GameStartRequest("망고,루카", 10);
        GameResultReponse gameResultReponse = new GameResultReponse(
                "망고",
                List.of(new CarStateResponse("망고", 7),
                        new CarStateResponse("루카", 5)));
        //mocking
        Mockito.when(racingCarService.playGame(Mockito.any())).thenReturn(gameResultReponse);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(gameStartRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated()) // 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.winners").value("망고"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.racingCars[0].name").value("망고"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.racingCars[0].position").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.racingCars[1].name").value("루카"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.racingCars[1].position").value(5));
    }

}
