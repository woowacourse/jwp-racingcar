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
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.dto.PlayerDto;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
        GameResponse gameResponse = new GameResponse(List.of("허브"), List.of(
                new PlayerDto("비버", 3),
                new PlayerDto("허브", 4)));

        given(racingGameService.play(any(GameRequest.class)))
                .willReturn(gameResponse);
        final String request = objectMapper.writeValueAsString(gameResponse);

        // expect
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("winners").exists())
                .andExpect(jsonPath("racingCars", hasSize(2)))
                .andDo(print());
    }
}
