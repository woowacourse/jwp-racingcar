package racingcar.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultDto;

@Repository
public interface GameRepository {

    void save(GameResultDto gameResultDto);

    List<GameResultDto> findAllResult();
}
