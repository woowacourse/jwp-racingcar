package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Winners;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.WinnerDto;
import racingcar.service.RacingGameService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        //given
        final String winnersPath = "$.winners";
        final String carsPath = "$..position";
        final List<Car> cars = List.of(new Car("브리", 10), new Car("토미", 9));
        final List<WinnerDto> winnerDtos = WinnerDto.createWinnerDtos(new Winners(new Cars(cars)));
        final List<CarDto> carDtos = cars.stream().map(CarDto::fromCar).collect(Collectors.toList());
        final GameResultDto gameResult = new GameResultDto(carDtos, winnerDtos);
        final PlayRequestDto request = new PlayRequestDto("브리,토미", 10);
        final String requestJson = objectMapper.writeValueAsString(request);

        //when
        given(racingGameService.play(anyList(), anyInt()))
                .willReturn(gameResult);

        //then
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

        final List<Car> cars1 = List.of(new Car("a", 3), new Car("b", 5));
        final List<CarDto> carDtos1 = cars1.stream()
                .map(CarDto::fromCar)
                .collect(Collectors.toList());
        final List<WinnerDto> winnerDtos1 = WinnerDto.createWinnerDtos(new Winners(new Cars(cars1)));
        gameResults.add(new GameResultDto(carDtos1, winnerDtos1));

        final List<Car> cars2 = List.of(new Car("c", 4), new Car("d", 6));
        final List<CarDto> carDtos2 = cars2.stream()
                .map(CarDto::fromCar)
                .collect(Collectors.toList());
        final List<WinnerDto> winnerDtos2 = WinnerDto.createWinnerDtos(new Winners(new Cars(cars2)));
        gameResults.add(new GameResultDto(carDtos2, winnerDtos2));


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
