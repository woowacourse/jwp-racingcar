package racingcar.dao;

import racingcar.domain.Car;
import racingcar.dto.GameResultDto;

import java.util.List;

public final class MemoryRacingCarDao implements RacingCarDao {
    private String winners;
    private List<Car> cars;

    @Override
    public long saveWinners(final int count, final String winners) {
        this.winners = winners;
        return 1L;
    }

    @Override
    public void saveCars(final Number resultId, final List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public List<GameResultDto> findAllResult() {
        return List.of(new GameResultDto(1L, winners));
    }

    @Override
    public List<Car> findCarsByResultId(final long resultId) {
        return cars;
    }
}
