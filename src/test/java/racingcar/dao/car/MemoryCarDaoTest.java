package racingcar.dao.car;

import org.junit.jupiter.api.*;
import racingcar.domain.car.Car;
import racingcar.domain.car.Name;
import racingcar.dto.CarDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemoryCarDaoTest {
    private MemoryCarDao carDao;
    
    @BeforeEach
    void setUp() {
        // given
        carDao = new MemoryCarDao();
        carDao.save(new CarDto(1L, new Car(new Name("아벨"))));
        carDao.save(new CarDto(1L, new Car(new Name("스플릿"))));
        carDao.save(new CarDto(1L, new Car(new Name("포비"))));
        carDao.save(new CarDto(2L, new Car(new Name("포비"))));
        carDao.save(new CarDto(2L, new Car(new Name("아벨"))));
    }
    
    @Test
    void GameId와_Name을_전달하면_carId를_반환한다() {
        // when
        final long carId = carDao.findIdByGameIdAndName(1L, "포비");
        
        // then
        assertThat(carId).isEqualTo(3L);
    }
    
    @Test
    void CarIds를_전달하면_CarDtos를_전달한다() {
        // given
        final CarDto expectedSplitDto = new CarDto(1L, new Car(new Name("스플릿")));
        final CarDto expectedPobiDto = new CarDto(2L, new Car(new Name("포비")));
        
        // when
        final List<CarDto> carDtos = carDao.findCarDtosByCarIds(List.of(2L, 4L));
        
        // then
        assertThat(carDtos).containsExactly(expectedSplitDto, expectedPobiDto);
    }
    
    @Test
    void GameId를_전달하면_CarDtos를_전달한다() {
        // given
        final long gameId = 2L;
        
        // when
        final List<CarDto> carDtos = carDao.findCarDtosByGameId(gameId);
        
        // then
        final CarDto pobiDto = new CarDto(2L, new Car(new Name("포비")));
        final CarDto abelDto = new CarDto(2L, new Car(new Name("아벨")));
        assertThat(carDtos).containsExactly(pobiDto, abelDto);
    }
    
    @AfterEach
    void tearDown() {
        carDao.deleteAll();
    }
}
