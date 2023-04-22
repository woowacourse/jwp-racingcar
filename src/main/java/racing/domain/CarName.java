package racing.domain;

public class CarName {
    private static final String CANT_CONTAIN_SPACE = "자동차 이름에 공백이 포함될 수 없습니다.";
    private static final String UNSUITABLE_LENGTH = "자동차 이름은 1자~5자만 입력할 수 있습니다.";
    private static final int CAR_NAME_MIN_LENGTH = 1;
    private static final int CAR_NAME_MAX_LENGTH = 5;

    private final String value;

    public CarName(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String carName) {
        validateNotBlank(carName);
        validateLengthInRange(carName);
    }

    private void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(CANT_CONTAIN_SPACE);
        }
    }

    private void validateLengthInRange(String name) {
        if (name.length() < CAR_NAME_MIN_LENGTH
                || CAR_NAME_MAX_LENGTH < name.length()) {
            throw new IllegalArgumentException(UNSUITABLE_LENGTH);
        }
    }

    public String getValue() {
        return value;
    }
}
