package racingcar.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.dto.PlayerDto;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class WebRacingGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RacingGameService racingGameService;

    @Test
    void 게임_진행() throws Exception {
        // given
        GameRequestDto gameRequest = new GameRequestDto(List.of("허브,비버"), 4);
        GameResponseDto gameResponse = new GameResponseDto(List.of("허브"), List.of(
                new PlayerDto("비버", 3),
                new PlayerDto("허브", 4)));

        given(racingGameService.play(any(GameRequestDto.class)))
                .willReturn(gameResponse);
        final String request = objectMapper.writeValueAsString(gameRequest);

        // expect
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("winners").exists())
                .andExpect(jsonPath("racingCars", hasSize(2)))
                .andDo(print());
    }

    @Test
    void 게임_진행_결과() throws Exception {
        List<GameResponseDto> gameResponseDtos = List.of(
                new GameResponseDto(
                        List.of("허브"),
                        List.of(
                                new PlayerDto("허브", 4),
                                new PlayerDto("비버", 0),
                                new PlayerDto("내꺼", 2)
                        ))
        );

        given(racingGameService.findAll())
                .willReturn(gameResponseDtos);

        mockMvc.perform(get("/plays")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].winners[0]").value("허브"))
                .andExpect(jsonPath("$[0].racingCars", hasSize(3)))
                .andExpect(jsonPath("$[0].racingCars[0].name").value("허브"))
                .andExpect(jsonPath("$[0].racingCars[0].position").value(4))
                .andDo(print());
    }
}
