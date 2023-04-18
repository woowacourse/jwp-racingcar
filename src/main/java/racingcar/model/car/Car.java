package racingcar.model.car;

import racingcar.exception.CustomException;
import racingcar.exception.ExceptionStatus;
import racingcar.model.car.strategy.MovingStrategy;

import java.util.Optional;
import java.util.regex.Pattern;

public class Car {
    private static final int POSITION_INIT = 1;
    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern STRING_PATTERN = Pattern.compile("(^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$)+");

    private final String carName;
    private int position;
    private final MovingStrategy movingStrategy;

    private Car(final String carName, final int position, final MovingStrategy movingStrategy) {
        validate(carName);

        this.carName = carName;
        this.position = position;
        this.movingStrategy = movingStrategy;
    }

    public static Car of(final String carName, final int position, final MovingStrategy movingStrategy) {
        final String stripCarName = carName.strip();

        return new Car(stripCarName, position, movingStrategy);
    }

    public static Car of(final String carName, final MovingStrategy movingStrategy) {
        final String stripCarName = carName.strip();

        return new Car(stripCarName, POSITION_INIT, movingStrategy);
    }

    private void validate(final String carName) {
        validateNull(carName);
        validateHasBlank(carName);
        validateValue(carName);
        validateOverMaxNameLength(carName);
    }

    private void validateNull(final String carName) {
        final Optional<String> optionalCarName = Optional.ofNullable(carName);
        if (optionalCarName.isEmpty()) {
            throw new CustomException(ExceptionStatus.HAS_BLANK_CAR_NAME);
        }
    }

    private void validateValue(final String carName) {
        if (!STRING_PATTERN.matcher(carName).matches()) {
            throw new CustomException(ExceptionStatus.INVALID_CAR_NAME_FORMAT);
        }
    }

    private void validateHasBlank(final String carName) {
        if (carName.isBlank() || carName.isEmpty()) {
            throw new CustomException(ExceptionStatus.HAS_BLANK_CAR_NAME);
        }
    }

    private void validateOverMaxNameLength(final String carName) {
        if (carName.length() > MAX_NAME_LENGTH) {
            throw new CustomException(ExceptionStatus.EXCEED_CAR_NAME_LENGTH);
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
