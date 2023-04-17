package racingcar.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.CarName;
import racingcar.domain.CarPosition;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;

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
        final CarStatusDto carStatusDto = new CarStatusDto(CarName.create("두둠"),
            new CarPosition(6));
        final RaceResponse raceResponse = RaceResponse.create(List.of("두둠"), List.of(carStatusDto));

        when(raceService.play(any()))
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
        when(raceService.play(any()))
            .thenThrow(IllegalArgumentException.class);

        // then
        mockMvc.perform(post("/plays")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("")
    public void testFindAllRace() throws Exception {
        //given
        final RaceEntity raceEntity = new RaceEntity(1L, 1, "test");
        final List<CarEntity> carEntities = List.of(new CarEntity(1L, "test", 1));
        final List<RaceResponse> raceResponses = List.of(RaceResponse.of(raceEntity, carEntities));

        //when
        when(raceService.findAllRace())
            .thenReturn(raceResponses);

        //then
        mockMvc.perform(get("/plays")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$[0].winners").value(raceEntity.getWinners()))
            .andExpect(jsonPath("$[0].racingCars[0].name").value(carEntities.get(0).getName()))
            .andExpect(
                jsonPath("$[0].racingCars[0].position").value(carEntities.get(0).getPosition()));
    }
}
