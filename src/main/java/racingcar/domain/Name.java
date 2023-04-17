package racingcar.domain;

import static racingcar.option.Option.MAX_NAME_LENGTH;
import static racingcar.option.Option.MIN_NAME_LENGTH;

public class Name {

    private final String name;

    public Name(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름이 너무 깁니다.");
        }
        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름이 너무 짧습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
