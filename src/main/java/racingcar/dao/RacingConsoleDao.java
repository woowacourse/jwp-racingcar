package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

public class RacingConsoleDao implements SimpleDao {

    private static List<CarDto> carStore = new ArrayList<>();
    private static List<TrackDto> trackStore = new ArrayList<>();

    @Override
    public void save(CarDto carDto) {
        carStore.add(carDto);
    }

    @Override
    public Integer save(TrackDto trackDto) {
        trackStore.add(trackDto);
        return trackStore.size() - 1;
    }

    @Override
    public List<CarDto> findAll() {
        return new ArrayList<>(carStore);
    }
}
