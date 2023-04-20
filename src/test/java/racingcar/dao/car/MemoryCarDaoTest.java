package racingcar.dao.car;

import org.junit.jupiter.api.*;
import racingcar.domain.car.Car;
import racingcar.domain.car.Name;
import racingcar.dto.CarDto;
import racingcar.dto.WinnerDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemoryCarDaoTest {
    private MemoryCarDao carDao;
    
    @BeforeEach
    void setUp() {
        carDao = new MemoryCarDao();
        carDao.insert(new CarDto(1L, new Car(new Name("아벨"))));
        carDao.insert(new CarDto(1L, new Car(new Name("스플릿"))));
        carDao.insert(new CarDto(1L, new Car(new Name("포비"))));
        carDao.insert(new CarDto(2L, new Car(new Name("포비"))));
        carDao.insert(new CarDto(2L, new Car(new Name("아벨"))));
    }
    
    @Test
    void CarDto를_전달하면_carId를_반환한다() {
        // given
        final CarDto carDto = new CarDto(1L, new Car(new Name("포비")));
        
        // when
        final long carId = carDao.findIdByCarDto(carDto);
        
        // then
        assertThat(carId).isEqualTo(3L);
    }
    
    @Test
    void WinnerDtos를_전달하면_CarDtos를_전달한다() {
        // given
        final List<WinnerDto> winnerDtos = List.of(new WinnerDto(1L, 2L), new WinnerDto(1L, 4L));
        final CarDto expectedSplitDto = new CarDto(1L, new Car(new Name("스플릿")));
        final CarDto expectedPobiDto = new CarDto(2L, new Car(new Name("포비")));
        
        // when
        final List<CarDto> carDtos = carDao.findCarDtosByWinnerDtos(winnerDtos);
        
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
