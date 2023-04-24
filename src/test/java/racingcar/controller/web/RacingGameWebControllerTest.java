package racingcar.controller.web;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;

@WebMvcTest(RacingGameWebController.class)
@DisplayName("RacingGameWebController 테스트")
@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingGameWebControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private RacingGameService racingGameService;
    @InjectMocks
    private RacingGameWebController racingGameWebController;

    @Test
    void playGameByRequest() throws Exception {
        RacingGameRequestDto requestDto = new RacingGameRequestDto("가가,나나", 1);
        RacingGameResponseDto responseDto = new RacingGameResponseDto(List.of("가가"),
                List.of(new CarStatusDto("가가",1), new CarStatusDto("나나",0)));

        Mockito.when(racingGameService.run(any(RacingGameRequestDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/plays")
                        .content(objectMapper.writeValueAsBytes(requestDto))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners[0]").value("가가"))
                .andExpect(jsonPath("racingCars", hasSize(1)))
                .andExpect(jsonPath("$.racingCars[0].name").value("가가"))
                .andExpect(jsonPath("$.racingCars[0].position").value(1))
                .andDo(print());

    }

    @Test
    void findAllResultOfGame() throws Exception  {
        List<RacingGameResponseDto> result = List.of(
                new RacingGameResponseDto(List.of("가가","나나"),
                        List.of(new CarStatusDto("가가",2),
                                new CarStatusDto("나나",2),
                                new CarStatusDto("다다",1))
                ),
                new RacingGameResponseDto(List.of("라라"),
                        List.of(new CarStatusDto("라라",2),
                                new CarStatusDto("마마",1),
                                new CarStatusDto("바바",1))
                )
        );

        Mockito.when(racingGameService.findAllGameResult())
                .thenReturn(result);

        mockMvc.perform(get("/plays")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners[0]").value("가가"))
                .andExpect(jsonPath("racingCars", hasSize(1)))
                .andExpect(jsonPath("$.racingCars[0].name").value("가가"))
                .andExpect(jsonPath("$.racingCars[0].position").value(1))
                .andDo(print());

    }
}
