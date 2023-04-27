package racingcar.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.entity.CarResultEntity;
import racingcar.domain.entity.RacingGameResultEntity;
import racingcar.dto.request.RacingGameRequest;
import racingcar.repository.RacingCarRepository;

@SpringBootTest
@AutoConfigureMockMvc
class WebRacingCarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingCarRepository racingCarRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("요청한 값을 바탕으로 게임을 플레이하여 결과값을 반환한다.")
    @Test
    void play_success() throws Exception {
        RacingGameRequest racingGameRequest = new RacingGameRequest("현서,오리,서현", 10);
        String content = objectMapper.writeValueAsString(racingGameRequest);

        String winnersPath = "$.winners";

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(winnersPath).exists())
                .andExpect(jsonPath("racingCars", hasSize(3)))
                .andExpect(jsonPath("racingCars[0].name", is("현서")))
                .andExpect(jsonPath("racingCars[1].name", is("오리")))
                .andExpect(jsonPath("racingCars[2].name", is("서현")))
                .andExpect(jsonPath("racingCars[0].position").exists())
                .andExpect(jsonPath("racingCars[1].position").exists())
                .andExpect(jsonPath("racingCars[2].position").exists())
                .andDo(print());
    }

    @DisplayName("이름이 비어있다면 예외를 반환한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void play_false_by_blank_name(String wrongValue) throws Exception {
        RacingGameRequest racingGameRequest = new RacingGameRequest(wrongValue, 10);
        String content = objectMapper.writeValueAsString(racingGameRequest);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("이름을 입력해주세요.")))
                .andDo(print());
    }

    @DisplayName("자동차 이름이 5글자를 초과한다면 예외를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"666666,666666", "7777777,7777777"})
    void play_false_by_name_length(String wrongValue) throws Exception {
        RacingGameRequest racingGameRequest = new RacingGameRequest(wrongValue, 10);
        String content = objectMapper.writeValueAsString(racingGameRequest);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("자동차 이름은 1자 이상 5자 이하여야 합니다.")))
                .andDo(print());
    }


    @DisplayName("자동차가 2대 이하면 예외를 반환한다.")
    @Test
    void play_false_by_number_of_car() throws Exception {
        RacingGameRequest racingGameRequest = new RacingGameRequest("car", 10);
        String content = objectMapper.writeValueAsString(racingGameRequest);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("자동차 대수는 2 이상이어야 합니다.")))
                .andDo(print());
    }

    @DisplayName("중복된 자동차 이름이 존재한다면 예외를 반환한다.")
    @Test
    void play_false_by_duplicate_car_name() throws Exception {
        RacingGameRequest racingGameRequest = new RacingGameRequest("car,car", 10);
        String content = objectMapper.writeValueAsString(racingGameRequest);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("중복된 자동차 이름이 존재합니다.")))
                .andDo(print());
    }


    @DisplayName("카운트가 비어 있으면 예외를 반환한다.")
    @Test
    void play_false_by_blank_count() throws Exception {
        RacingGameRequest racingGameRequest = new RacingGameRequest("현서,오리", null);
        String content = objectMapper.writeValueAsString(racingGameRequest);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is("횟수를 입력해주세요.")))
                .andDo(print());
    }

    @DisplayName("저장된 모든 자동차 경주 결과를 반환한다.")
    @Test
    void find_all_result() throws Exception {
        List<CarResultEntity> carEntities = List.of(
                new CarResultEntity("현서", 10, true),
                new CarResultEntity("오리", 10, true),
                new CarResultEntity("은서", 1, false));
        List<RacingGameResultEntity> racingGameEntities = List.of(new RacingGameResultEntity(carEntities, 10));

        BDDMockito.given(racingCarRepository.findAll())
                .willReturn(racingGameEntities);

        mockMvc.perform(get("/plays"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("size()", is(1)))
                .andExpect(jsonPath("$[0].racingCars.size()", is(3)))
                .andExpect(jsonPath("$[0].winners", is("현서,오리")))
                .andDo(print());
    }
}
