package racingcar.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import racingcar.service.RacingGameService;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.RacingGame;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameDto;
import racingcar.controller.web.RacingGameController;
import racingcar.controller.web.RacingGameRequest;
import racingcar.controller.web.RacingGameResponse;

@ExtendWith(MockitoExtension.class)
class RacingGameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RacingGameService racingGameService;

    @InjectMocks
    RacingGameController racingGameController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Nested
    class SuccessTest {
        private RacingGameResponse mockResponse;

        @BeforeEach
        void setUp() {
            RacingGame mockRacingGame = RacingGame.from(List.of("브리", "로지", "바론"));
            RacingGameDto racingGameDto = RacingGameDto.from(mockRacingGame);
            given(racingGameService.play(anyInt(), anyList())).willReturn(racingGameDto);

            mockResponse = RacingGameResponse.from(racingGameDto);
            MockitoAnnotations.openMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
        }

        @Test
        void testPlay() throws Exception {
            //given

            RacingGameRequest racingGameRequest = new RacingGameRequest("브리,로지,바론", 10);
            String requestAsString = objectMapper.writeValueAsString(racingGameRequest);
            String responseAsString = objectMapper.writeValueAsString(mockResponse);

            //when
            //then
            mockMvc.perform(post("/plays")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(requestAsString))
                    .andExpect(status().isOk())
                    .andExpect(content().json(responseAsString))
                    .andDo(MockMvcResultHandlers.print());


        }
    }

    @Nested
    class FailTest {
        @BeforeEach
        void setUp() {
            mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
        }

        @DisplayName("시도 횟수가 음수일 경우 예외가 발생한다.")
        @Test
        void test404ResponseWithMessage() throws Exception {

            //given
            ObjectMapper objectMapper = new ObjectMapper();
            RacingGameRequest racingGameRequest = new RacingGameRequest("브리,로지,바론", -1);
            String requestAsString = objectMapper.writeValueAsString(racingGameRequest);

            //when
            //then
            mockMvc.perform(post("/plays")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestAsString))
                    .andExpect(status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());
        }

    }

    @Nested
    class GetPlaysSuccessTest {
        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
        }
        @DisplayName("게임 이력을 올바른 형태로 반환한다.")
        @Test
        void testReadHistory() throws Exception {
            RacingGameDto mockRacingGameDto = mock(RacingGameDto.class);
            given(mockRacingGameDto.getWinnerNames()).willReturn(List.of("오리", "엔초"));
            given(mockRacingGameDto.getRacingCars()).willReturn(List.of(
                    RacingCarDto.from(new RacingCar("오리", 10)),
                    RacingCarDto.from(new RacingCar("엔초", 10)),
                    RacingCarDto.from(new RacingCar("로지", 7))
            ));
            given(racingGameService.readGameHistory()).willReturn(List.of(mockRacingGameDto));

            //when
            //then

            List<RacingGameResponse> expected = List.of(RacingGameResponse.from(mockRacingGameDto));
            mockMvc.perform(get("/plays")
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(expected)))
                    .andDo(MockMvcResultHandlers.print());
        }
    }


}
