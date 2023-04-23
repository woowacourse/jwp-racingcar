package racingcar.repository;

import java.util.List;
import racingcar.service.dto.GameHistoryDto;
import racingcar.service.dto.RacingCarDto;

public interface RacingGameRepository {

    List<GameHistoryDto> findGameHistories();

    void saveGameResult(List<String> winners, List<RacingCarDto> racingCars);
}
