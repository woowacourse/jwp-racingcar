package racingcar.db;

import racingcar.dto.GameResultDto;
import racingcar.dto.response.GameResponse;

import java.util.List;

public interface RacingGameRepository {
    void saveGame(GameResultDto resultDto);
    List<GameResponse> findAllGame();

    void deleteAll();
}
