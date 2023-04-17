package racingcar.dao;

import java.util.List;
import racingcar.dao.entity.CarEntity;
import racingcar.dto.CarDTO;

public interface CarDao {

    void batchInsert(final List<CarEntity> carEntity);

    List<CarDTO> selectAll(final int gameId);

    List<String> selectWinners(final int gameId);
}
