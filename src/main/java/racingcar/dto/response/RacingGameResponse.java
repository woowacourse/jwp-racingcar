package racingcar.dto.response;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.entity.CarEntity;

public class RacingGameResponse {

    private static final String DELIMITER = ",";

    private final List<CarResponse> racingCars;
    private final String winners;

    private RacingGameResponse(List<CarResponse> racingCars, String winners) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public static RacingGameResponse createByCars(List<Car> cars, List<Car> winningCars) {
        List<CarResponse> racingCars = cars.stream()
                .map(CarResponse::new)
                .collect(toList());
        String winners = winningCars.stream()
                .map(Car::getName)
                .collect(joining(DELIMITER));

        return new RacingGameResponse(racingCars, winners);
    }

    public static RacingGameResponse createByEntity(List<CarEntity> carEntities) {
        List<CarResponse> racingCars = carEntities.stream()
                .map(CarResponse::new)
                .collect(toList());

        String winners = carEntities.stream()
                .filter(CarEntity::isWin)
                .map(CarEntity::getName)
                .collect(Collectors.joining(DELIMITER));

        return new RacingGameResponse(racingCars, winners);
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }

    public String getWinners() {
        return winners;
    }
}
