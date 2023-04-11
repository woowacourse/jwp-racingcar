package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.Winner;
import racingcar.domain.Winners;
import racingcar.domain.numbergenerator.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final NumberGenerator numberGenerator;

    public RacingCarService(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public GameResult play(final List<String> names, final int gameTime) {
        final Cars cars = new Cars(names);
        final GameTime time = new GameTime(String.valueOf(gameTime));

        while (time.isPlayable()) {
            cars.moveCars(numberGenerator);
            time.runOnce();
        }
        return new GameResult(cars);
    }

    public static class GameResult {

        private final String winners;
        private final List<CarDto> racingCars;

        public GameResult(final Cars cars) {
            // TODO : Refactor
            this.racingCars = cars.getCars().stream()
                    .map(CarDto::new)
                    .collect(Collectors.toList());
            this.winners = new Winners(cars).getWinners().stream()
                    .map(Winner::getName)
                    .collect(Collectors.joining(","));
        }

        public String getWinners() {
            return winners;
        }

        public List<CarDto> getRacingCars() {
            return racingCars;
        }
    }

    public static class CarDto {
        private final String name;
        private final int position;

        public CarDto(final Car car) {
            this.name = car.getCarName();
            this.position = car.getPosition();
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    }
}
