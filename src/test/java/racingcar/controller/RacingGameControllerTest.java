package racingcar.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import racingcar.domain.numbergenerator.NumberGenerator;

import javax.annotation.Priority;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(value = RacingGameControllerTest.MockNumberGenerator.class)
@DisplayName("RacingGameControllerTest 은")
class RacingGameControllerTest {

    @Autowired
    RacingGameController racingGameController;

    @Priority(1)
    @TestComponent
    public static class MockNumberGenerator implements NumberGenerator {

        @Override
        public int generateNumber() {
            return 9;
        }
    }

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
    }

    @Test
    void playTest() throws Exception {
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
