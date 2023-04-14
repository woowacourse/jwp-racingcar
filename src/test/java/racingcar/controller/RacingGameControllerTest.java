package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Winners;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.service.RacingGameServiceImpl;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RacingGameController.class)
@DisplayName("RacingGameControllerTest 은")
class RacingGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RacingGameServiceImpl racingGameService;

    @Test
    void playTest() throws Exception {
        final String winnersPath = "$.winners";
        final String carsPath = "$..position";
        List<Car> cars = List.of(new Car("브리", 10), new Car("토미", 9));
        GameResultDto gameResult = new GameResultDto(cars, new Winners(new Cars(cars)));
        given(racingGameService.play(anyList(), anyInt()))
                .willReturn(gameResult);

        PlayRequestDto request = new PlayRequestDto("브리,토미", 10);
        String requestJson = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/plays")
                        .content(requestJson)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(winnersPath, is("브리")))
                .andExpect(jsonPath(carsPath, containsInAnyOrder(9, 10)))
                .andDo(print());
    }
}
