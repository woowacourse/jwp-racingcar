package racingcar.mapper.console;

import racingcar.entity.CarEntity;
import racingcar.mapper.CarMapper;

import java.util.List;

public class ConsoleCarMapper implements CarMapper {
    @Override
    public CarEntity save(CarEntity entity) {
        return null;
    }

    @Override
    public CarEntity findById(long id) {
        return null;
    }

    @Override
    public List<CarEntity> findAllByGameId(long id) {
        return null;
    }

    @Override
    public List<CarEntity> findWinnersByGameId(long id) {
        return null;
    }
}
