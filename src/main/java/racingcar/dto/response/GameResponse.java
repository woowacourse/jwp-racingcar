package racingcar.dto.response;

import racingcar.entity.CarEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {
    private final String winners;
    private final List<CarResponse> racingCars;

    private GameResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponse of(String winners, List<CarResponse> racingCars) {
        return new GameResponse(winners, racingCars);
    }

    public static GameResponse of(List<CarEntity> winners, List<CarEntity> racingCars) {
        String parsedWinners = winners.stream()
                .map(CarEntity::getName)
                .collect(Collectors.joining(","));

        List<CarResponse> parsedCars = racingCars.stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());

        return new GameResponse(parsedWinners, parsedCars);
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
