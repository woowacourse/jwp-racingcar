package racingcar.presentation;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.presentation.dto.CarData;
import racingcar.presentation.dto.GameResultResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ClientResponseConverter {

    public static GameResultResponse toGameResultResponse(final RacingGame racingGame) {
        return new GameResultResponse(toWinnerResponse(racingGame), toCarDataResponse(racingGame));
    }

    private static List<CarData> toCarDataResponse(final RacingGame racingGame) {
        return racingGame.getCars()
                .stream()
                .map(car -> new CarData(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private static String toWinnerResponse(final RacingGame racingGame) {
        return racingGame.getWinners()
                .stream()
                .map(Car::getCarName)
                .reduce((o1, o2) -> o1 + "," + o2)
                .orElseThrow();
    }
}
