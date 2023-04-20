package racingcar.dao;

import racingcar.domain.Car;
import racingcar.dto.PlayResultResponseDto;

import java.util.List;
import java.util.Map;

public class InMemoryPlayerDao implements PlayerDao {
    @Override
    public void insert(int gameId, List<Car> cars) {

    }

    @Override
    public List<Car> find(int gameId) {
        return null;
    }

    @Override
    public Map<Integer, PlayResultResponseDto> findAll() {
        return null;
    }
}
