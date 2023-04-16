package racingcar;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
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
import racingcar.domain.RacingGameService;
import racingcar.domain.car.Car;
import racingcar.domain.race.RacingGame;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.RacingGameDto;
import racingcar.web.RacingGameController;
import racingcar.web.RacingGameResponse;

@ExtendWith(MockitoExtension.class)
class RacingGameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RacingGameService racingGameService;

    @InjectMocks
    RacingGameController racingGameController;

    @Nested
    class SuccessTest {
        private RacingGameResponse mockResponse;


        @BeforeEach
        void setUp() {
            RacingGame mockRacingGame = new RacingGame(List.of("브리", "로지", "바론"), (list) -> List.of(new Car("브리")));
            RacingGameDto racingGameDto = RacingGameDto.from(mockRacingGame);
            given(racingGameService.start(anyInt(), anyList())).willReturn(racingGameDto);

            mockResponse = RacingGameResponse.from(racingGameDto);
            MockitoAnnotations.openMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
        }

        @Test
        void testPlay() throws Exception {
            //given
            ObjectMapper objectMapper = new ObjectMapper();
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
}
