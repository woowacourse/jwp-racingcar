package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingResultResponse;
import racingcar.repository.MySqlRacingCarRepository;

@SpringBootTest
@AutoConfigureMockMvc
class WebIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MySqlRacingCarRepository mySqlRacingCarRepository;

    @Test
    @DisplayName("/plays로 POST 요청을 보내면 데이터베이스에 게임의 결과가 저장되어야 한다.")
    void playRacingCarGame_success() throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest("glen,raon", 5);

        // when
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        List<RacingResultResponse> result = mySqlRacingCarRepository.findAllGameResults();
        assertThat(result)
                .hasSize(1);
        assertThat(result.get(0).getRacingCars())
                .hasSize(2);
    }

    @Test
    @DisplayName("/plays로 GET 요청을 보내면 게임의 기록을 조회할 수 있어야 한다.")
    void inquiryRacingCarGame_success() throws Exception {
        // given
        mySqlRacingCarRepository.saveGame(1);
        mySqlRacingCarRepository.saveCars(1, List.of(new Car("glen"), new Car("raon")));
        mySqlRacingCarRepository.saveWinner(1, List.of("glen"));

        // expect
        mockMvc.perform(get("/plays")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].winners").exists())
                .andExpect(jsonPath("$[0].racingCars").exists());
    }

    @Test
    @DisplayName("/plays로 POST 요청 시 이름이 중복되면 400 상태코드와 적절한 상태메시지가 반환되어야 한다.")
    void playRacingCarGame_duplicateName() throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest("glen,glen", 5);

        // expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[ERROR]: 중복된 차 이름이 있습니다."));
    }

    @Test
    @DisplayName("/plays로 POST 요청 시 이름이 영문 혹은 한글이 아니면 400 상태코드와 적절한 상태메시지가 반환되어야 한다.")
    void playRacingCarGame_invalidCarName() throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest("글렌ㅋ", 5);

        // expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[ERROR]: 자동차 이름은 영문 혹은 한글만 가능합니다."));
    }

    @Test
    @DisplayName("/plays로 POST 요청 시 이름이 공백이면 400 상태코드와 적절한 상태메시지가 반환되어야 한다.")
    void playRacingCarGame_blankCarName() throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest(" ", 5);

        // expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[ERROR]: 자동차 이름은 공백을 입력할 수 없습니다."));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    @DisplayName("/plays로 POST 요청 시 시도 횟수가 1보다 작으면 400 상태코드와 적절한 상태메시지가 반환되어야 한다.")
    void playRacingCarGame_underTryCount(int tryCount) throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest("글렌", tryCount);

        // expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[ERROR]: 시도 횟수는 0보다 커야 합니다."));
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 100})
    @DisplayName("/plays로 POST 요청 시 시도 횟수가 25보다 크면 400 상태코드와 적절한 상태메시지가 반환되어야 한다.")
    void playRacingCarGame_overTryCount(int tryCount) throws Exception {
        // given
        RacingCarRequest request = new RacingCarRequest("글렌", tryCount);

        // expect
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[ERROR]: 시도 횟수는 25이하여야 합니다."));
    }
}
