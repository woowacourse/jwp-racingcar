package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingDaoTest {

    @Autowired
    RacingWebDao racingDao;

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
