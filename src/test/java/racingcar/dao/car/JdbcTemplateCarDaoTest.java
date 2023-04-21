package racingcar.dao.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.dao.game.GameDao;
import racingcar.dao.game.JdbcTemplateGameDao;
import racingcar.domain.car.Car;
import racingcar.domain.car.Name;
import racingcar.domain.car.Position;
import racingcar.domain.racinggame.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameDto;
import racingcar.dto.WinnerDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@JdbcTest
class JdbcTemplateCarDaoTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private CarDao carDao;
    private GameDao gameDao;
    
    @BeforeEach
    void setUp() {
        // given
        namedParameterJdbcTemplate.getJdbcTemplate().execute("ALTER TABLE GAME ALTER COLUMN id RESTART WITH 1");
        namedParameterJdbcTemplate.getJdbcTemplate().execute("ALTER TABLE CAR ALTER COLUMN id RESTART WITH 1");
        
        this.gameDao = new JdbcTemplateGameDao(namedParameterJdbcTemplate);
        gameDao.save(new GameDto(new RacingGame("아벨", 0)));
        gameDao.save(new GameDto(new RacingGame("아벨", 0)));
        
        this.carDao = new JdbcTemplateCarDao(namedParameterJdbcTemplate);
        carDao.save(new CarDto(1L, new Car(new Name("아벨"), new Position(3))));
        carDao.save(new CarDto(1L, new Car(new Name("스플릿"), new Position(3))));
        carDao.save(new CarDto(1L, new Car(new Name("포비"), new Position(2))));
        carDao.save(new CarDto(2L, new Car(new Name("아벨"), new Position(3))));
        carDao.save(new CarDto(2L, new Car(new Name("스플릿"), new Position(2))));
    }
    
    @Test
    void CarDto를_전달하면_Car의_id를_반환한다() {
        // given
        final CarDto carDto = new CarDto(2L, new Car(new Name("아벨"), new Position(3)));
        
        // when
        final long carId = carDao.findIdByCarDto(carDto);
        
        // then
        assertThat(carId).isEqualTo(4L);
    }
    
    @Test
    void WinnerDtos를_전달하면_CarDtosfmf_반환한다() {
        // given
        final List<WinnerDto> winnerDtos = List.of(new WinnerDto(1L, 3L), new WinnerDto(1L, 4L));
        
        // when
        final List<CarDto> carDtos = carDao.findCarDtosByWinnerDtos(winnerDtos);
        
        // then
        final CarDto expectedFirstCarDto = new CarDto(1L, new Car(new Name("포비"), new Position(2)));
        final CarDto expectedSecondCarDto = new CarDto(2L, new Car(new Name("아벨"), new Position(3)));
        assertThat(carDtos).containsExactly(expectedFirstCarDto, expectedSecondCarDto);
    }
    
    @Test
    void GameId를_전달하면_CarDtos를_반환한다() {
        // when
        final List<CarDto> carDtos = carDao.findCarDtosByGameId(2L);
        
        // then
        final CarDto expectedFirstCarDto = new CarDto(2L, new Car(new Name("아벨"), new Position(3)));
        final CarDto expectedSecondCarDto = new CarDto(2L, new Car(new Name("스플릿"), new Position(2)));
        assertThat(carDtos).containsExactly(expectedFirstCarDto, expectedSecondCarDto);
    }
}
