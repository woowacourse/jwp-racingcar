package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import racingcar.controller.RacingGameController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Objects;

import static racingcar.controller.RacingGameController.PlayGameRequest;
import static racingcar.controller.RacingGameController.PlayGameResponse;

@SpringBootApplication
public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(ConsoleRacingCarApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

    @Component
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
