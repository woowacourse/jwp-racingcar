package racingcar;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import racingcar.domain.Car;
import racingcar.domain.NumberPicker;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@SpringBootApplication
public class RacingCarConsoleApplication {

    private final NumberPicker numberPicker;

    public RacingCarConsoleApplication(final NumberPicker numberPicker) {
        this.numberPicker = numberPicker;
    }

    public static void main(final String[] args) {
        new SpringApplicationBuilder(RacingCarConsoleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        final RacingGame racingGame = new RacingGame(InputView.inputCarName(), InputView.inputTryCount());
        racingGame.race(numberPicker);
        final List<String> winnerNames = racingGame.findWinner()
                .stream()
                .map(Car::getCarName)
                .collect(Collectors.toList());
        OutputView.printWinner(winnerNames);
    }
}
