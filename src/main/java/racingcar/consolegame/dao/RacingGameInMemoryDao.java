package racingcar.consolegame.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.dao.RacingGameDao;
import racingcar.dao.RacingGameEntity;
import racingcar.dto.RacingGameDto;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public class RacingGameInMemoryDao implements RacingGameDao {

    private final List<RacingGameEntity> racingGames = new ArrayList<>();
    private int id = 1;

    @Override
    public RacingGameEntity insertRacingGame(final RacingGameDto racingGameDto) {
        RacingGameEntity racingGameEntity = new RacingGameEntity(id, racingGameDto.getTrialCount());
        racingGames.add(racingGameEntity);
        return new RacingGameEntity(id++, racingGameDto.getTrialCount());
    }

    @Override
    public List<RacingGameEntity> selectAllResults() {
        return racingGames;
    }

}
