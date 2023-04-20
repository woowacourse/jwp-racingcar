package racingcar.service;

import racingcar.domain.strategy.move.MoveStrategy;
import racingcar.dto.GameInputDto;
import racingcar.dto.GameResultDto;

import java.util.List;

public interface RacingCarService {
    void playGame(GameInputDto gameInputDto, MoveStrategy moveStrategy);
    
    List<GameResultDto> findAllGameResult();
}
