package racingcar.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.GameResponseDto;
import racingcar.dto.HistoryResponseDto;
import racingcar.dto.RacingCarDto;
import racingcar.entity.RacingCarEntity;
import racingcar.validator.Validator;

@Service
public class RacingGameService {
    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingGameService(final GameDao gameDao, final CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    @Transactional
    public GameResponseDto play(final String carNames, final int count) {
        List<String> validCarNames = getValidCarNames(carNames);
        RacingGame racingGame = initializeGame(validCarNames);
        int validTryCount = getValidTryCount(count);

        racingGame.runRound(validTryCount);

        long gameId = gameDao.save(validTryCount);

        List<RacingCarEntity> results = calculateResults(racingGame, gameId);
        carDao.saveAll(results);

        List<String> winnerNames = findWinnerNamesByGameId(gameId);
        List<RacingCarDto> racingCarDtos = findRacingCarsByGameId(gameId);
        return new GameResponseDto(winnerNames, racingCarDtos);
    }

    private List<RacingCarDto> findRacingCarsByGameId(final long gameId) {
        return carDao.findAll().stream()
                .filter(racingCarEntity -> racingCarEntity.getGameId() == gameId)
                .map(racingCarEntity -> RacingCarDto.of(racingCarEntity.getName(), racingCarEntity.getPosition()))
                .collect(toUnmodifiableList());
    }

    private List<String> findWinnerNamesByGameId(final long gameId) {
        return carDao.findAll().stream()
                .filter(racingCarEntity -> racingCarEntity.getGameId() == gameId)
                .filter(RacingCarEntity::getIsWin)
                .map(RacingCarEntity::getName)
                .collect(toUnmodifiableList());
    }

    private List<String> getValidCarNames(final String carNames) {
        List<String> parsedCarNames = Arrays.stream(carNames.split(","))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
        Validator.validateNames(parsedCarNames);
        return parsedCarNames;
    }

    private int getValidTryCount(final int tryCount) {
        Validator.validateTryCount(tryCount);
        return tryCount;
    }

    private List<RacingCarEntity> calculateResults(final RacingGame racingGame, final long gameId) {
        return racingGame.calculateResult()
                .entrySet()
                .stream()
                .map((e) -> new RacingCarEntity(e.getKey().getName(), e.getKey().getPosition(), e.getValue().getValue(),
                        gameId))
                .collect(toUnmodifiableList());
    }

    private RacingGame initializeGame(final List<String> carNames) {
        List<RacingCar> racingCars = carNames.stream()
                .map(RacingCar::new)
                .collect(toUnmodifiableList());
        return new RacingGame(racingCars, new RandomNumberGenerator());
    }

    public List<HistoryResponseDto> findHistory() {
        List<RacingCarEntity> allResults = carDao.findAll();
        Map<Long, String> winners = groupingWinnersByGameId(allResults);
        Map<Long, List<RacingCarDto>> results = groupingResultByGameId(allResults);
        return winners.entrySet().stream()
                .map(entry -> new HistoryResponseDto(entry.getValue(), results.get(entry.getKey())))
                .collect(toUnmodifiableList());
    }

    private Map<Long, String> groupingWinnersByGameId(final List<RacingCarEntity> allResults) {
        return allResults.stream()
                .filter(RacingCarEntity::getIsWin)
                .collect(groupingBy(RacingCarEntity::getGameId,
                        mapping(RacingCarEntity::getName, joining(","))));
    }

    private Map<Long, List<RacingCarDto>> groupingResultByGameId(final List<RacingCarEntity> allResults) {
        return allResults.stream()
                .collect(groupingBy(RacingCarEntity::getGameId,
                        mapping(racingCarEntity -> RacingCarDto.of(racingCarEntity.getName(),
                                racingCarEntity.getPosition()), toUnmodifiableList())));
    }
}
