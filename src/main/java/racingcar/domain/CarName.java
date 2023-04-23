package racingcar.domain;

public class CarName {

    private static final int MAX_CAR_NAME_LENGTH = 5;

    private final String carName;

    public CarName(String carName) {
        validateCarName(carName);
        this.carName = carName;
    }

    private void validateCarName(String carName) {
        validateBlankOf(carName);
        validateLengthOf(carName);
    }

    private void validateBlankOf(String carName) {
        if (carName == null || carName.isBlank()) {
            throw new IllegalArgumentException("자동차의 이름이 비어있습니다. 비어있지 않은 이름을 입력해주세요.");
        }
    }

    private void validateLengthOf(String carName) {
        if (carName.length() > MAX_CAR_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("자동차의 이름은 %d자 이하여야 합니다.", MAX_CAR_NAME_LENGTH));
        }
    }

    public String getCarName() {
        return this.carName;
    }
}
