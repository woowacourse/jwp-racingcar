package racingcar;

import java.util.List;
import java.util.Scanner;
import racingcar.controller.RaceController;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.entity.Game;
import racingcar.entity.Player;
import racingcar.service.RacingCarService;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameDao gameDao = new GameDao() {
            @Override
            public Long insert(Game game) {
                return 1L;
            }

            @Override
            public List<Game> findAll() {
                return null;
            }
        };
        PlayerDao playerDao = new PlayerDao() {
            @Override
            public void insert(List<Player> players) {

            }

            @Override
            public List<Player> findAll() {
                return null;
            }
        };
        RaceController raceController = new RaceController(
                new InputView(scanner),
                new OutputView(),
                new RacingCarService(gameDao, playerDao, new RandomNumberGenerator())
        );
        raceController.play();
        scanner.close();
    }
}
