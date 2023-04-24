package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;
import racingcar.dto.WinnerDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class DummyRacingGameRepository implements RacingGameRepository {
    @Override
    public Long save(final RacingGame racingGame) {
        return null;
    }

    @Override
    public Optional<RacingGameDto> findById(final Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, List<CarDto>> findAllCars() {
        return null;
    }

    @Override
    public Map<Long, List<WinnerDto>> findAllWinners() {
        return null;
    }
}
