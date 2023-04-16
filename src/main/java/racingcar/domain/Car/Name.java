package racingcar.domain.Car;

import java.util.Objects;

public class Name {

    private static final int CAR_NAME_MINIMUM_LENGTH = 1;
    private static final int CAR_NAME_MAXIMUM_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validateLengthOfCarName(name);
        this.name = name;
    }

    private void validateLengthOfCarName(String name) {
        if (name.length() > CAR_NAME_MAXIMUM_LENGTH || name.length() < CAR_NAME_MINIMUM_LENGTH) {
            throw new IllegalArgumentException("자동차 이름의 길이는 1이상 5이하여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name n = (Name) o;
        return Objects.equals(name, n.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
