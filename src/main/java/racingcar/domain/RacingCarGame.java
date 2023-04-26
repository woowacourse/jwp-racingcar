package racingcar.domain;

import static java.util.stream.Collectors.toList;
import static racingcar.exception.ExceptionMessage.EMPTY_CARS;

import java.util.List;
import racingcar.dto.RacingCarNamesDto;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;

public class RacingCarGame {
    private final Cars cars;

    private RacingCarGame(final Cars cars) {
        this.cars = cars;
    }

    public static RacingCarGame from(RacingCarNamesDto racingCarNamesDto) {
        return new RacingCarGame(new Cars(racingCarNamesDto.getNames()));
    }

    public void moveCars(MoveStrategy moveStrategy) {
        validateEmptyCars();
        cars.moveCars(moveStrategy);
    }

    private void validateEmptyCars() {
        if (cars == null) {
            throw new IllegalStateException(EMPTY_CARS.getMessage());
        }
    }

    public List<RacingCarStatusDto> getCarStatuses() {
        validateEmptyCars();
        return cars.getCars()
                .stream()
                .map(RacingCarStatusDto::from)
                .collect(toList());
    }

    public RacingCarWinnerDto findWinners() {
        List<Car> winners = cars.getWinners();
        return RacingCarWinnerDto.of(winners);
    }
}
