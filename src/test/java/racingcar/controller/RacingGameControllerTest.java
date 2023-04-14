package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import racingcar.dto.RequestDto;

@DisplayName("post 요청 시 상태코드 200 반환 및 반환된 json 형식 데이터 확인")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RacingGameControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void play() {
        RequestDto requestDto = new RequestDto("jena", 3);

        Response response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().response();

        String winners = response.jsonPath().getString("winners");
        assertThat(winners).isEqualTo("jena");

        List<Map<String, Object>> racingCars = response.jsonPath().getList("racingCars");
        assertThat(racingCars).hasSize(1);
        assertThat(racingCars.get(0).get("name")).isEqualTo("jena");
    }
}
