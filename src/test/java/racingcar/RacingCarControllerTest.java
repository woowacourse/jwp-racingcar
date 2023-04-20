package racingcar;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;
import racingcar.service.RandomMoveStrategy;


@WebMvcTest(RacingCarController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DisplayName("RacingCarController 클래스")
class RacingCarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RacingCarService racingCarService;

    @Mock
    RandomMoveStrategy randomMoveStrategy;

    @Test
    void play_메서드가_적절한_형식을_반환한다() throws Exception {
        final PlayRequest playRequest = new PlayRequest("브리,토미,브라운", 1);
        final String request = objectMapper.writeValueAsString(playRequest);
        Car bri = new Car("브리");
        Car tomi = new Car("토미");
        Car brown = new Car("브라운");
        List<Car> cars = List.of(bri);
        final PlayResponse playResponse = PlayResponse.of(
                RacingCarWinnerDto.of(cars),
                List.of(
                        RacingCarStatusDto.from(bri),
                        RacingCarStatusDto.from(tomi),
                        RacingCarStatusDto.from(brown)
                )
        );

        // given
        given(randomMoveStrategy.isMovable()).willReturn(true);
        given(racingCarService.play(any(PlayRequest.class)))
                .willReturn(playResponse);

        // then
        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners[0]").value("브리"))
                .andExpect(jsonPath("racingCars", hasSize(3)))
                .andExpect(jsonPath("$.racingCars[0].name").value("브리"))
                .andExpect(jsonPath("$.racingCars[0].position").value(0));
    }
}
