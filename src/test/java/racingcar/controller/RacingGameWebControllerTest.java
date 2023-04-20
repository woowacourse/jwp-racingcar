package racingcar.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class RacingGameWebControllerTest extends RacingGameFixtures {


    @DisplayName("doGame을 실행하면 게임의 결과가 반환된다.")
    @Test
    void doGame_givenCarNamesAndCount_thenReturnResult() {
        // when
        final ExtractableResponse<Response> response = savePlayers("브리,토미,브라운", 10);

        // then
        final JsonPath result = response.jsonPath();
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(result.getString("winners")).isNotNull(),
                () -> assertThat(result.getList("racingCars.name", String.class)).containsExactly("브리", "토미", "브라운"),
                () -> assertThat(result.getList("racingCars.position", Integer.class)).hasSize(3)
        );
    }

    @DisplayName("findResult()를 실행하면 게임의 결과가 반환된다.")
    @Test
    void findResult_whenCall_thenReturnGameResponses() {
        // given
        savePlayers("콩하나,에단", 10);
        savePlayers("준팍,에코,소니", 30);

        // when
        final ExtractableResponse<Response> response = given()
                .when().get("/plays")
                .then().log().all()
                .extract();

        // then
        final JsonPath result = response.jsonPath();
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(result.getList("winners")).hasSize(2),
                () -> assertThat(result.getList("racingCars")).hasSize(2),
                () -> assertThat(result.getList("racingCars.name")).containsExactly(List.of("콩하나", "에단"), List.of("준팍", "에코", "소니"))
        );
    }
}
