package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarGameDao;
import racingcar.dao.entity.Player;
import racingcar.domain.RacingGame;

import java.util.List;

@Repository
public class RacingGameRepository implements RacingGames {

    private final RacingCarGameDao racingCarGameDao;

    public RacingGameRepository(RacingCarGameDao racingCarGameDao) {
        this.racingCarGameDao = racingCarGameDao;
    }

    @Override
    public RacingGame save(RacingGame racingGame) {
        if (racingGame.getGameId() == null) {
            Long gameId = racingCarGameDao.insertGameWithKeyHolder(RacingGameMapper.mapToGame(racingGame));
            RacingGame racingGameWithId = new RacingGame(gameId, racingGame);
            insertPlayers(RacingGameMapper.mapToPlayers(racingGameWithId));

            return racingGameWithId;
        }

        racingCarGameDao.updateGame(RacingGameMapper.mapToGame(racingGame));
        insertPlayers(RacingGameMapper.mapToPlayers(racingGame));

        return racingGame;
    }

    private void insertPlayers(List<Player> players) {
        for (Player player : players) {
            racingCarGameDao.insertPlayer(player);
        }
    }
}
