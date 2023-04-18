package racingcar.service;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static racingcar.exception.ExceptionMessage.EMPTY_CARS;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Position;
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
        for (Car car : cars.getCars()) {
            move(moveStrategy, car);
        }
    }

    private void validateEmptyCars() {
        if (cars == null) {
            throw new IllegalStateException(EMPTY_CARS.getMessage());
        }
    }

    private void move(MoveStrategy moveStrategy, Car car) {
        if (moveStrategy.isMovable()) {
            car.move();
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
        Position maxPosition = getMaxPosition();
        List<Car> winners = cars.getCars()
                .stream()
                .filter(car -> car.isSamePosition(maxPosition))
                .collect(toList());
        return RacingCarWinnerDto.of(winners);
    }

    private Position getMaxPosition() {
        validateEmptyCars();
        return cars.getCars()
                .stream()
                .map(Car::getMovedLength)
                .max(comparing(Position::getValue))
                .orElseThrow(() -> new IllegalStateException(EMPTY_CARS.getMessage()));
    }
}
