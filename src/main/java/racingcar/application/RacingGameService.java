package racingcar.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;
import racingcar.domain.Winner;
import racingcar.domain.Winners;
import racingcar.domain.numbergenerator.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RacingGameService {

    private final NumberGenerator numberGenerator;
    private final RacingGameRepository racingGameRepository;

    public RacingGameService(final NumberGenerator numberGenerator, final RacingGameRepository racingGameRepository) {
        this.numberGenerator = numberGenerator;
        this.racingGameRepository = racingGameRepository;
    }

    public Long play(final List<String> names, final int gameTime) {
        final Cars cars = mapToCars(names);
        final GameTime time = new GameTime(gameTime);
        final RacingGame racingGame = new RacingGame(cars, time);

        racingGame.play(numberGenerator);
        return racingGameRepository.save(racingGame);
    }

    private Cars mapToCars(final List<String> names) {
        return new Cars(names.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
    }

    public GameResult findResultById(final Long gameId) {
        final RacingGame racingGame = racingGameRepository
                .findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임이 없습니다."));
        return new GameResult(racingGame.getCars(), racingGame.winners());
    }

    public List<GameResult> findAll() {
        return racingGameRepository.findAll()
                .stream()
                .map(it -> new GameResult(it.getCars(), it.winners()))
                .collect(Collectors.toList());
    }

    public static class GameResult {
        private final List<String> winners;
        private final List<CarDto> racingCars;

        public GameResult(final List<Car> cars, final Winners winners) {
            this.racingCars = cars.stream()
                    .map(CarDto::new)
                    .collect(Collectors.toList());
            this.winners = winners.getWinners().stream()
                    .map(Winner::getName)
                    .collect(Collectors.toList());
        }

        public List<String> getWinners() {
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
