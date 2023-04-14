package racingcar.controller;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.response.CarResponse;
import racingcar.dto.response.RacingGameResultResponse;
import racingcar.service.RacingCarService;

@WebMvcTest(WebRacingCarController.class)
class WebRacingCarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingCarService racingCarService;

    @DisplayName("정상적으로 결과를 반환한다.")
    @Test
    void test1() throws Exception {
        final String content = "{\"names\":\"포비,네오,브리\", \"count\":10}";

        final RacingGameResultResponse then = new RacingGameResultResponse(
                List.of(new CarResponse("포비", 6),
                        new CarResponse("네오", 10),
                        new CarResponse("브리", 10)),
                "네오,브리");

        given(racingCarService.play(any()))
                .willReturn(then);

        final String winnersPath = "$.winners";

        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath(winnersPath, Matchers.is("네오,브리")))
                .andExpect(jsonPath("racingCars", Matchers.hasSize(3)))
                .andDo(print());
    }
}
