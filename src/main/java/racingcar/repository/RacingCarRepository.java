package racingcar.repository;

import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

import java.util.List;
import java.util.Map;

public interface RacingCarRepository {
    void saveWinners(int gameId, List<String> winners);
    void saveCars(int gameId, List<Car> cars);
    int saveGame(int count);
    List<String> findWinnersByGameId(int gameId);
    List<RacingCarDto> findRacingCarsByGameId(int gameId);
    Map<Integer, List<String>> findWinners();
    Map<Integer, List<RacingCarDto>> findRacingCars();
}
