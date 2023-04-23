package racingcar.controller;

import static org.hamcrest.core.Is.is;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import racingcar.game.dto.GameRequestDTO;

@DisplayName("Http Method")
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingCarGameWebControllerTest {
    
    @LocalServerPort
    int port;
    
    @BeforeEach
    void setUp() {
        RestAssured.port = this.port;
    }
    
    @DisplayName("POST /plays - 게임 실행")
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
    
    @DisplayName("이름이 1개 이하인 경우 예외 발생")
    @Test
    void handleIllegalArgumentExceptionTest() {
        final GameRequestDTO gameRequestDTO = new GameRequestDTO("io", 2);
        
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("자동차는 2대 이상이어야 합니다."));
    }
    
    @DisplayName("이름 없는 경우 예외 발생")
    @Test
    void handleIllegalArgumentExceptionTest2() {
        final GameRequestDTO gameRequestDTO = new GameRequestDTO("", 2);
        
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("자동차는 2대 이상이어야 합니다."));
    }
    
    @DisplayName("이름 5자 초과 예외 발생")
    @Test
    void handleIllegalArgumentExceptionTest3() {
        final GameRequestDTO gameRequestDTO = new GameRequestDTO("eeeeeeee,eee", 2);
        
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("자동차 이름은 5자를 초과할 수 없습니다."));
    }
    
    @DisplayName("이름 중복인 경우 예외 발생")
    @Test
    void handleIllegalArgumentExceptionTest4() {
        final GameRequestDTO gameRequestDTO = new GameRequestDTO("echo,io,echo", 2);
        
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("중복된 자동차 이름이 있습니다."));
    }
    
    @DisplayName("시도 횟수 0인 경우 예외 발생")
    @Test
    void handleIllegalArgumentExceptionTest5() {
        final GameRequestDTO gameRequestDTO = new GameRequestDTO("echo,io", 0);
        
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("시도 횟수는 1 이상이어야 합니다."));
    }
    
    @DisplayName("시도 횟수 음수인 경우 예외 발생")
    @Test
    void handleIllegalArgumentExceptionTest6() {
        final GameRequestDTO gameRequestDTO = new GameRequestDTO("echo,io", -1);
        
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(gameRequestDTO)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is("시도 횟수는 1 이상이어야 합니다."));
    }
    
}