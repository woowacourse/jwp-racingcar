package racingcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.global.exception.ExceptionStatus;
import racingcar.service.RaceResultService;
import racingcar.service.dto.GameInfoRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RacingCarWebController.class)
class RacingCarWebControllerIntegrationTest {

    @MockBean
    private RaceResultService raceResultService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("데이터베이스 연결 실패 테스트")
    void test_registerRaceResult_connectionFailure() throws Exception {
        //given
        final GameInfoRequest gameInfoRequest = new GameInfoRequest("a,b,c,d", 4);
        final String requestData = objectMapper.writeValueAsString(gameInfoRequest);

        //when
        when(raceResultService.createRaceResult(any()))
                .thenThrow(DataAccessResourceFailureException.class);

        //then
        mockMvc.perform(post("/plays")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestData))
               .andExpect(status().isInternalServerError())
               .andExpect(jsonPath("$.statusCode").value(
                                  ExceptionStatus.TEMPORARY_DATABASE_CONNECTION_EXCEPTION.getStatus()
                          )
               )
               .andExpect(jsonPath("$.message").value(
                       ExceptionStatus.TEMPORARY_DATABASE_CONNECTION_EXCEPTION.getMessage())
               );
    }
}
