package racingcar.domain;

import racingcar.exception.CarNameBlankException;
import racingcar.exception.CarNameLengthException;

public class CarName {

    private static final int MAX_CAR_NAME_LENGTH = 5;

    private final String carName;

    public CarName(String carName) {
        validateCarName(carName);
        this.carName = carName;
    }

    private void validateCarName(String carName) {
        validateBlankOf(carName);
        validateLengthOf(carName);
    }

    private void validateBlankOf(String carName) {
        if (carName == null || carName.isBlank()) {
            throw new CarNameBlankException();
        }
    }

    private void validateLengthOf(String carName) {
        if (carName.length() > MAX_CAR_NAME_LENGTH) {
            throw new CarNameLengthException();
        }
    }

    public String getCarName() {
        return this.carName;
    }
}
