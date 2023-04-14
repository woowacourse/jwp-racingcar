package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultDto;

@Repository
public interface GameRepository {

    void save(GameResultDto gameResultDto);
}
