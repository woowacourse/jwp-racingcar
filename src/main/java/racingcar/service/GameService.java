package racingcar.service;

import racingcar.dao.GameDao;
import racingcar.domain.Game;
import racingcar.domain.MoveChance;

public class GameService {

    private final GameDao gameDao;
    private final MoveChance moveChance;

    public GameService(GameDao gameDao, MoveChance moveChance) {
        this.gameDao = gameDao;
        this.moveChance = moveChance;
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
