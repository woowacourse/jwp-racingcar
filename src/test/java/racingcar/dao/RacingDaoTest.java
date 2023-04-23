package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;
import racingcar.mapper.CarDtoMapper;
import racingcar.mapper.TrackDtoMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Import(RacingDao.class)
@JdbcTest
class RacingDaoTest {

    @Autowired
    private RacingDao racingDao;

    @Test
    void createTrack() {
        final TrackDto trackDto = TrackDtoMapper.from(5);

        final Integer savedId = racingDao.save(trackDto);

        assertThat(savedId).isNotNull();
    }

    @Test
    void createCar() {
        final TrackDto trackDto = TrackDtoMapper.from(5);
        final Integer savedId = racingDao.save(trackDto);

        final CarDto carDto = CarDtoMapper.of("pobi", 5, true, savedId);

        assertDoesNotThrow(() -> racingDao.save(carDto));
    }
}
