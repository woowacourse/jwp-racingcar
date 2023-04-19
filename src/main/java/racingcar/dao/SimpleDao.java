package racingcar.dao;

import java.util.List;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

public interface SimpleDao {
    void save(final CarDto carDto);

    Integer save(final TrackDto trackDto);

    List<CarDto> findAll();
}
