package racingcar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.PlayerSaveDto;

import java.util.List;

@Repository
@Transactional
public class JdbcRacingGameRepository implements RacingGameRepository {

    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public JdbcRacingGameRepository(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    @Override
    public void save(final int trialCount, final List<PlayerSaveDto> playerSaveDtos) {
        final Long gameId = gameDao.save(trialCount);
        playerDao.save(gameId, playerSaveDtos);
    }

}
