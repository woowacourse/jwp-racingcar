package racingcar.dao.car;

import racingcar.dto.CarDto;
import racingcar.dto.WinnerDto;

import java.util.List;

public interface CarDao {
    void save(CarDto carDto);
    
    long findIdByCarDto(CarDto carDto);
    
    List<CarDto> findCarDtosByWinnerDtos(List<WinnerDto> winnerDtos);
    
    List<CarDto> findCarDtosByGameId(long gameId);
}
