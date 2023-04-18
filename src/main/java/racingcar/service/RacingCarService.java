package racingcar.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberPicker;
import racingcar.domain.TryCount;
import racingcar.entity.GameHistoryEntity;
import racingcar.repository.RacingGameRepository;

@Service
public class RacingCarService {

    private final RacingGameRepository racingGameRepository;

    public RacingCarService(final RacingGameRepository racingGameRepository) {
        this.racingGameRepository = racingGameRepository;
    }

    public Map<Long, List<Car>> findGameHistory() {
        return racingGameRepository.findGameHistories().stream()
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
    }

    @Transactional
    public void saveGameResult(Cars cars, TryCount tryCount) {
        racingGameRepository.saveGameResult(cars, tryCount);
    }
}
