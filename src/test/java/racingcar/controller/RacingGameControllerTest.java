package racingcar.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import racingcar.application.RacingGameService;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Winners;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest(RacingGameController.class)
@DisplayName("RacingGameControllerTest 은")
class RacingGameControllerTest {

    @MockBean
    private RacingGameService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 게임_진행_요청을_처리하고_결과를_반환한다() throws Exception {
        // given
        given(service.play(any(), eq(10)))
                .willReturn(1L);
        final Cars cars = new Cars(List.of(
                new Car("브리", 10),
                new Car("토미", 10),
                new Car("브라운", 10))
        );
        given(service.findResultById(1L))
                .willReturn(new RacingGameService.GameResult(cars.getCars(), new Winners(cars)));

        final MvcResult mvcResult = mockMvc.perform(post("/plays").content(
                                "{\"names\": \"브리,토미,브라운\", \"count\": 10}")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        final String contentAsString = mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        final String ex = "{\n" +
                "    \"winners\": \"브리,토미,브라운\",\n" +
                "    \"racingCars\": [\n" +
                "        {\n" +
                "            \"name\": \"브리\",\n" +
                "            \"position\": 10\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"토미\",\n" +
                "            \"position\": 10\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"브라운\",\n" +
                "            \"position\": 10\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        final String s = StringUtils.replaceWhitespaceCharacters(ex, "");
        assertThat(contentAsString)
                .isEqualTo(s);
    }
}
