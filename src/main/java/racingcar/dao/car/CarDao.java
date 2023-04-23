package racingcar.dao.car;

import racingcar.dto.CarDto;

import java.util.List;

public interface CarDao {
    void save(CarDto carDto);
    
    long findIdByGameIdAndName(long gameId, String name);
    
    List<CarDto> findCarDtosByCarIds(List<Long> gameIds);
    
    List<CarDto> findCarDtosByGameId(long gameId);
}
