package racingcar.domain;

public class CarName {

    private static final int MAX_CAR_NAME_LENGTH = 5;

    private final String carName;

    public CarName(final String carName) {
        validateCarName(carName);
        this.carName = carName;
    }

    private void validateCarName(final String carName) {
        validateNotBlank(carName);
        validateProperLength(carName);
    }

    private void validateNotBlank(final String carName) {
        if (carName.isBlank()) {
            throw new IllegalArgumentException("자동차의 이름은 공백이면 안됩니다.");
        }
    }

    private void validateProperLength(final String carName) {
        if (carName.length() > MAX_CAR_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름의 길이는 1자 이상, 5자 이하여야 합니다.");
        }
    }

    public String getCarName() {
        return carName;
    }
}
