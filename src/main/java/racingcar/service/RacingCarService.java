package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameResultDao;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.CarStatusResponseDto;
import racingcar.dto.GameHistoriesResponseDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.PlayerHistoryDto;
import racingcar.utils.RandomPowerGenerator;

@Service
public class RacingCarService {

    private static final String CAR_NAME_DELIMITER = ",";

    private final RandomPowerGenerator randomPowerGenerator;
    private final GameResultDao gameResultDao;

    @Autowired
    public RacingCarService(final RandomPowerGenerator powerGenerator, final GameResultDao gameResultDao) {
        this.randomPowerGenerator = powerGenerator;
        this.gameResultDao = gameResultDao;
    }

    @Transactional(readOnly = true)
    public List<GameHistoriesResponseDto> findAllGameHistories() {
        List<Long> allGamesId = gameResultDao.findAllGamesId();
        List<GameHistoriesResponseDto> allGameHistories = new ArrayList<>();

        allGamesId.forEach(gameId -> {
            List<PlayerHistoryDto> playerHistories = gameResultDao.findPlayerHistoriesByGameId(gameId);

            List<CarStatusResponseDto> carStatuses = playerHistories.stream()
                    .map(CarStatusResponseDto::toDto)
                    .collect(Collectors.toList());

            allGameHistories.add(GameHistoriesResponseDto.toDto(findWinnerNames(playerHistories), carStatuses));
        });

        return allGameHistories;
    }

    private String findWinnerNames(final List<PlayerHistoryDto> playerHistories) {
        return playerHistories.stream()
                .filter(PlayerHistoryDto::isWinner)
                .map(PlayerHistoryDto::getName)
                .collect(Collectors.joining(CAR_NAME_DELIMITER));
    }

    @Transactional
    public GameResultResponseDto startRace(final Cars cars, final TryCount tryCount) {
        moveCars(cars, tryCount);
        return gameResultDao.saveGame(cars, tryCount);
    }

    private void moveCars(final Cars cars, final TryCount tryCount) {
        for (int i = 0; i < tryCount.getTryCount(); i++) {
            cars.moveAll(randomPowerGenerator);
        }
    }
}
