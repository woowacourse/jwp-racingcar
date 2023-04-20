package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import racingcar.domain.CarName;
import racingcar.domain.CarPosition;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceResultEntity;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;

@SpringBootTest
@AutoConfigureMockMvc
class WebRaceControllerTest {

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
        final RaceRequest raceRequest = new RaceRequest("test1,test2", 10);
        final String request = objectMapper.writeValueAsString(raceRequest);
        final CarStatusDto expectedCarStatusDto = CarStatusDto.of(CarName.create("test1"),
            new CarPosition(6));
        final RaceResponse expectedResponse = RaceResponse.of(List.of("test1"),
            List.of(expectedCarStatusDto));

        when(raceService.play(any()))
            .thenReturn(expectedResponse);

        // when, then
        final MvcResult result = mockMvc.perform(post("/plays")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        final RaceResponse response = jsonToObject(result, RaceResponse.class);

        final List<CarStatusDto> carStatusDtos = response.getRacingCars();
        for (final CarStatusDto carStatusDto : carStatusDtos) {
            assertThat(carStatusDto.getName())
                .isEqualTo(expectedCarStatusDto.getName());
            assertThat(carStatusDto.getPosition())
                .isEqualTo(expectedCarStatusDto.getPosition());
        }
        assertThat(response.getWinners()).isEqualTo(expectedResponse.getWinners());
    }

    @Test
    @DisplayName("자동차 경주 실패 - 비즈니스 로직 예외")
    void plays_fail_business_exception() throws Exception {
        // given
        final RaceRequest raceRequest = new RaceRequest("test1,test2", 10);
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
    @DisplayName("자동차 경주 기록 조회")
    public void testFindAllRace() throws Exception {
        //given
        final List<CarEntity> carEntities = List.of(new CarEntity(1L, "test", 1));
        final RaceResultEntity raceResultEntity = new RaceResultEntity(1L, 1, "test", carEntities);
        final List<RaceResponse> expectedResponse = List.of(RaceResponse.of(raceResultEntity));

        //when
        when(raceService.findAllRace())
            .thenReturn(expectedResponse);

        //then
        final MvcResult result = mockMvc.perform(get("/plays")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        final List<RaceResponse> responses = Arrays.asList(
            jsonToObject(result, RaceResponse[].class));

        for (int i = 0; i < responses.size(); i++) {
            final RaceResponse raceResponse = responses.get(i);
            assertThat(raceResponse.getWinners())
                .isEqualTo(expectedResponse.get(i).getWinners());

            final List<CarStatusDto> carStatusDtos = raceResponse.getRacingCars();
            final List<CarStatusDto> expectedCarStatusDtos = expectedResponse.get(i)
                .getRacingCars();

            for (int j = 0; j < carStatusDtos.size(); j++) {
                assertThat(carStatusDtos.get(j).getName())
                    .isEqualTo(expectedCarStatusDtos.get(j).getName());
                assertThat(carStatusDtos.get(j).getPosition())
                    .isEqualTo(expectedCarStatusDtos.get(j).getPosition());
            }
        }
    }

    private <T> T jsonToObject(final MvcResult result, final Class<T> valueType)
        throws UnsupportedEncodingException, JsonProcessingException {
        final String responseString = result.getResponse()
            .getContentAsString();
        return objectMapper.readValue(responseString, valueType);
    }
}
