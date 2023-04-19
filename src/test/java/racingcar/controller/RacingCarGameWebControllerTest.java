package racingcar.controller;

import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.game.dto.GameRequestDTO;

@DisplayName("Http Method")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarGameWebControllerTest {
    
    @LocalServerPort
    int port;
    
    @BeforeEach
    void setUp() {
        RestAssured.port = this.port;
    }
    
    @DisplayName("Http Method - POST")
    @Test
    void playRacingCarGameTest() {
        final GameRequestDTO gameRequestDTO = new GameRequestDTO("echo,io", 10);
        
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("racingCars.size()", is(2));
    }
    
}