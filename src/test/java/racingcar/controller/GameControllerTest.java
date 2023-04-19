package racingcar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import racingcar.controller.dto.PlayRequest;
import racingcar.exception.IllegalGameArgumentException;
import racingcar.service.GameService;
import racingcar.service.dto.CarDto;
import racingcar.service.dto.ResultDto;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    @Test
    @DisplayName("post(/plays) 테스트")
    void post_plays() throws Exception {
        final List<CarDto> cars = List.of(
                new CarDto("땡칠", 1),
                new CarDto("필립", 2)
        );
        given(gameService.playWith(any(), anyInt()))
                .willReturn(new ResultDto(List.of("필립"), cars));

        String request = objectMapper.writeValueAsString(new PlayRequest("땡칠,필립", 2));
        String expectedResponse = objectMapper.writeValueAsString(
                new ResultDto(List.of("필립"), cars)
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/plays")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse))
                .andDo(print());
    }

    @Test
    @DisplayName("post(/plays) GameException bad request 테스트")
    void post_plays_GameException_bad_request() throws Exception {
        given(gameService.playWith(any(), anyInt()))
                .willThrow(new IllegalGameArgumentException("test"));

        String request = objectMapper.writeValueAsString(new PlayRequest("aa", 1));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/plays")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("test"))
                .andDo(print());
    }
}
