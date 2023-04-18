package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.domain.Position;
import racingcar.dto.request.RacingCarRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.service.RacingCarService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RacingCarApiController.class)
class RacingCarApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RacingCarService racingCarService;

    @DisplayName("게임을 진행한다.")
    @Test
    void play() throws Exception {
        final String names = "다즐,오즈";
        final int count = 3;
        final RacingCarRequest racingCarRequest = new RacingCarRequest(names, count);
        final String content = objectMapper.writeValueAsString(racingCarRequest);
        final RacingGameResponse racingGameResponse = RacingGameResponse.of(
                List.of("오즈"),
                List.of(new Car("다즐", new Position(2)), new Car("오즈", new Position(3)))
        );

        given(racingCarService.play(names, count)).willReturn(racingGameResponse);

        mockMvc.perform(post("/plays")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.winners").value("오즈"))
                .andExpect(jsonPath("$.racingCars", hasSize(2)))
                .andExpect(jsonPath("$.racingCars[0].name").value("다즐"))
                .andExpect(jsonPath("$.racingCars[0].position").value(2))
                .andExpect(jsonPath("$.racingCars[1].name").value("오즈"))
                .andExpect(jsonPath("$.racingCars[1].position").value(3));
    }

    @DisplayName("게임 이력을 조회한다.")
    @Test
    void findPlays() throws Exception {
        final RacingGameResponse firstRacingGameResponse = RacingGameResponse.of(
                List.of("오즈"),
                List.of(new Car("다즐", new Position(2)), new Car("오즈", new Position(3)))
        );
        final RacingGameResponse secondRacingGameResponse = RacingGameResponse.of(
                List.of("다즐"),
                List.of(new Car("루쿠", new Position(2)), new Car("다즐", new Position(3)))
        );

        given(racingCarService.findPlays()).willReturn(List.of(firstRacingGameResponse, secondRacingGameResponse));

        mockMvc.perform(get("/plays"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].winners").value("오즈"))
                .andExpect(jsonPath("$[0].racingCars[0].name").value("다즐"))
                .andExpect(jsonPath("$[0].racingCars[0].position").value(2))
                .andExpect(jsonPath("$[0].racingCars[1].name").value("오즈"))
                .andExpect(jsonPath("$[0].racingCars[1].position").value(3))
                .andExpect(jsonPath("$[1].winners").value("다즐"))
                .andExpect(jsonPath("$[1].racingCars[0].name").value("루쿠"))
                .andExpect(jsonPath("$[1].racingCars[0].position").value(2))
                .andExpect(jsonPath("$[1].racingCars[1].name").value("다즐"))
                .andExpect(jsonPath("$[1].racingCars[1].position").value(3));
    }
}
