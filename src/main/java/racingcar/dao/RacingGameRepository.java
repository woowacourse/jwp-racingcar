package racingcar.dao;

import racingcar.dto.GameResultDto;
import racingcar.dto.response.GameResponseDto;

import java.util.List;

public interface RacingGameRepository {
    void saveGame(GameResultDto resultDto);
    List<GameResponseDto> findAllGame();
}
