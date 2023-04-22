package racingcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import racingcar.service.RacingService;
import racingcar.view.ApplicationCommand;
import racingcar.view.ConsoleInputView;

import java.util.Map;

@SpringBootApplication
public class RacingCarApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(RacingCarApplication.class);

        final Map<ApplicationCommand, Runnable> applicationChoices = Map.of(
                ApplicationCommand.CONSOLE, () -> runConsoleApplication(springApplication, args),
                ApplicationCommand.WEB, () -> runWebApplication(springApplication, args)
        );

        ApplicationCommand applicationCommand = ConsoleInputView.getApplicationCommand();
        applicationChoices.get(applicationCommand).run();
    }

    private static void runConsoleApplication(SpringApplication springApplication, String[] args) {
        springApplication.setAdditionalProfiles("console");
        ApplicationContext context = springApplication.run(args);
        RacingService service = context.getBean(RacingService.class);

        ConsoleRacingCarApplication application = new ConsoleRacingCarApplication(service);
        application.run();
    }

    private static void runWebApplication(SpringApplication springApplication, String[] args) {
        springApplication.setAdditionalProfiles("web");
        springApplication.run(args);
    }
}
