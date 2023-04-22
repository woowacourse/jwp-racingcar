package racingcar;

import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleApplication {
    public static void main(String[] args) {
        RacingGame racingGame = new RacingGame(InputView.readCarNames());
        racingGame.moveCars(InputView.readCount());

        OutputView.printResultMessage();

        List<CarDto> cars = racingGame.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());

        OutputView.printAllCars(cars);
        OutputView.printWinners(racingGame.decideWinners());
    }
}
