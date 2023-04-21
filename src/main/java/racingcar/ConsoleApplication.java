package racingcar;

import java.util.Scanner;

import racingcar.controller.ConsoleController;
import racingcar.dao.InMemoryCarDao;
import racingcar.dao.InMemoryGameDao;
import racingcar.service.RacingcarService;
import racingcar.ui.ConsoleView;

public class ConsoleApplication {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleView VIew = new ConsoleView(scanner);
            RacingcarService racingcarService = new RacingcarService(new InMemoryGameDao(), new InMemoryCarDao());
            ConsoleController consoleController = new ConsoleController(VIew, racingcarService);
            consoleController.run();
        }
    }
}
