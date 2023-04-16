package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.view.ApplicationCommand;
import racingcar.view.ConsoleInputView;

import java.util.Map;

@SpringBootApplication
public class RacingCarApplication {
    public static void main(String[] args) {
        final Map<ApplicationCommand, Runnable> applicationChoices = Map.of(
                ApplicationCommand.CONSOLE, RacingCarApplication::runConsoleApplication,
                ApplicationCommand.WEB, () -> runWebApplication(args)
        );

        ApplicationCommand applicationCommand = ConsoleInputView.getApplicationCommand();
        applicationChoices.get(applicationCommand).run();
    }

    private static void runConsoleApplication() {
        ConsoleRacingCarApplication.run();
    }

    private static void runWebApplication(String[] args) {
        SpringApplication.run(RacingCarApplication.class, args);
    }
}
