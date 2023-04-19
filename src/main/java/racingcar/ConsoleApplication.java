package racingcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import racingcar.game.controller.ConsoleController;

@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner {
    
    private final ConsoleController consoleController;
    
    public ConsoleApplication(final ConsoleController consoleController) {
        this.consoleController = consoleController;
    }
    
    public static void main(final String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }
    
    @Override
    public void run(final String... args) throws Exception {
        this.consoleController.play();
    }
}
