package racingcar.car.model;

public final class CarName implements Name {
    
    public static final int MIN_NAME_LENGTH = 5;
    public static final String CAR_NAME_CANNOT_EXCEED_FIVE_LETTERS = "자동차 이름은 " + MIN_NAME_LENGTH + "자를 초과할 수 없습니다.";
    private final String value;
    
    private CarName(final String value) {
        this.value = value;
    }
    
    public static CarName create(final String value) {
        validate(value);
        return new CarName(value);
    }
    
    private static void validate(final String name) {
        if (name.length() > MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(CAR_NAME_CANNOT_EXCEED_FIVE_LETTERS);
        }
    }
    
    @Override
    public String getValue() {
        return this.value;
    }
}
