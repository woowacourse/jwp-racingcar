package racingcar.dao;

import racingcar.dto.CarSavingDto;

public interface CarInfoDao {
    int saveCar(CarSavingDto carSavingDto);
}
