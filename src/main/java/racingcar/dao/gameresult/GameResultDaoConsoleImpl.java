package racingcar.dao.gameresult;

import racingcar.dto.db.GameResultDto;
import racingcar.dto.db.GameResultWithCarDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResultDaoConsoleImpl implements GameResultDao {

    private Long id = 0L;
    private final Map<Long, Integer> tryCountMap = new HashMap<>();

    @Override
    public Long save(final GameResultDto dto) {
        tryCountMap.put(++id, dto.getTryCount());
        return id;
    }

    @Override
    public Map<Long, List<GameResultWithCarDto>> findAll() {
        return null;
    }
}
