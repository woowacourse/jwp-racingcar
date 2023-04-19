package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.context.annotation.ApplicationScope;
import racingcar.controller.GameController;
import racingcar.dao.GameDao;
import racingcar.dao.GameLogDao;
import racingcar.dao.WinnersDao;
import racingcar.service.GameService;
@SpringBootApplication
public class RacingCarConsoleApplication {
    @Autowired
    private GameController gameController;
    RacingCarConsoleApplication(GameController gameController){
        this.gameController = gameController;
    }
    public static void main(String[] args) {
        new SpringApplicationBuilder(RacingCarConsoleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        gameController.play();
        gameController.showResult();
    }
}
