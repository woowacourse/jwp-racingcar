package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import racingcar.service.dto.CarStatusResponse;
import racingcar.service.dto.GameInfoRequest;
import racingcar.service.dto.RaceResultResponse;
import racingcar.service.RaceResultService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("RacingCarWebController Unit Test")
class RacingCarWebControllerTest {

    private MockMvc mockMvc;

    private RacingCarWebController racingCarWebController;

    private RaceResultService raceResultService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        raceResultService = mock(RaceResultService.class);
        racingCarWebController = new RacingCarWebController(raceResultService);

        mockMvc = MockMvcBuilders.standaloneSetup(racingCarWebController)
                                 .build();
    }

    @Test
    @DisplayName("registerRaceResult() : 게임 정보를 통해 새로운 게임을 생성할 수 있다.")
    void test_registerRaceResult() throws Exception {
        //given
        final GameInfoRequest gameInfoRequest = new GameInfoRequest("a,b,c,d", 4);
        final List<CarStatusResponse> carStatusResponses = List.of(new CarStatusResponse("a", 4),
                                                                   new CarStatusResponse("b", 2),
                                                                   new CarStatusResponse("c", 4),
                                                                   new CarStatusResponse("d", 3));

        final String bodyData = objectMapper.writeValueAsString(gameInfoRequest);

        //when
        final RaceResultResponse raceResultResponse = new RaceResultResponse("a,c", carStatusResponses);

        when(raceResultService.createRaceResult(any()))
                .thenReturn(raceResultResponse);

        //then

        final String resultData = objectMapper.writeValueAsString(raceResultResponse);

        mockMvc.perform(post("/plays")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bodyData))
               .andExpect(status().isCreated())
               .andExpect(content().json(resultData));
    }

    @Test
    @DisplayName("showRaceResult() : 모든 게임 정보를 불러올 수 있다.")
    void test_showRaceResult() throws Exception {
        //given
        final List<CarStatusResponse> carStatusResponses1 = List.of(new CarStatusResponse("a", 4),
                                                                    new CarStatusResponse("b", 2),
                                                                    new CarStatusResponse("c", 4),
                                                                    new CarStatusResponse("d", 3));

        final List<CarStatusResponse> carStatusResponses2 = List.of(new CarStatusResponse("e", 4),
                                                                    new CarStatusResponse("f", 2),
                                                                    new CarStatusResponse("g", 4),
                                                                    new CarStatusResponse("h", 3));

        final List<RaceResultResponse> raceResultResponses =
                List.of(new RaceResultResponse("a,c", carStatusResponses1),
                        new RaceResultResponse("e,g", carStatusResponses2));

        //when
        when(raceResultService.searchRaceResult())
                .thenReturn(raceResultResponses);

        //then

        final String resultData = objectMapper.writeValueAsString(raceResultResponses);

        mockMvc.perform(get("/plays")
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(resultData));
    }
}
