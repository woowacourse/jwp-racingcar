package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.PlayResultResponseDto;

import java.util.List;
import java.util.Map;

@Repository
public interface PlayerDao {
    void insert(int gameId, List<Car> cars);

    List<Car> find(int gameId);

    Map<Integer, PlayResultResponseDto> findAll();
}
