package racingcar.controller.validator;

import org.springframework.stereotype.Component;
import racingcar.controller.dto.GameInfoRequest;

@Component
public class GameOptionValidator {

    private static final String CAR_NAMES_BLANK_ERROR = "[ERROR] 경주할 자동차 이름이 입력되지 않았습니다.";
    private static final String TRY_NUM_NOT_POSITIVE_ERROR = "[ERROR] 시도할 횟수는 1 이상이어야 합니다.";

    public void validateGameOption(final GameInfoRequest gameInfoRequest) {
        validateNames(gameInfoRequest.getNames());
        validateTrialCount(gameInfoRequest.getCount());
    }

    private void validateNames(String names) {
        if (names.length() < 1) {
            throw new IllegalArgumentException(CAR_NAMES_BLANK_ERROR);
        }
    }

    private void validateTrialCount(int trialCount) {
        if (trialCount <= 0) {
            throw new IllegalArgumentException(TRY_NUM_NOT_POSITIVE_ERROR);
        }
    }
}
