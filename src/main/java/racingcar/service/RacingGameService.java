package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.GameResponseDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;
import racingcar.utils.Parser;
import racingcar.validator.Validator;

@Service
public class RacingGameService {
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingGameService(GameDao gameDao, CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public GameResponseDto play(String carNames, int count) {
        List<String> validCarNames = getValidCarNames(carNames);
        RacingGame racingGame = initializeGame(validCarNames);
        int validTryCount = getValidTryCount(count);
        racingGame.runRound(validTryCount);
        long gameId = gameDao.save(validTryCount);
        List<RacingCarResultDto> results = calculateResults(racingGame, gameId);
        carDao.saveAll(results);
        List<String> winners = carDao.findWinnersByGameId(gameId);
        List<RacingCarDto> racingCarDtos = carDao.findCarsByGameId(gameId);
        return new GameResponseDto(winners, racingCarDtos);
    }

    private List<String> getValidCarNames(String carNames) {
        List<String> parsedCarNames = Parser.parsing(carNames, ",");
        Validator.validateNames(parsedCarNames);
        return parsedCarNames;
    }

    private int getValidTryCount(int tryCount) {
        Validator.validateTryCount(tryCount);
        return tryCount;
    }

    private List<RacingCarResultDto> calculateResults(final RacingGame racingGame, final long gameId) {
        return racingGame.calculateResult()
                .entrySet()
                .stream()
                .map((e) -> RacingCarResultDto.of(e.getKey(), e.getValue().getValue(), gameId))
                .collect(Collectors.toUnmodifiableList());
    }

    private RacingGame initializeGame(List<String> carNames) {
        List<RacingCar> racingCars = carNames.stream()
                .map(RacingCar::new)
                .collect(Collectors.toUnmodifiableList());
        return new RacingGame(racingCars, new RandomNumberGenerator());
    }
}
