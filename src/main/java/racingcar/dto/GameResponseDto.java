package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.ArrayList;
import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResponse> racingCars;

    private GameResponseDto(final String winners, final List<PlayerResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponseDto of(final List<String> winners, final Cars cars) {
        final List<PlayerResponse> racingCars = new ArrayList<>();
        List<Car> carsResult = cars.getLatestResult();
        for (Car car : carsResult) {
            racingCars.add(new PlayerResponse(car.getCarName().getName(), car.getCurrentPosition().getPosition()));
        }
        return new GameResponseDto(String.join(",", winners), racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResponse> getRacingCars() {
        return racingCars;
    }

    private static class PlayerResponse {
        private final String name;
        private final int position;

        public PlayerResponse(final String name, final int position) {
            this.name = name;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    }
}
