package racingcar.consolegame.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.dao.PlayResult;
import racingcar.dao.PlayResultDao;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public class PlayResultInMemoryDao implements PlayResultDao {

    private final List<PlayResult> playResults = new ArrayList<>();
    private int id = 1;

    @Override
    public int insertPlayResult(final PlayResult playResult) {
        playResult.setId(id++);
        playResults.add(playResult);
        return id;
    }

    @Override
    public List<PlayResult> selectAllResults() {
        return playResults;
    }

}
