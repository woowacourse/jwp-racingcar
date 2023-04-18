package racingcar.model.car;

import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.HasBlankCarNameException;
import racingcar.exception.InvalidCarNameFormatException;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.car.strategy.RandomMovingStrategy;

import java.util.regex.Pattern;

public class Car {
    private static final int POSITION_INIT = 1;
    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern STRING_PATTERN = Pattern.compile("(^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$)+");
    private static final String EXCEED_CAR_NAME_LENGTH_ERROR_MESSAGE = "자동차 이름은 다섯 글자 이하여야 합니다.";
    private static final String INVALID_CAR_NAME_FORMAT_ERROR_MESSAGE = "자동차 이름은 문자와 숫자만 가능합니다.";
    private static final String BLANK_CAR_NAME_ERROR_MESSAGE = "자동차 이름에 공백이 포함될 수 없습니다.";

    private final String carName;
    private int position;
    private final MovingStrategy movingStrategy;

    public Car(final String carName, final MovingStrategy movingStrategy) {
        validate(carName);

        this.carName = carName;
        this.position = POSITION_INIT;
        this.movingStrategy = movingStrategy;
    }

    public Car(final String carName, final int position) {
        this.carName = carName;
        this.position = position;
        this.movingStrategy = new RandomMovingStrategy();
    }

    private void validate(final String carName) {
        validateHasBlank(carName);
        validateValue(carName);
        validateOverMaxNameLength(carName);
    }

    private void validateValue(final String carName) {
        if (!STRING_PATTERN.matcher(carName).matches()) {
            throw new InvalidCarNameFormatException(INVALID_CAR_NAME_FORMAT_ERROR_MESSAGE);
        }
    }

    private void validateHasBlank(final String carName) {
        if (carName.isBlank()) {
            throw new HasBlankCarNameException(BLANK_CAR_NAME_ERROR_MESSAGE);
        }
    }

    private void validateOverMaxNameLength(final String carName) {
        if (carName.length() > MAX_NAME_LENGTH) {
            throw new ExceedCarNameLengthException(EXCEED_CAR_NAME_LENGTH_ERROR_MESSAGE);
        }
    }

    public void moveForward() {
        position++;
    }

    public boolean isWinner(final int maxPosition) {
        return position == maxPosition;
    }

    public boolean movable() {
        return movingStrategy.movable();
    }

    public String getCarName() {
        return carName;
    }

    public int getPosition() {
        return position;
    }
}
