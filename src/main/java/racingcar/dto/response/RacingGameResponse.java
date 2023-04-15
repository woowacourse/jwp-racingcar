package racingcar.dto.response;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.dto.CarEntity;

public class RacingGameResponse {

    private static final String DELIMITER = ", ";

    private final List<CarResponse> carResponses;
    private final String winners;

    public RacingGameResponse(final List<CarResponse> carResponses, final String winners) {
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

    public static RacingGameResponse createByEntity(List<CarEntity> carResultEntities) {
        List<CarResponse> carResponses = carResultEntities.stream()
                .map(CarResponse::new)
                .collect(toList());

        final String winners = carResultEntities.stream()
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
