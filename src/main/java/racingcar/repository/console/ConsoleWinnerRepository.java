package racingcar.repository.console;

import racingcar.entity.CarEntity;
import racingcar.repository.WinnerRepository;

import java.util.List;

public class ConsoleWinnerRepository implements WinnerRepository {
    @Override
    public CarEntity save(CarEntity entity) {
        return null;
    }

    @Override
    public List<CarEntity> findWinnersByGameId(long id) {
        return null;
    }
}
