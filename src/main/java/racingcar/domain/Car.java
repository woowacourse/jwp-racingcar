package racingcar.domain;


import racingcar.dto.RacingCarDto;
import racingcar.utils.Validator;

public class Car {
    private static final int MIN_MOVE_POWER = 4;
    private static final int DEFAULT_POSITION = 0;

    private final String name;
    private int position;

    public Car(final String name) {
        Validator.checkBlank(name);
        Validator.checkLength(name);
        this.name = name;
        this.position = DEFAULT_POSITION;
    }

    public void move(final int power) {
        if (power >= MIN_MOVE_POWER) {
            this.position++;
        }
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public RacingCarDto convertToDto() {
        return new RacingCarDto(name, position);
    }
}
