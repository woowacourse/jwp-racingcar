package racingcar.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.NumberGenerator;
import racingcar.dto.GamePlayRequestDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RacingGameIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GameDao gameDao;

    @Autowired
    private CarDao carDao;

    @MockBean
    private NumberGenerator numberGenerator;

    @Test
    void 게임을_진행한다() throws Exception {
        // given
        final GamePlayRequestDto gamePlayRequestDto = new GamePlayRequestDto(List.of("비버", "허브"), 3);
        final String request = objectMapper.writeValueAsString(gamePlayRequestDto);
        given(numberGenerator.generate()).willReturn(7, 3, 3, 4, 5, 2);

        // expect
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.winners", Matchers.contains("비버")))
                .andExpect(jsonPath("$.racingCars", hasSize(2)))
                .andExpect(jsonPath("$.racingCars[0].name", is("비버")))
                .andExpect(jsonPath("$.racingCars[0].position").value(2))
                .andExpect(jsonPath("$.racingCars[1].name", is("허브")))
                .andExpect(jsonPath("$.racingCars[1].position").value(1))
                .andDo(print());
        assertThat(carDao.findAll()).hasSize(2);
    }

    @Test
    void 게임_결과를_반환한다() throws Exception {
        // given
        final Integer gameId = gameDao.saveAndGetId(new GameEntity(9999)).get();
        carDao.saveAll(List.of(
                new CarEntity("비버", 5, true, gameId),
                new CarEntity("허브", 4, false, gameId)
        ));

        // expect
        mockMvc.perform(get("/plays")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].winners", Matchers.contains("비버")))
                .andExpect(jsonPath("$[0].racingCars", hasSize(2)))
                .andExpect(jsonPath("$[0].racingCars[0].name", is("비버")))
                .andExpect(jsonPath("$[0].racingCars[0].position").value(5))
                .andExpect(jsonPath("$[0].racingCars[1].name", is("허브")))
                .andExpect(jsonPath("$[0].racingCars[1].position").value(4))
                .andDo(print());
    }
}
