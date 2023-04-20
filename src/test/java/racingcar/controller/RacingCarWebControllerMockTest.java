package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.dto.RacingGameRequestDto;
import racingcar.service.RacingCarService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class RacingCarWebControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingCarService racingCarService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
    }

    @DisplayName("post요청으로 /plays를 받으면 해당 RequestBody를 인자로 racingCarService의 play를 한 번 호출한다")
    @Test
    public void plays_post_calls_racingCarService() throws Exception {
        final RacingGameRequestDto requestDto = new RacingGameRequestDto(List.of("아코", "마코"), 5);

        String json = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/plays")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(racingCarService, times(1)).play(refEq(requestDto));
    }

    @DisplayName("get요청으로 /plays 받으면 racingCarService의 getAllRacingGameHistory를 한 번 호출한다")
    @Test
    public void plays_get_calls_racingCarService() throws Exception {
        mockMvc.perform(get("/plays"))
                        .andExpect(status().isOk());

        verify(racingCarService, times(1)).getAllRacingGameHistory();
    }
}
