package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.MoveChance;
import racingcar.domain.RandomMoveChance;

@Service
public class GameService {

    private final GameDao gameDao;
    private final MoveChance moveChance;


    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
        this.moveChance = RandomMoveChance.getInstance();
    }

    public Game createGameWith(List<String> names, int trialCount) {
        return new Game(makeCarsWith(names), trialCount);
    }

    private List<Car> makeCarsWith(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void play(Game game) {
        while (game.isNotDone()) {
            game.playOnceWith(moveChance);
        }

        gameDao.insertResult(game);
    }
}
