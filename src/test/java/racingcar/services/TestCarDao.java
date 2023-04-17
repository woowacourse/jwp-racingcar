package racingcar.services;

import java.util.List;

import racingcar.dao.CarDao;
import racingcar.dto.CarDto;

public class TestCarDao implements CarDao {
    @Override
    public void insertCar(List<CarDto> carDtos, long gameId) {

    }

    @Override
    public List<CarDto> findCarsInfoByGameId(Long gameId) {
        return List.of(
                new CarDto("폴로", 4),
                new CarDto("이리내", 6)
        );
    }
}
