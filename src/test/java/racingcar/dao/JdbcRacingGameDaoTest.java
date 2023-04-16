package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.entity.RacingGameEntity;

@JdbcTest
class JdbcRacingGameDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    JdbcRacingGameDao jdbcRacingGameDao;

    @BeforeEach
    void setUp() {
        jdbcRacingGameDao = new JdbcRacingGameDao(jdbcTemplate);
    }

    @Test
    @DisplayName("정상적으로 game을 저장한다.")
    void save_game() {
        //when
        final int actual = jdbcRacingGameDao.save(10);
        //then
        assertThat(actual).isPositive();
    }

    @Test
    @DisplayName("저장된 게임을 모두 가져온다.")
    void find_all() {
        //given
        jdbcRacingGameDao.save(10);
        jdbcRacingGameDao.save(20);
        //when
        final List<RacingGameEntity> actual = jdbcRacingGameDao.findAll();
        //then
        assertThat(actual.size()).isEqualTo(2);
    }
}
