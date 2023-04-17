package racingcar.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.CarDto;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.service.RacingGameService;

@WebMvcTest
@DisplayName("WebRacingGameController 클래스")
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
    void play_메서드는_게임을_진행한다() throws Exception {
        // given
        final GamePlayRequestDto gameRequest = new GamePlayRequestDto(List.of("비버", "허브"), 1);
        final String request = objectMapper.writeValueAsString(gameRequest);
        final GamePlayResponseDto gameResponse = new GamePlayResponseDto(
                List.of("비버"),
                List.of(new CarDto("비버", 1), new CarDto("허브", 0))
        );
        given(racingGameService.play(any(GamePlayRequestDto.class)))
                .willReturn(gameResponse);

        // expect
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners[0]").value("비버"))
                .andExpect(jsonPath("racingCars", hasSize(2)))
                .andExpect(jsonPath("$.racingCars[0].name").value("비버"))
                .andExpect(jsonPath("$.racingCars[0].position").value(1))
                .andDo(print());
    }
}
