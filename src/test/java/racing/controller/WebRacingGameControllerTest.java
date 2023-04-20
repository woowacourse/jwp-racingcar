package racing.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import racing.dto.CarDto;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;
import racing.service.WebRacingGameService;
import racing.util.RandomNumberGenerator;

@WebMvcTest(WebRacingGameController.class)
class WebRacingGameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    WebRacingGameService webRacingGameService;

    @DisplayName("/plays URL 로 POST 요청시 승자의 이름들과 참가 차량을 JSON 으로 반환한다.")
    @Test
    void play() throws Exception {
        //given
        final GameResultDto gameResult = new GameResultDto(
            List.of(
                new CarDto("스플릿", 4, true),
                new CarDto("아벨", 4, true)
            )
        );
        when(webRacingGameService.playGame(any(GameInputDto.class), any(RandomNumberGenerator.class))).thenReturn(
            gameResult);

        //when //then
        mockMvc.perform(post("/plays")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new GameInputDto("스플릿,아벨", "4"))))
            .andExpect(jsonPath("$.winners").value("스플릿,아벨"))
            .andExpect(jsonPath("racingCars", hasSize(2)))
            .andExpect(jsonPath("$.racingCars[0].name").value("스플릿"))
            .andExpect(jsonPath("$.racingCars[0].position").value(4))
            .andExpect(jsonPath("$.racingCars[1].name").value("아벨"))
            .andExpect(jsonPath("$.racingCars[1].position").value(4))
            .andDo(print());
    }

    @Test
    void showAllGames() throws Exception {
        //given
        final List<GameResultDto> games = List.of(
            new GameResultDto(
                List.of(
                    new CarDto("스플릿", 4, true),
                    new CarDto("아벨", 4, true)
                )
            ),
            new GameResultDto(
                List.of(
                    new CarDto("아벨", 4, true),
                    new CarDto("스플릿", 4, true)
                )
            )
        );
        when(webRacingGameService.showGames()).thenReturn(games);

        //when then
        mockMvc.perform(MockMvcRequestBuilders.get("/plays")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(games)))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].winners").value("스플릿,아벨"))
            .andExpect(jsonPath("$[0].racingCars[0].name").value("스플릿"))
            .andExpect(jsonPath("$[0].racingCars[1].name").value("아벨"))
            .andExpect(jsonPath("$[1].winners").value("아벨,스플릿"));
    }
}
