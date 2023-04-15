package racingcar.service;

import racingcar.dao.ResultCarDao;
import racingcar.dto.CarDto;

import java.util.List;

class StubResultCarDao implements ResultCarDao {
    @Override
    public void save(int gameId, List<CarDto> carDtoList) {
    }
}
