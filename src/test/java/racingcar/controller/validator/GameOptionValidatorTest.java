package racingcar.controller.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.view.InputViewValidator;


@SpringBootTest
class GameOptionValidatorTest {

    @Autowired
    private GameOptionValidator gameOptionValidator;

    @Nested
    @DisplayName("전체 자동차 이름 검증 테스트")
    class carNamesTest {

        @Test
        @DisplayName("전체 입력받은 자동차 이름이 빈 값인 경우 예외 처리")
        void carNamesBlankTest() {
            String carNames = "";
            GameInfoRequest gameInfoRequest = new GameInfoRequest(carNames, 1);

            Assertions.assertThatThrownBy(() -> gameOptionValidator.validateGameOption(gameInfoRequest))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0"})
    @DisplayName("시도할 횟수가 0 이하인 경우 예외 테스트")
    void readTryNumNotPositiveTest(String tryNum) {

        GameInfoRequest gameInfoRequest = new GameInfoRequest("성하,우르", Integer.parseInt(tryNum));
        Assertions.assertThatThrownBy(() -> gameOptionValidator.validateGameOption(gameInfoRequest))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

