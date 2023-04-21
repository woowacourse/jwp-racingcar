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
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;
import racingcar.entity.GameHistoryEntity;
import racingcar.repository.RacingGameRepository;

@Service
public class RacingCarService {

    private final RacingGameRepository racingGameRepository;
    private final NumberPicker numberPicker;

    public RacingCarService(final RacingGameRepository racingGameRepository, final NumberPicker numberPicker) {
        this.racingGameRepository = racingGameRepository;
        this.numberPicker = numberPicker;
    }

    public Map<Long, List<Car>> findGameHistory() {
        return racingGameRepository.findGameHistories().stream()
                .collect(groupingBy(GameHistoryEntity::getId,
                        mapping(RacingCarService::mapCar, toUnmodifiableList())));
    }

    private static Car mapCar(final GameHistoryEntity gameHistoryEntity) {
        return new Car(gameHistoryEntity.getPlayerName(), gameHistoryEntity.getPlayerPosition());
    }

    //TODO : 저장로직과 게임 실행로직, 게임 결과를 반환하는 기능을 적당한 기준으로 분리하기
    @Transactional
    public RacingCarGameResultDto playRound(final List<String> playerNames, final int tryCountValue) {
        final Cars cars = Cars.from(playerNames);
        final TryCount tryCount = new TryCount(tryCountValue);
        for (int i = 0; i < tryCount.getValue(); i++) {
            cars.runRound(numberPicker);
        }
        saveGameResult(cars, tryCount);
        return new RacingCarGameResultDto(cars.getWinners(), toDto(cars));
    }

    private List<RacingCarDto> toDto(final Cars cars) {
        return cars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName().getValue(), car.getDistance().getValue()))
                .collect(toUnmodifiableList());
    }

    //TODO : private으로 수정하기ㅣ
    public void saveGameResult(final Cars cars, final TryCount tryCount) {
        racingGameRepository.saveGameResult(cars, tryCount);
    }
}
