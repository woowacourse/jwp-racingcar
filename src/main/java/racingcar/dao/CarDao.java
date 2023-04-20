package racingcar.dao;

import racingcar.dto.CarNameDTO;
import racingcar.dto.CarNamePositionDTO;

import java.util.List;

public interface CarDao {

    int insert(final String name, final int position, final Long gameId, final boolean isWin);

    int countRows();

    void deleteAll();

    List<CarNameDTO> findWinners(final Long gameId);

    List<CarNamePositionDTO> findAllCarNamesAndPositions(final Long gameId);
}
