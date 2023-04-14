package racingcar.domain;


import racingcar.dto.PlayerDto;

public class Car {
    private static final int MIN_MOVE_POWER = 4;
    private static final int DEFAULT_POSITION = 0;
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 10;

    private final String name;
    private int position;

    public Car(final String name) {
        validateName(name);
        this.name = name;
        this.position = DEFAULT_POSITION;
    }

    private void validateName(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("1글자 이상 10글자 이하의 이름을 입력해 주세요.");
        }
    }

    public void move(final int power) {
        if (power >= MIN_MOVE_POWER) {
            this.position++;
        }
    }

    public PlayerDto convertToDto() {
        return new PlayerDto(name, position);
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
