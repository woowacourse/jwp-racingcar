package racingcar.domain;

import java.util.Objects;

public class CarName {

    private static final int CAR_NAME_MAX_LENGTH = 5;
    private static final String BLANK_MESSAGE = "%s은(는) 빈 값이 들어올 수 없습니다.";
    private static final String LENGTH_MESSAGE = "%d글자를 초과하였습니다.";

    private final String name;

    private CarName(final String name) {
        validateNameBlank(name);
        validateNameLength(name);
        this.name = name.trim();
    }

    public static CarName create(final String carName) {
        return new CarName(carName);
    }

    private void validateNameBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(String.format(BLANK_MESSAGE, "이름"));
        }
    }

    private void validateNameLength(final String name) {
        if (name.length() > CAR_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(LENGTH_MESSAGE, CAR_NAME_MAX_LENGTH));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarName carName = (CarName) o;
        return Objects.equals(getName(), carName.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
