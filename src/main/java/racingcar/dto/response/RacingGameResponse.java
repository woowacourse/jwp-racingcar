package racingcar.dto.response;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.entity.CarEntity;

public class RacingGameResponse {

    private static final String DELIMITER = ", ";

    private final List<CarResponse> carResponses;
    private final String winners;

    private RacingGameResponse(final List<CarResponse> carResponses, final String winners) {
        this.carResponses = carResponses;
        this.winners = winners;
    }

    public static RacingGameResponse createByCars(List<Car> cars, List<Car> winningCars) {
        List<CarResponse> carResponses = cars.stream()
                .map(CarResponse::new)
                .collect(toList());

        String winners = winningCars.stream()
                .map(Car::getName)
                .collect(joining(DELIMITER));

        return new RacingGameResponse(carResponses, winners);
    }

    public static RacingGameResponse createByEntity(List<CarEntity> carEntities) {
        List<CarResponse> carResponses = carEntities.stream()
                .map(CarResponse::new)
                .collect(toList());

        final String winners = carEntities.stream()
                .filter(CarEntity::isWin)
                .map(CarEntity::getName)
                .collect(Collectors.joining(DELIMITER));

        return new RacingGameResponse(carResponses, winners);
    }

    public List<CarResponse> getCarResponses() {
        return carResponses;
    }

    public String getWinners() {
        return winners;
    }
}
