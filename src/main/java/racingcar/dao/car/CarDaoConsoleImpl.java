package racingcar.dao.car;

import racingcar.dto.db.CarDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarDaoConsoleImpl implements CarDao {

    private Long id = 0L;
    private final Map<Long, CarDto> carMap = new HashMap<>();


    @Override
    public Long save(final CarDto dto) {
        carMap.put(++id, dto);
        return id;
    }

    @Override
    public void saveAll(final List<CarDto> dtos) {
        for (CarDto dto : dtos) {
            save(dto);
        }
    }
}
