package racingcar.dao.winner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.dao.car.CarDao;
import racingcar.dao.car.JdbcTemplateCarDao;
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

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@JdbcTest
class JdbcTemplateWinnerDaoTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private WinnerDao winnerDao;
    private CarDao carDao;
    private GameDao gameDao;
    
    @BeforeEach
    void setUp() {
        // given
        namedParameterJdbcTemplate.getJdbcTemplate().execute("ALTER TABLE WINNER ALTER COLUMN id RESTART WITH 1");
        namedParameterJdbcTemplate.getJdbcTemplate().execute("ALTER TABLE GAME ALTER COLUMN id RESTART WITH 1");
        namedParameterJdbcTemplate.getJdbcTemplate().execute("ALTER TABLE CAR ALTER COLUMN id RESTART WITH 1");
        
        this.gameDao = new JdbcTemplateGameDao(namedParameterJdbcTemplate);
        gameDao.save(new GameDto(new RacingGame("아벨", 0)));
        gameDao.save(new GameDto(new RacingGame("아벨", 0)));
        gameDao.save(new GameDto(new RacingGame("아벨", 0)));
        
        this.carDao = new JdbcTemplateCarDao(namedParameterJdbcTemplate);
        carDao.save(new CarDto(1L, new Car(new Name("아벨"), new Position(3))));;
        carDao.save(new CarDto(1L, new Car(new Name("스플릿"), new Position(3))));;
        carDao.save(new CarDto(1L, new Car(new Name("포비"), new Position(3))));;
        carDao.save(new CarDto(1L, new Car(new Name("향로"), new Position(3))));;
        carDao.save(new CarDto(2L, new Car(new Name("아벨"), new Position(3))));;
        carDao.save(new CarDto(2L, new Car(new Name("스플릿"), new Position(3))));;
        carDao.save(new CarDto(3L, new Car(new Name("포비"), new Position(3))));;
        
        this.winnerDao = new JdbcTemplateWinnerDao(namedParameterJdbcTemplate);
        winnerDao.save(new WinnerDto(1L, 1L));
        winnerDao.save(new WinnerDto(1L, 2L));
        winnerDao.save(new WinnerDto(1L, 3L));
        winnerDao.save(new WinnerDto(2L, 6L));
        winnerDao.save(new WinnerDto(2L, 7L));
    }
    
    @Test
    void GameId를_반환하면_WinnerDtos를_반환한다() {
        // when
        final List<WinnerDto> winnerDtos = winnerDao.findWinnerDtosByGameId(2L);
        
        // then
        final WinnerDto expectedFirstWinnerDto = new WinnerDto(2L, 6L);
        final WinnerDto expectedSecondWinnerDto = new WinnerDto(2L, 7L);
        assertThat(winnerDtos).containsExactly(expectedFirstWinnerDto, expectedSecondWinnerDto);
    }
}
