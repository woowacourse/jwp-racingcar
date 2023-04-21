package racingcar.domain;

public class Car {

    private static final int DRIVING_DISTANCE = 1;
    private static final int MOVING_STANDARD = 4;
    private static final int NUMBER_MIN_INCLUSIVE = 0;
    private static final int NUMBER_MAX_INCLUSIVE = 9;

    private final CarName name;
    private int drivenDistance = 0;

    public Car(final String name) {
        this.name = new CarName(name);
    }

    public void drive(NumberGenerator numberGenerator) {
        int number = chooseNumber(numberGenerator);
        if (number >= MOVING_STANDARD) {
            drivenDistance += DRIVING_DISTANCE;
        }
    }

    private int chooseNumber(NumberGenerator numberGenerator) {
        int number = numberGenerator.generate();
        validateNumber(number);
        return number;
    }

    private void validateNumber(final int number) {
        if (number < NUMBER_MIN_INCLUSIVE || NUMBER_MAX_INCLUSIVE < number) {
            throw new IllegalArgumentException("차량 전진 판별 숫자는 0이상 9이하여야합니다.");
        }
    }

    public int getDrivenDistance() {
        return drivenDistance;
    }

    public String getName() {
        return name.getName();
    }
}
