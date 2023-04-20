package racing.web.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racing.domain.Car;
import racing.domain.CarFactory;
import racing.domain.Cars;
import racing.domain.RacingCarGame;
import racing.domain.TrialCount;
import racing.domain.repository.CarRepository;
import racing.domain.repository.RacingGameRepository;
import racing.domain.utils.RandomRacingCarNumberGenerator;

@Service
public class RacingGameService {

    private final CarRepository carRepository;
    private final RacingGameRepository racingGameRepository;

    public RacingGameService(CarRepository carRepository, RacingGameRepository racingGameRepository) {
        this.carRepository = carRepository;
        this.racingGameRepository = racingGameRepository;
    }

    public Long playNewGame(int count, String carNames) {
        Cars cars = CarFactory.carFactory(carNames);
        RacingCarGame racingCarGame = new RacingCarGame(cars, new TrialCount(count));
        racingCarGame.playRounds(new RandomRacingCarNumberGenerator());

        Long savedGameId = racingGameRepository.saveGameByCount(count);
        carRepository.saveCarsInGame(cars, savedGameId);

        return savedGameId;
    }

    public RacingCarGame getResultById(Long gameId) {
        return racingGameRepository.findRacingGameById(gameId);
    }

    public String filterWinnersToCarNames(RacingCarGame racingCarGame) {
        Cars winners = racingCarGame.winnerCars();

        return winners.getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public List<RacingCarGame> getAllResults() {
        return racingGameRepository.findAllGamesOrderByRecent();
    }
}
