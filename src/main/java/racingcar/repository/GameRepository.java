package racingcar.repository;

import java.util.List;
import racingcar.dto.GameResultDto;

public interface GameRepository {

    void save(GameResultDto gameResultDto);

    List<GameResultDto> findAllResult();
}
