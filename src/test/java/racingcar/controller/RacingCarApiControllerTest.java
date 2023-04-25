package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.request.RacingCarRequestDto;
import racingcar.dto.response.RacingCarResponseDto;
import racingcar.dto.response.RacingGameResponseDto;
import racingcar.service.RacingCarService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

    @Test
    @DisplayName("게임을 시작하면 결과를 반환한다.")
    void play() throws Exception {
        RacingCarRequestDto racingCarRequestDto = new RacingCarRequestDto("다즐,루쿠", 5);
        final String content = objectMapper.writeValueAsString(racingCarRequestDto);

        RacingGameResponseDto racingGameResponseDto = new RacingGameResponseDto(
                "다즐", List.of(
                new RacingCarResponseDto("다즐", 5),
                new RacingCarResponseDto("루쿠", 4)));

        given(racingCarService.play(any())).willReturn(racingGameResponseDto);

        mockMvc.perform(post("/plays")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value("다즐"))
                .andExpect(jsonPath("$.racingCars.[0].name").value("다즐"))
                .andExpect(jsonPath("$.racingCars.[0].position").value(5))
                .andExpect(jsonPath("$.racingCars.[1].name").value("루쿠"))
                .andExpect(jsonPath("$.racingCars.[1].position").value(4));
    }

    @Test
    @DisplayName("저장된 게임 이력을 반환한다.")
    void findAllHistories() throws Exception {
        RacingGameResponseDto racingGameResponseDto1 = new RacingGameResponseDto(
                "다즐", List.of(
                new RacingCarResponseDto("다즐", 5),
                new RacingCarResponseDto("루쿠", 4)));

        RacingGameResponseDto racingGameResponseDto2 = new RacingGameResponseDto(
                "루쿠", List.of(
                new RacingCarResponseDto("다즐", 4),
                new RacingCarResponseDto("루쿠", 5)));

        given(racingCarService.findAllHistories()).willReturn(List.of(racingGameResponseDto1, racingGameResponseDto2));

        mockMvc.perform(get("/plays"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].winners").value("다즐"))
                .andExpect(jsonPath("$.[0].racingCars.[0].name").value("다즐"))
                .andExpect(jsonPath("$.[0].racingCars.[0].position").value(5))
                .andExpect(jsonPath("$.[0].racingCars.[1].name").value("루쿠"))
                .andExpect(jsonPath("$.[0].racingCars.[1].position").value(4))
                .andExpect(jsonPath("$.[1].winners").value("루쿠"))
                .andExpect(jsonPath("$.[1].racingCars.[0].name").value("다즐"))
                .andExpect(jsonPath("$.[1].racingCars.[0].position").value(4))
                .andExpect(jsonPath("$.[1].racingCars.[1].name").value("루쿠"))
                .andExpect(jsonPath("$.[1].racingCars.[1].position").value(5));
    }
}