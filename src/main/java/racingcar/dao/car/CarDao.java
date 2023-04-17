package racingcar.dao.car;

import racingcar.dto.db.CarDto;

import java.util.List;

public interface CarDao {

    Long save(final CarDto dto);

    void saveAll(final List<CarDto> dtos);
}
