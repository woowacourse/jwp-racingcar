package racingcar.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GameDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private GameDao jdbcGameDao;

    @BeforeEach
    public void setUp() {
        jdbcGameDao = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    @Transactional
    void insertGameTest() {
        // given, when
        int gameId1 = jdbcGameDao.insert("jena", 3);
        int gameId2 = jdbcGameDao.insert("odo", 6);

        // then
        assertThat(gameId2 - gameId1).isEqualTo(1);
    }

    // TODO:: @transactional 이 안먹히는 거 확인 필요
//    @Test
//    @Transactional
//    void findLastIdTest() {
//        // given
//        jdbcGameDao.insert("jena2", 3);
//        jdbcGameDao.insert("odo2", 6);
//
//        // when
//        int lastGameId = jdbcGameDao.findLastId().orElseThrow(() -> new IllegalStateException("존재하는 게임 정보가 없습니다"));
//
//        // then
//        assertThat(lastGameId).isEqualTo(2);
//    }

    @Test
    @Transactional
    void findWinnersTest() {
        // given
        jdbcGameDao.insert("jena2", 3);
        jdbcGameDao.insert("odo2", 6);

        // when
        String winners = jdbcGameDao.findWinners(2);

        // then
        assertThat(winners).isEqualTo("odo2");

    }
}
