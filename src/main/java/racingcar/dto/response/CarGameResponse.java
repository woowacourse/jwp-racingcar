package racingcar.dto.response;

import java.util.List;

public class CarGameResponse {
    private final String winners;
    private final List<CarResponse> racingCars;

    private CarGameResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static CarGameResponse of(String winners, List<CarResponse> racingCars) {
        return new CarGameResponse(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }

    @Override
    public String toString() {
        return "CarGameResponse{" +
                "winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }
}
