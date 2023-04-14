package racingcar.dao;

import racingcar.dto.CarResultDto;

import java.util.List;

public interface CarResultDao {

    void save(int gameId, List<CarResultDto> carResultDtos);

    List<CarResultDto> findAll();
}
