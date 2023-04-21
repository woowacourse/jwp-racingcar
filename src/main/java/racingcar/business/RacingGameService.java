package racingcar.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.persistence.repository.GameRepository;
import racingcar.presentation.dto.RacingGameRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final GameRepository racingGameRepository;

    public RacingGameService(final GameRepository gameRepository) {
        this.racingGameRepository = gameRepository;
    }

    @Transactional
    public RacingGame playRacingGame(final RacingGameRequest racingGameRequest) {
        RacingGame racingGame = createRacingGame(racingGameRequest);
        racingGame.start();
        racingGameRepository.saveGame(racingGame);
        return racingGame;
    }

    private RacingGame createRacingGame(final RacingGameRequest racingGameRequest) {
        return new RacingGame(
                toCars(racingGameRequest.getNames()),
                racingGameRequest.getCount(),
                new RandomNumberGenerator()
        );
    }

    private List<Car> toCars(final String cars) {
        return Arrays.stream(cars.split(","))
                .map(name -> new Car(name, 0))
                .collect(Collectors.toList());
    }

    public List<RacingGame> readAllGames() {
        return racingGameRepository.selectAllGames();
    }
}
