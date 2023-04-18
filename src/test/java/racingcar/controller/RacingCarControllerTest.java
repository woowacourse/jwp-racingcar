package racingcar.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.dto.PlaysRequest;
import racingcar.dto.PlaysResponse;
import racingcar.service.RacingCarService;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

@WebMvcTest
class RacingCarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingCarService racingCarService;

    @Autowired
    private ObjectMapper objectMapper;

    private NumberGenerator numberGenerator;

    @BeforeEach
    void setUp() {
        numberGenerator = new RandomNumberGenerator();
    }

    @Test
    void 컨트롤러_테스트() throws Exception {
        PlaysResponse response = PlaysResponse.of(
                "car2",
                List.of(new Car("car1", numberGenerator), new Car("car2", numberGenerator)));

        given(racingCarService.play(any(PlaysRequest.class)))
                .willReturn(response);

        PlaysRequest playsRequest = new PlaysRequest("car1,car2", 4);
        String request = objectMapper.writeValueAsString(playsRequest);

        mockMvc.perform(post("/plays")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("winners").value("car2"))
                .andExpect(jsonPath("racingCars", hasSize(2)))
                .andDo(print());
    }
}
