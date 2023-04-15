package racingcar.controller;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

import io.restassured.RestAssured;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameInputDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RacingGameControllerTest {
    @LocalServerPort
    int port;

    private static Stream<Arguments> provideNormalInputs() {
        return Stream.of(
                Arguments.of("리사", 1),
                Arguments.of("리사, 토미", 2),
                Arguments.of("리사, 토미, 포비", 99),
                Arguments.of("리사, 토미, 포비,네오", 100)
        );
    }

    private static Stream<Arguments> provideDuplicateNameInputs() {
        return Stream.of(
                Arguments.of("리사, 리사", 1),
                Arguments.of("리사, 토미, 리사", 2),
                Arguments.of("리사, 토미, 리사, 포비", 99),
                Arguments.of("리사, 토미, 포비, 리사,네오", 100)
        );
    }

    private static Stream<Arguments> provideEmptyNameInputs() {
        return Stream.of(
                Arguments.of("", 1),
                Arguments.of(", ", 2),
                Arguments.of(" ,,", 99),
                Arguments.of("  ", 100)
        );
    }

    private static Stream<Arguments> provideLowerThenOnePlayCountInputs() {
        return Stream.of(
                Arguments.of("리사", -1),
                Arguments.of("리사, 토미", -2),
                Arguments.of("리사, 토미, 포비", -99),
                Arguments.of("리사, 토미, 포비, 네오", -100)
        );
    }

    private static Stream<Arguments> provideLargerThenHundredPlayCountInputs() {
        return Stream.of(
                Arguments.of("리사", 101),
                Arguments.of("리사,  토미", 102),
                Arguments.of("리사,  토미, 포비", 999),
                Arguments.of("리사,  토미, 포비, 네오", 1000)
        );
    }

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @DisplayName("게임이 정상적으로 실행된다.")
    @ParameterizedTest(name = "참가자: {0}, 플레이 횟수: {1}")
    @MethodSource("provideNormalInputs")
    void playGame(final String names, final int playCount) {
        final GameInputDto racingGameDto = new GameInputDto(names, playCount);
        final int namesSize = names.split(",").length;

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("winners", notNullValue())
                .body("playCount", notNullValue())
                .body("racingCars.size()", is(namesSize));
    }

    @DisplayName("중복된 이름이 있을 경우 예외가 발생한다.")
    @ParameterizedTest(name = "참가자: {0}, 플레이 횟수: {1}")
    @MethodSource("provideDuplicateNameInputs")
    void duplicateNameException(final String names, final int playCount) {
        final GameInputDto racingGameDto = new GameInputDto(names, playCount);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("이름이 비어있을 경우 예외가 발생한다.")
    @ParameterizedTest(name = "참가자: {0}, 플레이 횟수: {1}")
    @MethodSource("provideEmptyNameInputs")
    void emptyNameException(final String names, final int playCount) {
        final GameInputDto racingGameDto = new GameInputDto(names, playCount);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("플레이 횟수가 1 미만일 경우 예외가 발생한다.")
    @ParameterizedTest(name = "참가자: {0}, 플레이 횟수: {1}")
    @MethodSource("provideLowerThenOnePlayCountInputs")
    void lowerThenOnePlayCountException(final String names, final int playCount) {
        final GameInputDto racingGameDto = new GameInputDto(names, playCount);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("플레이 횟수가 100 초과일 경우 예외가 발생한다.")
    @ParameterizedTest(name = "참가자: {0}, 플레이 횟수: {1}")
    @MethodSource("provideLargerThenHundredPlayCountInputs")
    void largerThenHundredPlayCountException(final String names, final int playCount) {
        final GameInputDto racingGameDto = new GameInputDto(names, playCount);

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(racingGameDto)
                .when().post("/plays")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
