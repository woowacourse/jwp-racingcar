package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Winner;
import racingcar.domain.Winners;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.service.RacingGameService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RacingGameController.class)
class RacingGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RacingGameService racingGameService;

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

    @Test
    void findResult() throws Exception {
        final String namePath = "$..name";
        final String deepPositionPath = "$..position";
        final String firstGameCarsPositionPath = "$.[1].racingCars[*].position";

        //given
        List<GameResultDto> gameResults = new ArrayList<>();
        List<Winner> winners = List.of(new Winner("a"), new Winner("b"));
        gameResults.add(new GameResultDto(List.of(new Car("a", 3), new Car("b", 5)), new Winners(winners)));
        gameResults.add(new GameResultDto(List.of(new Car("c", 4), new Car("d", 6)), new Winners(winners)));

        //when
        given(racingGameService.findAllResult())
                .willReturn(gameResults);

        //then
        mockMvc.perform(get("/plays"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(namePath, containsInAnyOrder("a", "b", "c", "d")))
                .andExpect(jsonPath(deepPositionPath, containsInAnyOrder(3, 4, 5, 6)))
                .andExpect(jsonPath(firstGameCarsPositionPath, containsInAnyOrder(4, 6)))
                .andDo(print());
    }
}
