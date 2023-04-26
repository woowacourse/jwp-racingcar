package racingcar.domain;

public class CarName {

    private static final int MAX_LENGTH = 5;

    private final String carName;

    public CarName(final String carName) {
        final String carNameStrip = carName.strip();
        validateBlank(carNameStrip);
        validateLength(carNameStrip);
        this.carName = carNameStrip;
    }

    private static void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백 입력은 허용되지 않습니다.");
        }
    }

    private static void validateLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 차 이름은 5글자 이하로 입력해야합니다.");
        }
    }

    public String getCarName() {
        return carName;
    }
}
