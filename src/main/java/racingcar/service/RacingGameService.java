package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.CarData;
import racingcar.dto.GameResultResponse;
import racingcar.dto.RacingGameRequest;
import racingcar.persistence.repository.GameRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final GameRepository racingGameRepository;

    public RacingGameService(final GameRepository gameRepository) {
        this.racingGameRepository = gameRepository;
    }

    @Transactional
    public GameResultResponse playRacingGame(final RacingGameRequest racingGameRequest) {
        RacingGame racingGame = createRacingGame(racingGameRequest);
        racingGame.start();
        GameResultResponse gameResultResponse = new GameResultResponse(
                mapWinnerNamesTextFrom(racingGame),
                mapCarDtosFrom(racingGame)
        );
        racingGameRepository.saveGameRecord(gameResultResponse, racingGameRequest.getCount());
        return gameResultResponse;
    }

    private RacingGame createRacingGame(final RacingGameRequest racingGameRequest) {
        return new RacingGame(
                List.of(racingGameRequest.getNames().split(",")),
                racingGameRequest.getCount(),
                new RandomNumberGenerator()
        );
    }

    private List<CarData> mapCarDtosFrom(final RacingGame racingGame) {
        return racingGame.getCars().stream()
                .sorted(Comparator.comparingInt(Car::getPosition).reversed())
                .map(car -> new CarData(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private String mapWinnerNamesTextFrom(final RacingGame racingGame) {
        return racingGame.getWinners().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(","));
    }

    public List<GameResultResponse> makeGameRecords() {
        return racingGameRepository.makeGameRecords();
    }
}
