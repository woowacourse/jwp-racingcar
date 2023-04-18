package racingcar.dto.response;

import java.util.List;

public class CarGameResponse {
    private final String winners;
    private final List<CarResponse> cars;

    public CarGameResponse(String winners, List<CarResponse> cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getCars() {
        return cars;
    }
}
