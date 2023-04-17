package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import racingcar.domain.Car;
import racingcar.domain.Winner;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultResponseDto;
import racingcar.service.RacingGameService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("post 요청 시 상태코드 200 반환 및 반환된 json 형식 데이터 확인")
@WebMvcTest
class RacingGameControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RacingGameService racingGameService;

    @Test
    void play() throws Exception {
        // given
        PlayRequestDto playRequestDto = new PlayRequestDto("jena,odo", 3);

        List<Car> racingCars = new ArrayList<>();
        racingCars.add(new Car("jena", 9));
        racingCars.add(new Car("odo", 7));
        PlayResultResponseDto expected = new PlayResultResponseDto(new Winner(List.of("jena")), racingCars);

        // when
        Mockito.when(racingGameService.run("jena,odo", 3)).thenReturn(expected);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(playRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value("jena"))
                .andExpect(jsonPath("$.racingCars[0].name").value("jena"))
                .andExpect(jsonPath("$.racingCars[0].distance").value(9))
                .andExpect(jsonPath("$.racingCars[1].name").value("odo"))
                .andExpect(jsonPath("$.racingCars[1].distance").value(7));
    }
}
