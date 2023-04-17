package racing.domain;

public class CarName {
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
            throw new IllegalArgumentException();
        }
    }

    private void validateLengthInRange(String name) {
        if (name.length() < CAR_NAME_MIN_LENGTH
                || CAR_NAME_MAX_LENGTH < name.length()) {
            throw new IllegalArgumentException();
        }
    }

    public String getValue() {
        return value;
    }
}
