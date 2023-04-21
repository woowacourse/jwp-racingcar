package racing;

import racing.controller.ConsoleRacingGameController;
import racing.dao.car.MemoryCarDao;
import racing.dao.game.MemoryGameDao;
import racing.service.ConsoleRacingGameService;

public class ConsoleApplication {

    public static void main(String[] args) {
        final ConsoleRacingGameService service = new ConsoleRacingGameService(new MemoryCarDao(), new MemoryGameDao());
        final ConsoleRacingGameController controller = new ConsoleRacingGameController(service);
        controller.play(controller.getInput());
    }
}
