package racingcar.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.dto.response.CarResponse;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebRacingCarController.class)
class WebRacingCarControllerTest {

    @MockBean
    RacingGameService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 컨트롤러_테스트() throws Exception {
        CarGameRequest request = new CarGameRequest("glen,juno", 5);
        CarGameResponse response = CarGameResponse.of("glen", List.of(new CarResponse(new Car("glen"))));

        Mockito.when(service.play(any(CarGameRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value("glen"))
                .andExpect(jsonPath("$.racingCars[0].position").value(0));
    }
}
