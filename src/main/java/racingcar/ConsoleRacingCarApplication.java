package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import racingcar.controller.RacingGameController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Objects;

import static org.springframework.context.annotation.FilterType.ANNOTATION;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;
import static racingcar.controller.RacingGameController.PlayGameRequest;
import static racingcar.controller.RacingGameController.PlayGameResponse;

@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = ANNOTATION, classes = Console.class),
        excludeFilters = @ComponentScan.Filter(type = ASSIGNABLE_TYPE, classes = WebRacingCarApplication.class)
)
public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(ConsoleRacingCarApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

    @Console
    static class GameRunner implements CommandLineRunner {

        private final RacingGameController controller;

        GameRunner(final RacingGameController controller) {
            this.controller = controller;
        }

        @Override
        public void run(final String... args) {
            try {
                final String names = InputView.inputCarName();
                final int time = InputView.inputGameTime();
                final ResponseEntity<PlayGameResponse> play = controller.play(new PlayGameRequest(names, time));
                OutputView.printWinners(Objects.requireNonNull(play.getBody()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                run(args);
            }
        }
    }
}
