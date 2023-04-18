package racingcar;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.NumberPicker;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberPicker;
import racingcar.repositoryImpl.DefaultRacingGameRepository;
import racingcar.service.AddRaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(final String[] args) {
        final List<String> names = InputView.inputCarName();
        final int targetCount = InputView.inputTryCount();
        final AddRaceService addRaceService = new AddRaceService(numberPicker(),
                new DefaultRacingGameRepository());
        final RacingGame raceResult = addRaceService.addRace(names, targetCount);

        final List<String> winnerNames = raceResult.findWinner()
                .stream()
                .map(Car::getCarName)
                .collect(Collectors.toList());
        OutputView.printWinner(winnerNames);
    }

    private static NumberPicker numberPicker() {
        return new RandomNumberPicker();
    }
}
