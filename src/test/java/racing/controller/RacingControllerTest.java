package racing.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.repository.RacingGameDao;
import racing.service.RacingGameService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RacingController.class)
class RacingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RacingGameService racingGameService;

    @MockBean
    RacingGameDao racingGameDao;

    @DisplayName("자동차 이름과 시도 횟수를 통해 게임을 진행할 수 있다.")
    @Test
    void playGameTest() throws Exception {
        when(racingGameService.saveGameByCount(anyInt())).thenReturn(1L);
        doNothing().when(racingGameService).playGame(anyLong(), any());
        RacingGameInfoRequest request = new RacingGameInfoRequest("bebe,royce", 6);

        mockMvc.perform(MockMvcRequestBuilders.post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("잘못된 요청에 대해선 BAD REQUEST 상태를 응답한다.")
    @Test
    void invalidRequestTest() throws Exception {
        when(racingGameService.saveGameByCount(anyInt())).thenReturn(1L);
        doNothing().when(racingGameService).playGame(anyLong(), any());
        RacingGameInfoRequest request = new RacingGameInfoRequest("bebe,12312312royce", 6);

        mockMvc.perform(MockMvcRequestBuilders.post("/plays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
