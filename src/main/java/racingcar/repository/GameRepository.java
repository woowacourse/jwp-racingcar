package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarGameDao;
import racingcar.dao.entity.Game;

@Repository
public class GameRepository {

    private final RacingCarGameDao racingCarGameDao;

    public GameRepository(RacingCarGameDao racingCarGameDao) {
        this.racingCarGameDao = racingCarGameDao;
    }

    public Game save(Game game) {
        if (game.getGameId() == null) {
            Long gameId = racingCarGameDao.insertGameWithKeyHolder(game);
            return new Game(gameId, game.getPlayCount(), game.getWinners());
        }

        racingCarGameDao.updateGame(game);
        return game;
    }
}
