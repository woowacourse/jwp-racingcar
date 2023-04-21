package racingcar.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.api.dto.request.CarGameRequest;
import racingcar.api.dto.response.CarResponse;
import racingcar.api.dto.response.GameResponse;
import racingcar.domain.Car;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebRacingCarController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class WebRacingCarControllerTest {

    @MockBean
    RacingGameService racingGameService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 자동차_경주_게임_플레이() throws Exception {
        CarGameRequest request = new CarGameRequest("glen,juno", 5);
        GameResponse response = GameResponse.of("glen", List.of(CarResponse.from(new Car("glen"))));

        Mockito.when(racingGameService.play(any(CarGameRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value("glen"))
                .andExpect(jsonPath("$.racingCars[0].position").value(0))
                .andDo(print());
    }

    @Test
    void 자동차_경주_게임_정보_가져오기() throws Exception {
        CarGameRequest request = new CarGameRequest("glen,juno", 5);
        GameResponse response = GameResponse.of("glen", List.of(CarResponse.from(new Car("glen"))));

        Mockito.when(racingGameService.findAllGame())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].winners").value("glen"))
                .andExpect(jsonPath("$[0].racingCars[0].position").value(0))
                .andDo(print());
    }
}
