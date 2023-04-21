package racingcar.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberPicker;
import racingcar.domain.TryCount;
import racingcar.entity.GameHistoryEntity;
import racingcar.repository.RacingGameRepository;
import racingcar.service.dto.RacingCarDto;
import racingcar.service.dto.RacingCarGameResultDto;

@Service
public class RacingCarService {

    private final RacingGameRepository racingGameRepository;
    private final NumberPicker numberPicker;

    public RacingCarService(final RacingGameRepository racingGameRepository, final NumberPicker numberPicker) {
        this.racingGameRepository = racingGameRepository;
        this.numberPicker = numberPicker;
    }

    public List<RacingCarGameResultDto> findGameHistories() {
        return racingGameRepository.findGameHistories().stream()
                .collect(groupingBy(GameHistoryEntity::getId))
                .values().stream()
                .map(RacingCarService::toDto)
                .collect(toUnmodifiableList());
    }

    private static Car mapCar(final GameHistoryEntity gameHistoryEntity) {
        return new Car(gameHistoryEntity.getPlayerName(), gameHistoryEntity.getPlayerPosition());
    }

    public RacingCarGameResultDto playRound(final List<String> playerNames, final int tryCountValue) {
        final Cars cars = Cars.from(playerNames);
        final TryCount tryCount = new TryCount(tryCountValue);
        for (int i = 0; i < tryCount.getValue(); i++) {
            cars.runRound(numberPicker);
        }
        return new RacingCarGameResultDto(cars.getWinners(), toDto(cars));
    }

    private List<RacingCarDto> toDto(final Cars cars) {
        return cars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName().getValue(), car.getDistance().getValue()))
                .collect(toUnmodifiableList());
    }

    private static RacingCarGameResultDto toDto(final List<GameHistoryEntity> historyEntities) {
        if (historyEntities.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final List<String> winnerNames = Arrays.stream(historyEntities.get(0).getWinners().split(",", -1))
                .collect(toUnmodifiableList());
    }

    public void saveGameResult(final Cars cars) {
        racingGameRepository.saveGameResult(cars);
    }

    @Transactional
    public void saveGameResult(final RacingCarGameResultDto racingCarGameResult, final int count) {
        final List<RacingCarDto> racingCars = racingCarGameResult.getRacingCars();
        racingGameRepository.saveGameResult(toCars(racingCars));
    }

    private Cars toCars(final List<RacingCarDto> racingCars) {

    }
}
