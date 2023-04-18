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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        doReturn(response)
                .when(racingGameService)
                .play(any(), any());

        //then
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value("브라운"))
                .andExpect(jsonPath("$.racingCars[*].name", containsInAnyOrder("브리", "브라운")))
                .andExpect(jsonPath("$.racingCars[*].position", containsInAnyOrder(7, 8)));
    }

    @DisplayName("/plays로 get 요청시, 저장된 모든 게임 결과를 반환한다.")
    @Test
    void findAllSuccessTest() throws Exception {
        //given
        List<CarDto> carDtos1 = List.of(CarDto.of("브리", 7), CarDto.of("브라운", 8));
        List<CarDto> carDtos2 = List.of(CarDto.of("쥬니", 3), CarDto.of("찰리", 10));

        List<GameResponseDto> response = List.of(
                new GameResponseDto("브라운", carDtos1),
                new GameResponseDto("찰리", carDtos2)
        );

        //when
        doReturn(response)
                .when(racingGameService)
                .findAllGameAndPlayerResults();

        //then
        mockMvc.perform(get("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].winners", containsInAnyOrder("브라운", "찰리")))
                .andExpect(jsonPath("$[*].racingCars[*].name",
                        containsInAnyOrder("브리", "브라운", "찰리", "쥬니")))
                .andExpect(jsonPath("$[*].racingCars[*].position", containsInAnyOrder(3, 7, 8, 10)));
    }
}
