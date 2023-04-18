package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.CarStatusResponseDto;
import racingcar.dto.GameHistoriesResponseDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.StartGameRequestDto;
import racingcar.service.RacingCarService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RacingGameWebController.class)
public class RacingGameWebControllerTest {

    @MockBean
    RacingCarService racingCarService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("게임 이력 조회를 한다.")
    void returns_game_histories() throws Exception {
        // given
        List<GameHistoriesResponseDto> expect = List.of(
                GameHistoriesResponseDto.toDto("jay,turtle",
                        List.of(
                                CarStatusResponseDto.toDto(new Car("jay", 0)),
                                CarStatusResponseDto.toDto(new Car("turtle", 0))
                        )
                )
        );

        given(racingCarService.findAllGameHistories()).willReturn(expect);

        // when & then
        mockMvc.perform(get("/plays"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].winners").value("jay,turtle"))
                .andExpect(jsonPath("$[0].racingCars[0].name").value("jay"))
                .andExpect(jsonPath("$[0].racingCars[1].name").value("turtle"))
                .andDo(print());
    }

    @Test
    @DisplayName("게임 시작 요청을 처리하면 상태코드 201을 반환한다.")
    void returns_created_when_processing_request() throws Exception {
        // given
        StartGameRequestDto req = new StartGameRequestDto("jay,turtle", 3);
        GameResultResponseDto expected = GameResultResponseDto.toDto(Cars.from(List.of("jay", "turtle")));

        given(racingCarService.startRace(any(Cars.class), any(TryCount.class))).willReturn(expected);

        // when & then
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.winners[0]").value("jay"))
                .andExpect(jsonPath("$.winners[1]").value("turtle"))
                .andExpect(jsonPath("$.racingCars[0].name").value("jay"))
                .andExpect(jsonPath("$.racingCars[1].name").value("turtle"))
                .andDo(print());
    }
}
