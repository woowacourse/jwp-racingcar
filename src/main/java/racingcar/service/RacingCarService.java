package racingcar.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.PlayResultDao;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberPicker;
import racingcar.domain.TryCount;
import racingcar.entity.GameHistoryEntity;

@Service
public class RacingCarService {

    private final PlayResultDao playResultDao;
    private final RacingCarDao racingCarDao;

    public RacingCarService(PlayResultDao playResultDao, RacingCarDao racingCarDao) {
        this.playResultDao = playResultDao;
        this.racingCarDao = racingCarDao;
    }

    public Map<Long, List<Car>> findGameHistory() {
        return playResultDao.findGameHistories().stream()
                .collect(groupingBy(GameHistoryEntity::getId,
                        mapping(RacingCarService::mapCar, toUnmodifiableList())));
    }

    private static Car mapCar(final GameHistoryEntity gameHistoryEntity) {
        return new Car(gameHistoryEntity.getPlayerName(), gameHistoryEntity.getPlayerPosition());
    }

    public void playRound(final Cars cars, final TryCount tryCount, final NumberPicker numberPicker) {
        for (int i = 0; i < tryCount.getValue(); i++) {
            cars.runRound(numberPicker);
        }
        saveGameResult(cars, tryCount);
    }

    @Transactional
    void saveGameResult(Cars cars, TryCount tryCount) {
        Long gameId = playResultDao.insertWithKeyHolder(tryCount.getValue(), cars.getWinner());
        for (Car car : cars.getCars()) {
            racingCarDao.insert(gameId, car.getName().getValue(), car.getDistance().getValue());
        }
    }
}
