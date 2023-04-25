package racingcar.repository.console;

import java.util.List;

import racingcar.domain.Cars;
import racingcar.dto.ResultDto;
import racingcar.repository.RacingCarRepository;

public class ConsoleRacingCarRepository implements RacingCarRepository {
	@Override
	public void save(Cars cars, int count) {
	}

	@Override
	public List<ResultDto> find() {
		return null;
	}
}
