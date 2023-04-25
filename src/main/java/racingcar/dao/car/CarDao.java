package racingcar.dao.car;

import racingcar.dto.CarDto;

import java.util.List;

public interface CarDao {
    void save(final CarDto carDto);
    
    long findIdByGameIdAndName(final long gameId, final String name);
    
    List<CarDto> findCarDtosByCarIds(final List<Long> gameIds);
    
    List<CarDto> findCarDtosByGameId(final long gameId);
}
