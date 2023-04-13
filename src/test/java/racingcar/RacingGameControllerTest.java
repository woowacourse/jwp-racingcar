package racingcar;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import racingcar.dao.CarRecord;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.ResultDto;

@ExtendWith(MockitoExtension.class)
class RacingGameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RacingGameService racingGameService;

    @InjectMocks
    RacingGameController racingGameController;

    private ResultDto mockResponse;


    @BeforeEach
    void setUp() {
        mockResponse = new ResultDto(List.of(new CarRecord("브리", 6, true),
                new CarRecord("로지", 5, false),
                new CarRecord("바론", 4, false)));

        given(racingGameService.start(anyInt(), anyList())).willReturn(mockResponse);
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(racingGameController).build();
    }

    @Test
    void testPlay() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
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
}