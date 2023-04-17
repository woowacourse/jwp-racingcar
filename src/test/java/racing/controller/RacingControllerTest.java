package racing.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;
import racing.repository.RacingGameDao;
import racing.service.RacingGameService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RacingController.class)
class RacingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RacingGameService racingGameService;

    @MockBean
    RacingGameDao racingGameDao;

    @DisplayName("자동차 이름과 시도 횟수를 통해 게임을 진행할 수 있다.")
    @Test
    void playGameTest() throws Exception {
        when(racingGameService.saveGameByCount(anyInt())).thenReturn(1L);
        doNothing().when(racingGameService).playGame(anyLong(), any());
        RacingGameInfoRequest request = new RacingGameInfoRequest("bebe,royce", 6);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request)))
                .andExpect(status().isOk());
    }

    @DisplayName("잘못된 요청에 대해선 BAD REQUEST 상태를 응답한다.")
    @Test
    void invalidRequestTest() throws Exception {
        when(racingGameService.saveGameByCount(anyInt())).thenReturn(1L);
        doNothing().when(racingGameService).playGame(anyLong(), any());
        RacingGameInfoRequest request = new RacingGameInfoRequest("bebe,12312312royce", 6);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value("자동차 이름은 1 ~ 5자 사이 이어야 합니다."));
    }

    @DisplayName("진행된 게임의 이력을 조회 할 수 있다.")
    @Test
    void getAllResults() throws Exception {
        List<Cars> cars = playedCarsFixture();
        when(racingGameService.getAllResults()).thenReturn(cars);
        when(racingGameService.filterWinnersToCarNames(cars.get(0))).thenReturn("CarA");
        when(racingGameService.filterWinnersToCarNames(cars.get(1))).thenReturn("CarC,CarD");

        mockMvc.perform(get("/plays"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].winners").value("CarA"))
                .andExpect(jsonPath("$.[1].winners").value("CarC,CarD"));
    }

    private List<Cars> playedCarsFixture() {
        Cars firstGameCars = new Cars(List.of(
                new Car(new CarName("CarA"), 3),
                new Car(new CarName("CarB"), 1)
        ));

        Cars secondGameCars = new Cars(List.of(
                new Car(new CarName("CarC"), 2),
                new Car(new CarName("CarD"), 2)
        ));

        return List.of(firstGameCars, secondGameCars);
    }
}
