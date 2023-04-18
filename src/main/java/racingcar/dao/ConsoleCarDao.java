package racingcar.dao;

import java.util.List;
import racingcar.dao.entity.CarEntity;
import racingcar.dto.CarDTO;

public class ConsoleCarDao implements CarDao {

    @Override
    public void batchInsert(final List<CarEntity> carEntity) {
    }

    @Override
    public List<CarDTO> selectAll(final int gameId) {
        return null;
    }

    @Override
    public List<String> selectWinners(final int gameId) {
        return null;
    }
}
