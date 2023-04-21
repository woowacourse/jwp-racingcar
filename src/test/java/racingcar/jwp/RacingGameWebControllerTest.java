package racingcar.jwp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import racingcar.controller.RacingGameWebController;
import racingcar.dto.request.GameRequestDto;
import racingcar.service.GameFindService;
import racingcar.service.GamePlayService;

@WebMvcTest(RacingGameWebController.class)
public class RacingGameWebControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    GameFindService gameFindService;
    @MockBean
    GamePlayService gamePlayService;

    @Test
    @DisplayName("게임 실행 테스트")
    void postTest() throws Exception {
        final GameRequestDto gameRequestDto = new GameRequestDto("ditoo,leo", 10);

        mvc.perform(MockMvcRequestBuilders.post("/plays")
                        .content(objectMapper.writeValueAsString(gameRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/plays"));
    }
}
