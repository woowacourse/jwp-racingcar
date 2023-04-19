package racingcar;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import racingcar.controller.GameController;

@SpringBootApplication
public class RacingCarConsoleApplication {
    private GameController gameController;

    RacingCarConsoleApplication(GameController gameController) {
        this.gameController = gameController;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(RacingCarConsoleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        gameController.play();
    }
}
