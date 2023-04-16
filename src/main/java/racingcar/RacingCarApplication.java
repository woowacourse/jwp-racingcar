package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.controller.RacingController;
import racingcar.dao.CarInfoCollectionDao;
import racingcar.dao.RacingCollectionDao;
import racingcar.dto.RacingResultResponseDto;
import racingcar.service.RacingService;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.ApplicationCommand;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;

import java.util.Map;

@SpringBootApplication
public class RacingCarApplication {
    public static void main(String[] args) {
        ConsoleInputView inputView = new ConsoleInputView();

        final Map<ApplicationCommand, Runnable> applicationChoices = Map.of(
                ApplicationCommand.CONSOLE, () -> runConsoleApplication(inputView, new ConsoleOutputView()),
                ApplicationCommand.WEB, () -> runWebApplication(args)
        );

        ApplicationCommand applicationCommand = inputView.getApplicationCommand();
        applicationChoices.get(applicationCommand).run();
    }

    private static void runConsoleApplication(ConsoleInputView inputView, ConsoleOutputView outputView) {
        RacingController controller = new RacingController(
                new RacingService(RandomNumberGenerator.makeInstance(),
                        new RacingCollectionDao(),
                        new CarInfoCollectionDao())
        );
        RacingResultResponseDto racingResult = controller.playRacing(inputView.getRacingInfoRequest());
        outputView.printResult(racingResult);
    }

    private static void runWebApplication(String[] args) {
        SpringApplication.run(RacingCarApplication.class, args);
    }
}
