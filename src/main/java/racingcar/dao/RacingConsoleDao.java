package racingcar.dao;

import java.util.List;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

public class RacingConsoleDao implements RacingDao {
    @Override
    public void saveCar(CarDto carDto) {

    }

    @Override
    public void saveWithBatch(List<CarDto> carDtos) {

    }

    @Override
    public Integer saveTrack(TrackDto trackDto) {
        return null;
    }

    @Override
    public List<Integer> findAllTrackIds() {
        return null;
    }

    @Override
    public List<CarDto> findAllCarsByTrackId(int trackId) {
        return null;
    }
}
