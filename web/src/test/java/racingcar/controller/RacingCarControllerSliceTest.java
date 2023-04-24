package racingcar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.domain.GameId;
import racingcar.domain.RacingGame;
import racingcar.domain.Winners;
import racingcar.dto.GameResponse;
import racingcar.dto.PlayGameRequest;
import racingcar.service.RaceAddService;
import racingcar.service.RaceFindService;

@SuppressWarnings("NonAsciiCharacters")
@WebMvcTest
class RacingCarControllerSliceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RaceAddService raceAddService;

    @MockBean
    private RaceFindService raceFindService;

    @Test
    void 게임_진행하는_테스트() throws Exception {
        //given
        final PlayGameRequest playGameRequest = new PlayGameRequest(List.of("브리", "토미", "브라운"), 10);
        final String request = objectMapper.writeValueAsString(playGameRequest);
        final GameResponse gameResponse = new GameResponse("브리,토미,브라운", List.of(
                new racingcar.dto.CarResponse("브리", 5),
                new racingcar.dto.CarResponse("토미", 5),
                new racingcar.dto.CarResponse("브라운", 5)
        ));
        final String response = objectMapper.writeValueAsString(gameResponse);
        given(raceAddService.addRace(any(List.class), any(int.class))).willReturn(new RacingGame(1, List.of(
                new racingcar.domain.Car("브리", 5),
                new racingcar.domain.Car("토미", 5),
                new racingcar.domain.Car("브라운", 5)
        ), 10));
        given(raceFindService.findWinners(any(RacingGame.class))).willReturn(
                new Winners(new GameId(1), List.of(
                        new Car("브리", 5),
                        new Car("토미", 5),
                        new Car("브라운", 5
                        ))));

        //expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }

    @Test
    void 게임_조회_테스트() throws Exception {
        //given
        final List<GameResponse> gameResponse = List.of(new GameResponse("브리,토미,브라운", List.of(
                new racingcar.dto.CarResponse("브리", 5),
                new racingcar.dto.CarResponse("토미", 5),
                new racingcar.dto.CarResponse("브라운", 5)
        )));
        final String response = objectMapper.writeValueAsString(gameResponse);
        given(raceFindService.findAllRace()).willReturn(List.of(
                new RacingGame(1, List.of(
                        new racingcar.domain.Car("브리", 5),
                        new racingcar.domain.Car("토미", 5),
                        new racingcar.domain.Car("브라운", 5)
                ), 10)
        ));
        given(raceFindService.findWinners(any(RacingGame.class))).willReturn(
                new Winners(new GameId(1), List.of(
                        new Car("브리", 5),
                        new Car("토미", 5),
                        new Car("브라운", 5
                        ))));

        //expect
        mockMvc.perform(get("/plays"))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    void 숫자가_0이하면_400_에러_테스트() throws Exception {
        //given
        final PlayGameRequest playGameRequest = new PlayGameRequest(List.of("브리", "토미", "브라운"), 0);
        final String request = objectMapper.writeValueAsString(playGameRequest);

        //expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 빈_이름이_들어오면_400_에러_테스트() throws Exception {
        //given
        final PlayGameRequest playGameRequest = new PlayGameRequest(List.of("브리", "", "브라운"), 10);
        final String request = objectMapper.writeValueAsString(playGameRequest);

        //expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().isBadRequest());
    }
}
