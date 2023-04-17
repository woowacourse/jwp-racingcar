package racingcar.car.model;

public final class CarPosition implements Position {
    
    public static final int MIN_VALUE = 0;
    public static final String POSITION_VALUE_IS_LESS_THAN_MIN_VALUE = "자동차 위치 값은 " + MIN_VALUE + "보다 작을 수 없습니다.";
    private final int value;
    
    private CarPosition(final int value) {
        this.value = value;
    }
    
    public static Position create(final int value) {
        validate(value);
        return new CarPosition(value);
    }
    
    private static void validate(final int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException(POSITION_VALUE_IS_LESS_THAN_MIN_VALUE);
        }
    }
    
    @Override
    public int compareTo(final Position o) {
        return Integer.compare(this.value, o.getValue());
    }
    
    @Override
    public Position add(final int value) {
        validate(value);
        return new CarPosition(this.value + value);
    }
    
    @Override
    public int getValue() {
        return this.value;
    }
}
