package racingcar.repository;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.service.dto.GameHistoryDto;

public interface RacingGameRepository {

    List<GameHistoryDto> findGameHistories();

    void saveGameResult(final Cars cars);
}
