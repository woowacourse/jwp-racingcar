package racingcar.repository;

import java.util.List;

import racingcar.domain.Cars;
import racingcar.dto.ResultDto;

public interface RacingCarRepository {
	void save(Cars cars, int count);

	List<ResultDto> find();
}
