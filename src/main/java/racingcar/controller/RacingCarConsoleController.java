package racingcar.controller;

import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.PlayResultDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class RacingCarConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        final RacingGame racingGame = createGame();

        final String winners = racingGame.getWinnerNames().stream()
                .collect(Collectors.joining(", "));
        final List<CarDto> cars = racingGame.getCars().stream()
                .map(CarDto::new)
                .collect(Collectors.toList());

        outputView.printPlayResult(new PlayResultDto(cars, winners));
    }

    private RacingGame createGame() {
        final List<String> carNames = inputView.askCarNames();
        final int racingCount = inputView.askRacingCount();

        final RacingGame racingGame = RacingGame.of(carNames);
        racingGame.race(racingCount);

        return racingGame;
    }

}
