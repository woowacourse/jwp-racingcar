package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.dto.request.GameRequest;
import racingcar.dto.GameResultDto;
import racingcar.service.RacingGameService;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebRacingGameController.class)
public class WebRacingGameControllerTest {

    @MockBean
    RacingGameService racingGameService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @DisplayName("/plays로 post 요청시, 게임을 수행하고, 결과를 반환한다.")
    @Test
    void playGameSuccessTest() throws Exception {
        //given
        GameRequest request = new GameRequest("브리,브라운", 10);
        GameResultDto response = new GameResultDto("브라운",
                List.of(new Car(new Name("브리"), 7), new Car(new Name("브라운"), 8)));

        //when
        doReturn(response)
                .when(racingGameService)
                .saveGamePlay(request.getNames(), request.getCount());

        //then
        mockMvc.perform(post("/plays")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winners").value("브라운"))
                .andExpect(jsonPath("$.racingCars[*].name", containsInAnyOrder("브리", "브라운")))
                .andExpect(jsonPath("$.racingCars[*].position", containsInAnyOrder(7, 8)));
    }
}
