package racingcar.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import racingcar.dto.response.RacingGameWinnersDto;

import javax.sql.DataSource;

@JdbcTest
class RacingGameDaoTest {

    @Autowired
    private DataSource dataSource;

    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameDao(dataSource);
    }

    @DisplayName("자동차 게임 정보를 저장한다.")
    @Test
    void save() {
        final Long id = racingGameDao.save("루쿠", 10);

        assertThat(id).isNotNull();
    }

    @Test
    @DisplayName("자동차 게임의 모든 id와 승리자를 가져온다.")
    void findWinners() {
        final Long id1 = racingGameDao.save("루쿠", 10);
        final Long id2 = racingGameDao.save("다즐", 10);
        List<RacingGameWinnersDto> winnersDtos = racingGameDao.findAllWinners();

        assertThat(winnersDtos.get(0).getId()).isEqualTo(id1);
        assertThat(winnersDtos.get(0).getWinners()).isEqualTo("루쿠");
        assertThat(winnersDtos.get(1).getId()).isEqualTo(id2);
        assertThat(winnersDtos.get(1).getWinners()).isEqualTo("다즐");
    }
}
