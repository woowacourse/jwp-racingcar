package racingcar.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.NumberPicker;
import racingcar.domain.TryCount;
import racingcar.repository.RacingGameRepository;
import racingcar.service.dto.GameHistoryDto;
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
                .collect(groupingBy(GameHistoryDto::getGameId))
                .values().stream()
                .map(RacingCarService::convert)
                .collect(toUnmodifiableList());
    }

    private static RacingCarGameResultDto convert(final List<GameHistoryDto> gameHistoryDtos) {
        final List<String> winners = gameHistoryDtos.get(0).getWinners();
        final List<RacingCarDto> racingCars = gameHistoryDtos.stream()
                .map(gameHistoryDto -> new RacingCarDto(gameHistoryDto.getName(), gameHistoryDto.getPosition()))
                .collect(toUnmodifiableList());
        return new RacingCarGameResultDto(winners, racingCars);
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

    //RacingCarGameResultDto가 모든 레이어에서 호출됨. 이를 수정하고 싶은데 어떻게 해야 할지?
    @Transactional
    public void saveGameResult(final RacingCarGameResultDto racingCarGameResult) {
        racingGameRepository.saveGameResult(racingCarGameResult.getWinners(), racingCarGameResult.getRacingCars());
    }
}
