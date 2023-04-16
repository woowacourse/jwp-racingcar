package racingcar.dao;

import racingcar.dto.GameResultDto;
import racingcar.dto.GameWinnerDto;

import java.util.List;

public interface GameResultDao {

    int save(GameResultDto resultDto);

    List<GameWinnerDto> selectAllGame();

    void deleteAll();
}
