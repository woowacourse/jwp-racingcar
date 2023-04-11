package racing.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.response.RacingGameResultResponse;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingControllerTest {

    @LocalServerPort
    private int randomServerPort;

    @DisplayName("PostMapping Test")
    @Test
    void start() {
        // given
        RacingGameInfoRequest request = new RacingGameInfoRequest("bebe,royce", 6);
        String baseUrl = "http://localhost:" + randomServerPort;

        // when
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<RacingGameResultResponse> response =
                restTemplate.postForEntity(baseUrl + "/plays", request, RacingGameResultResponse.class);

        // then
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
