package racing.web.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;
import racing.domain.RacingCarGame;
import racing.domain.TrialCount;
import racing.web.controller.dto.request.RacingGameInfoRequest;
import racing.web.service.RacingGameService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RacingController.class)
class RacingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RacingGameService racingGameService;

    @DisplayName("자동차 이름과 시도 횟수를 통해 게임을 진행할 수 있다.")
    @Test
    void playGameTest() throws Exception {
        when(racingGameService.playNewGame(anyInt(), any())).thenReturn(1L);
        RacingGameInfoRequest request = new RacingGameInfoRequest("bebe,royce", 6);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request)))
                .andExpect(status().isCreated());
    }

    @DisplayName("잘못된 요청에 대해선 BAD REQUEST 상태를 응답한다.")
    @Test
    void invalidRequestTest() throws Exception {
        when(racingGameService.playNewGame(anyInt(), any())).thenReturn(1L);
        RacingGameInfoRequest request = new RacingGameInfoRequest("bebe,12312312royce", 6);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("자동차 이름은 1 ~ 5자 사이 이어야 합니다."));
    }

    @DisplayName("진행된 게임의 이력을 조회 할 수 있다.")
    @Test
    void getAllResults() throws Exception {
        Cars cars = new Cars(List.of(
                new Car(new CarName("CarA"), 3),
                new Car(new CarName("CarB"), 1)
        ));
        RacingCarGame racingCarGame = new RacingCarGame(cars, new TrialCount(4));
        when(racingGameService.getResultById(anyLong())).thenReturn(racingCarGame);
        when(racingGameService.filterWinnersToCarNames(any())).thenReturn("CarA");

        mockMvc.perform(get("/result/{gameId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value("CarA"));
    }
}
