package racingcar.model.car;

import org.springframework.util.StringUtils;
import racingcar.exception.CustomException;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.car.strategy.RandomMovingStrategy;

import java.util.regex.Pattern;

public class Car {
    private static final int POSITION_INIT = 1;
    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern STRING_PATTERN = Pattern.compile("(^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$)+");

    private final String carName;
    private int position;

    private Car(final String carName, final int position) {
        validate(carName);

        this.carName = carName;
        this.position = position;
    }

    public static Car from(final String carName) {
        return new Car(carName, POSITION_INIT);
    }

    public static Car of(final String carName, final int position) {
        return new Car(carName, position);
    }

    private void validate(final String carName) {
        validateEmptyInput(carName);
        validateValue(carName);
        validateOverMaxNameLength(carName);
    }

    private void validateEmptyInput(final String carName) {
        if (!StringUtils.hasText(carName)) {
            throw new CustomException("비어있는 자동차 이름이 존재합니다.");
        }
    }

    private void validateValue(final String carName) {
        if (!STRING_PATTERN.matcher(carName).matches()) {
            throw new CustomException("자동차 이름은 문자와 숫자만 가능합니다.");
        }
    }

    private void validateOverMaxNameLength(final String carName) {
        if (carName.length() > MAX_NAME_LENGTH) {
            throw new CustomException("자동차 이름은 다섯 글자 이하여야 합니다.");
        }
    }

    public void moveForward() {
        position++;
    }

    public boolean isWinner(final int maxPosition) {
        return position == maxPosition;
    }

    public boolean movable(final MovingStrategy movingStrategy) {
        return movingStrategy.movable();
    }

    public String getCarName() {
        return carName;
    }

    public int getPosition() {
        return position;
    }
}
