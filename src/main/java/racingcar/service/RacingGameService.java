package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.*;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RacingGameService {

    private static final int RACE_START_POINT = 0;
    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;
    public static final String DELIMITER = ",";

    public RacingGameResponseDto run(RacingGameRequestDto racingGameRequestDto) {
        RacingCars racingCars = createRacingCars(racingGameRequestDto);
        TryCount tryCount = new TryCount(racingGameRequestDto.getCount());
        raceCars(racingCars, tryCount);

        return createResult(racingCars);
    }

    private RacingCars createRacingCars(final RacingGameRequestDto racingGameRequestDto) {
        List<String> carNames = List.of(racingGameRequestDto.getNames().split(DELIMITER));
        List<Car> cars = CarFactory.generate(carNames, RACE_START_POINT);
        return new RacingCars(cars);
    }

    private void raceCars(final RacingCars racingCars, final TryCount tryCount) {
        NumberGenerator numberGenerator = new RandomNumberGenerator(MINIMUM_RANDOM_NUMBER, MAXIMUM_RANDOM_NUMBER);

        IntStream.range(0, tryCount.getTries())
                .forEach(ignored -> racingCars.moveCars(numberGenerator));
    }

    private RacingGameResponseDto createResult(final RacingCars racingCars) {
        List<String> winnerCars = racingCars.pickWinnerCarNames();

        List<CarStatusDto> carStatuses = racingCars.getCars().stream()
                .map(car -> new CarStatusDto(car.getName(), car.getCurrentPosition()))
                .collect(Collectors.toUnmodifiableList());

        return new RacingGameResponseDto(winnerCars, carStatuses);
    }
}
