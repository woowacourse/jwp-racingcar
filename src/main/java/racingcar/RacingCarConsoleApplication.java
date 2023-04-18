package racingcar;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.NumberPicker;
import racingcar.domain.RandomNumberPicker;
import racingcar.dto.CarPositionDto;
import racingcar.dto.RaceDto;
import racingcar.repositoryImpl.DefaultRacingGameRepository;
import racingcar.service.RacingCarsService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(final String[] args) {
        final List<String> names = InputView.inputCarName();
        final int targetCount = InputView.inputTryCount();
        final RacingCarsService racingCarsService = new RacingCarsService(numberPicker(),
                new DefaultRacingGameRepository());
        final RaceDto raceResult = racingCarsService.race(names, targetCount);

        final List<String> winnerNames = raceResult.getWinners()
                .stream()
                .map(CarPositionDto::getCarName)
                .collect(Collectors.toList());
        OutputView.printWinner(winnerNames);
    }

    private static NumberPicker numberPicker() {
        return new RandomNumberPicker();
    }
}
