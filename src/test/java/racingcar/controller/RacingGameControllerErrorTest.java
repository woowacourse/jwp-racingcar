package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class RacingGameControllerErrorTest extends RacingGameFixtures {

    @Test
    @DisplayName("음수로된 동작횟수을 입력하면 예외를 발생시킨다.")
    void doGame_givenNegativeTrialCount_thenFail() {
        // when
        final ExtractableResponse<Response> response = savePlayers("콩하나,에단", -1);

        // then
        final JsonPath result = response.jsonPath();
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value()),
                () -> assertThat(result.getString("exceptionMessage")).isEqualTo("[ERROR] 양의 정수를 입력해주세요.")
        );
    }

    @Test
    @DisplayName(",로 안 나뉘어진 이름을 입력하면 예외를 발생시킨다.")
    void doGame_givenInvalidNameSeparator_thenFail() {
        // when
        final ExtractableResponse<Response> response = savePlayers("콩하나|에단", -1);

        // then
        final JsonPath result = response.jsonPath();
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value()),
                () -> assertThat(result.getString("exceptionMessage")).isEqualTo("[ERROR] 쉼표로 이름을 구분해주세요.")
        );
    }
}
