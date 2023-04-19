package racingcar.consolegame.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.dao.PlayerResult;
import racingcar.dao.PlayerResultDao;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public class PlayerResultInMemoryDao implements PlayerResultDao {

    private final List<PlayerResult> playerResults = new ArrayList<>();
    private int id = 1;

    @Override
    public void insertPlayerResult(final PlayerResult playerResult) {
        playerResult.setId(id++);
        playerResults.add(playerResult);
    }

    @Override
    public List<PlayerResult> selectPlayerResult(final int playResultId) {
        return playerResults;
    }
}
