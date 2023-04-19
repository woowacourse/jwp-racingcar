package racingcar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.service.RacingCarService;

@WebMvcTest(RacingCarWebController.class)
class RacingCarWebControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RacingCarService racingCarService;

    @DisplayName("이름과 시도 횟수를 보내면 결과로 우승자와 참가자(이름,포지션)을 반환한다")
    @Test
    void return_result_given_names_and_trial_count() throws Exception {
        RacingGameRequestDto racingGameRequestDto = new RacingGameRequestDto("마코,아코", 10);
        ResultResponseDto resultResponseDto = new ResultResponseDto("마코,아코",
            List.of(new PlayerDto("마코", 10), new PlayerDto("아코", 10)));
        when(racingCarService.play(any(RacingGameRequestDto.class))).thenReturn(resultResponseDto);

        mockMvc.perform(post("/plays")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(racingGameRequestDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.winners").value("마코,아코"))
            .andExpect(jsonPath("$.racingCars.[0].name").value( "마코"))
            .andExpect(jsonPath("$.racingCars.[0].position").value(10))
            .andExpect(jsonPath("$.racingCars.[1].name").value( "아코"))
            .andExpect(jsonPath("$.racingCars.[1].position").value(10))
            .andDo(print());
    }
}
