package racingcar.dao;

import racingcar.dto.CarSavingDto;

import java.util.ArrayList;
import java.util.List;

public class CarInfoCollectionDao implements CarInfoDao {
    private final List<CarInfo> cars = new ArrayList<>();
    private int idHolder = 0;

    @Override
    public int saveCar(CarSavingDto carSavingDto) {
        int id = idHolder++;
        cars.add(
                new CarInfo(id,
                        carSavingDto.getRacingId(),
                        carSavingDto.getName(),
                        carSavingDto.getPosition(),
                        carSavingDto.getIsWinner())
        );
        return id;
    }
}
