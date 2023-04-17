package racingcar;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import racingcar.domain.car.Car;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.ResultDto;

@ExtendWith(MockitoExtension.class)
class RacingGameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RacingGameService racingGameService;

    @InjectMocks
    RacingGameController racingGameController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ResultDto mockResponse = ResultDto.ofCars(List.of(new Car("브리", 6),
                    new Car("로지", 5),
                    new Car("바론", 4)),
            List.of(new Car("브리", 6)));

    @DisplayName("게임 실행 후 결과를 조회한다.")
    @Test
    void testPlay() throws Exception {
        //given
        given(racingGameService.start(anyInt(), anyList())).willReturn(mockResponse);
        this.mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
        RacingGameRequest racingGameRequest = new RacingGameRequest("브리,로지,바론", 10);
        String requestAsString = objectMapper.writeValueAsString(racingGameRequest);
        String responseAsString = objectMapper.writeValueAsString(mockResponse);

        //when
        //then
        mockMvc.perform(post("/plays")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestAsString))
                .andExpect(status().isOk())
                .andExpect(content().json(responseAsString))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("전체 게임 실행 결과를 조회한다.")
    @Test
    void testViewAllGames() throws Exception {
        //given
        given(racingGameService.findAllGameHistories()).willReturn(List.of(mockResponse, mockResponse));
        this.mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
        String responseAsString = objectMapper.writeValueAsString(List.of(mockResponse, mockResponse));

        //when
        //then
        mockMvc.perform(get("/plays")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(responseAsString))
                .andDo(MockMvcResultHandlers.print());
    }

}
