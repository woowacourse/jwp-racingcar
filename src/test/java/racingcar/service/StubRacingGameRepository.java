package racingcar.service;

import java.util.List;
import racingcar.dao.entity.InsertGameEntity;
import racingcar.domain.Car;
import racingcar.domain.Count;
import racingcar.domain.RacingGameResult;
import racingcar.repository.RacingGameRepository;

public class StubRacingGameRepository implements RacingGameRepository {

    @Override
    public InsertGameEntity save(final RacingGameResult racingGameResult) {
        return new InsertGameEntity(1, racingGameResult);
    }

    @Override
    public List<RacingGameResult> findAll() {
        final List<Car> cars = List.of(new Car("브리", 9));

        return List.of(new RacingGameResult(cars, cars, new Count(10)));
    }
}
