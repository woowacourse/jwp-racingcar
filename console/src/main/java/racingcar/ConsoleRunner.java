package racingcar;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.domain.Car;
import racingcar.domain.NumberPicker;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Configuration
public class ConsoleRunner {

    private final NumberPicker numberPicker;

    public ConsoleRunner(final NumberPicker numberPicker) {
        this.numberPicker = numberPicker;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            final RacingGame racingGame = new RacingGame(InputView.inputCarName(), InputView.inputTryCount());
            racingGame.race(numberPicker);
            final List<String> winnerNames = racingGame.findWinner()
                    .stream()
                    .map(Car::getCarName)
                    .collect(Collectors.toList());
            OutputView.printWinner(winnerNames);
        };
    }
}
