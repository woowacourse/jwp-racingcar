package racingcar.service;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Count;
import racingcar.dto.RacingGameDto;
import racingcar.repository.RacingGameRepository;

public class StubRacingGameRepository implements RacingGameRepository {

    @Override
    public RacingGameDto save(final RacingGameDto racingGameDto) {
        return racingGameDto;
    }

    @Override
    public List<RacingGameDto> findAll() {
        final List<Car> cars = List.of(new Car("브리", 9));

        return List.of(new RacingGameDto(cars, cars, new Count(10)));
    }
}
