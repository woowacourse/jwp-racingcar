package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RaceControllerTest {

    @MockBean
    private RaceService raceService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("자동차 경주 성공")
    void plays_success() throws Exception {
        // given
        final RaceRequest raceRequest = new RaceRequest("두둠,져니", 10);
        final String request = objectMapper.writeValueAsString(raceRequest);
        final CarStatusDto carStatusDto = new CarStatusDto("두둠", 6);
        final RaceResponse raceResponse = RaceResponse.create(List.of("두둠"), List.of(carStatusDto));

        when(raceService.getRaceResults(any()))
                .thenReturn(raceResponse);

        // when, then
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.winners").value("두둠"))
                .andExpect(jsonPath("$.racingCars[0].name").value("두둠"))
                .andExpect(jsonPath("$.racingCars[0].position").value(6));
    }

    @Test
    @DisplayName("자동차 경주 실패 - 비즈니스 로직 예외")
    void plays_fail_business_exception() throws Exception {
        // given
        final RaceRequest raceRequest = new RaceRequest("두둠,져니", 10);
        final String request = objectMapper.writeValueAsString(raceRequest);

        // when
        when(raceService.getRaceResults(any()))
                .thenThrow(IllegalArgumentException.class);

        // then
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
