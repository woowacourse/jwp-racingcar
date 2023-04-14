package racingcar.dto.input;

import java.util.List;

public final class CarNameRequest {
    private final List<String> carNames;

    public CarNameRequest(final List<String> carNames) {
        this.carNames = carNames;
    }

    public List<String> getCarNames() {
        return carNames;
    }
}
