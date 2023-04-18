package racingcar.dao;

import java.util.List;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

public interface RacingDao {

    void saveCar(final CarDto carDto);

    void saveWithBatch(final List<CarDto> carDtos);

    Integer saveTrack(final TrackDto trackDto);

    List<Integer> findAllTrackIds();

    List<CarDto> findAllCarsByTrackId(int trackId);
}
