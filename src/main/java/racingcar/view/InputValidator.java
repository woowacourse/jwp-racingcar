package racingcar.view;

import java.util.List;
import java.util.Set;

class InputValidator {

    private InputValidator() {
    }

    public static void validateCarNames(List<String> carNames) {
        CarNamesValidator.validateCarsCount(carNames);
        CarNamesValidator.validateLengthOfCarNames(carNames);
        CarNamesValidator.validateDuplicatedCarNames(carNames);
    }

    public static void validateTryCount(int tryCount) {
        TryCountValidator.validateTryCount(tryCount);
    }

    private static class CarNamesValidator {
        private static final int CAR_NAME_MINIMUM_LENGTH = 1;
        private static final int CAR_NAME_MAXIMUM_LENGTH = 5;
        private static final int MINIMUM_CARS_COUNT = 2;


        private static void validateCarsCount(List<String> carNames) {
            if (carNames.size() < MINIMUM_CARS_COUNT) {
                throw new IllegalArgumentException("자동차 수는 2대 이상이어야 합니다.");
            }
        }

        private static void validateLengthOfCarNames(List<String> carNames) {
            for (String carName : carNames) {
                validateLengthOfCarName(carName);
            }
        }

        private static void validateLengthOfCarName(String name) {
            if (name.length() > CAR_NAME_MAXIMUM_LENGTH || name.length() < CAR_NAME_MINIMUM_LENGTH) {
                throw new IllegalArgumentException("자동차 이름의 길이는 1이상 5이하여야 합니다.");
            }
        }

        private static void validateDuplicatedCarNames(List<String> carNames) {
            if (Set.copyOf(carNames).size() != carNames.size()) {
                throw new IllegalArgumentException("자동차 이름은 중복되지 않아야 합니다.");
            }
        }
    }

    private static class TryCountValidator {
        private static final int MINIMUM_TRY_COUNT = 0;

        private static void validateTryCount(int tries) {
            if (tries <= MINIMUM_TRY_COUNT) {
                throw new IllegalArgumentException("시도 횟수는 1이상의 숫자여야 합니다.");
            }
        }
    }
}
