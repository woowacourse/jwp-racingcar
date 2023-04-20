package racingcar.model;

import racingcar.Strategy.NumberGenerator;

public class Car {
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 5;
    private static final int MOVING_CONDITION = 4;

    private final String name;
    private int position = 0;

    public Car(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "자동차 이름은 최소 " + MINIMUM_NAME_LENGTH + "글자, 최대 " + MAXIMUM_NAME_LENGTH + "글자까지 가능해요.");
        }
    }

    // TODO: 지우기
    public void move(int movingCondition) {
        if (movingCondition >= MOVING_CONDITION) {
            position++;
        }
    }

    public int findHigherPosition(int position) {
        return Math.max(this.position, position);
    }

    public boolean isMaxPosition(int maxPosition) {
        return maxPosition == position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move(int trial, NumberGenerator numberGenerator) {
        for (int i = 0; i < trial; i++) {
            move(numberGenerator);
        }
    }

    private void move(NumberGenerator numberGenerator) {
        if (numberGenerator.generate() >= MOVING_CONDITION) {
            position++;
        }
    }
}
