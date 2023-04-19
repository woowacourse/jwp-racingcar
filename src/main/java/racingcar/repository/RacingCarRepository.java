package racingcar.repository;

import java.util.List;
import java.util.Map;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

public interface RacingCarRepository {
    void saveWinner(int gameId, List<String> winners);

    void saveCars(int gameId, List<Car> cars);

    int saveGame(int count);

    List<String> findWinners(int gameId);

    List<RacingCarDto> findRacingCars(int gameId);

    Map<Integer, List<RacingCarDto>> findAllRacingCars();

    Map<Integer, List<String>> findAllWinners();
}
