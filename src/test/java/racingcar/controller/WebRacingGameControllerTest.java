package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.CarDto;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebRacingGameController.class)
public class WebRacingGameControllerTest {

    @MockBean
    RacingGameService racingGameService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @DisplayName("/plays로 post 요청시, 게임을 수행하고, 결과를 반환한다.")
    @Test
    void playGameSuccessTest() throws Exception {
        //given
        List<CarDto> carDtos = List.of(CarDto.of("브리", 7), CarDto.of("브라운", 8));
        GameRequestDto request = new GameRequestDto("브리,브라운", 8);
        GameResponseDto response = new GameResponseDto("브라운", carDtos);

        //when
        List<String> names = List.of(request.getNames().split(","));
        doReturn(response)
                .when(racingGameService)
                .play(names, request.getCount());

        //then
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value("브라운"))
                .andExpect(jsonPath("$.racingCars[*].name", containsInAnyOrder("브리", "브라운")))
                .andExpect(jsonPath("$.racingCars[*].position", containsInAnyOrder(7, 8)));
    }
}
