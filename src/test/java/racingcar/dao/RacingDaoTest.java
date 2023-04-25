package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

class RacingDaoTest {

    SimpleDao racingDao = new RacingConsoleDao();

    @Test
    void createTrack() {
        final TrackDto trackDto = new TrackDto(5);

        final Integer savedId = racingDao.save(trackDto);

        assertThat(savedId).isNotNull();
    }

    @Test
    void createCar() {
        final TrackDto trackDto = new TrackDto(5);
        final Integer savedId = racingDao.save(trackDto);

        final CarDto carDto = new CarDto("pobi", 5, true, savedId);

        assertDoesNotThrow(() -> racingDao.save(carDto));
    }
}
