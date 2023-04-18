package racingcar.domain;

import racingcar.common.ErrorData;
import racingcar.common.exception.ResourceLengthException;

import java.util.Objects;

public class CarName {

    private static final int CAR_NAME_MAX_LENGTH = 5;

    private final String name;

    private CarName(final String name) {
        validateNameLength(name);
        this.name = name.trim();
    }

    public static CarName create(final String carName) {
        return new CarName(carName);
    }

    private void validateNameLength(final String name) {
        if (name.length() > CAR_NAME_MAX_LENGTH) {
            throw new ResourceLengthException(new ErrorData<>(CAR_NAME_MAX_LENGTH));
        }
    }

    @Override
    public boolean equals(Object diffCarName) {
        if (this == diffCarName) return true;
        if (diffCarName == null || getClass() != diffCarName.getClass()) return false;
        CarName carName = (CarName) diffCarName;
        return Objects.equals(name, carName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
