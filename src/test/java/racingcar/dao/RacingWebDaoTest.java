package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingWebDaoTest {

    @Autowired
    RacingWebDao racingWebDao;

    @Test
    void createTrack() {
        final TrackDto trackDto = new TrackDto(5);

        final Integer savedId = racingWebDao.saveTrack(trackDto);

        assertThat(savedId).isNotNull();
    }

    @Test
    void createCar() {
        final TrackDto trackDto = new TrackDto(5);
        final Integer savedId = racingWebDao.saveTrack(trackDto);

        final CarDto carDto = new CarDto("pobi", 5, true, savedId);

        assertDoesNotThrow(() -> racingWebDao.saveCar(carDto));
    }

    @Test
    void createCarsWithBatch() {
        final TrackDto trackDto = new TrackDto(5);
        final Integer savedId = racingWebDao.saveTrack(trackDto);

        final CarDto carDto1 = new CarDto("gray", 5, true, savedId);
        final CarDto carDto2 = new CarDto("logun", 3, false, savedId);
        final CarDto carDto3 = new CarDto("hoy", 2, false, savedId);
        List<CarDto> carDtos = List.of(carDto1, carDto2, carDto3);

        assertDoesNotThrow(() -> racingWebDao.saveWithBatch(carDtos));
    }

    @Test
    void findAllCarsWithTrackId() {
        final TrackDto trackDto = new TrackDto(5);
        final Integer savedId = racingWebDao.saveTrack(trackDto);
        final CarDto carDto1 = new CarDto("gray", 5, true, savedId);
        final CarDto carDto2 = new CarDto("logun", 3, false, savedId);
        final CarDto carDto3 = new CarDto("hoy", 2, false, savedId);
        List<CarDto> carDtos = List.of(carDto1, carDto2, carDto3);
        racingWebDao.saveWithBatch(carDtos);

        List<CarDto> carsByTrackId = racingWebDao.findAllCarsByTrackId(savedId);

        assertThat(carsByTrackId).hasSameSizeAs(carDtos);
    }
}
