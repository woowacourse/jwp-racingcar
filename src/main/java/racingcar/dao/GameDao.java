package racingcar.dao;

import racingcar.dto.GameIdDTO;

import java.util.List;

public interface GameDao {
    Long insert(final int count);

    int countRows();

    void deleteAll();

    List<GameIdDTO> findAllGameIds();
}
