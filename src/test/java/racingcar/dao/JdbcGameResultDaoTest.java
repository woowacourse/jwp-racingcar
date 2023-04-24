package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.GameResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class JdbcGameResultDaoTest {
    private final JdbcTemplate jdbcTemplate;
    private JdbcGameResultDao webPlayResultDao;

    @Autowired
    public JdbcGameResultDaoTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    public void setUp() {
        this.webPlayResultDao = new JdbcGameResultDao(jdbcTemplate);
    }

    @Test
    void save_메서드는_데이터베이스에_게임결과를_저장() {
        GameResult gameResult = new GameResult(10, Timestamp.valueOf(LocalDateTime.now()));
        long id = webPlayResultDao.save(gameResult);
        GameResult result = webPlayResultDao.findById(id);
        assertThat(result).isNotNull();
    }

    @Test
    void findById_메서드는_특정Id의_게임결과를_반환() {
        GameResult gameResult = new GameResult(10, Timestamp.valueOf("2023-04-19 00:00:00"));
        long id = webPlayResultDao.save(gameResult);
        GameResult savedResult = webPlayResultDao.findById(id);
        assertAll(
                () -> assertThat(savedResult.getId()).isEqualTo(id),
                () -> assertThat(savedResult.getTrialCount()).isEqualTo(gameResult.getTrialCount()),
                () -> assertThat(savedResult.getCreatedAt()).isEqualTo(gameResult.getCreatedAt())
        );
    }

    @Test
    void findAll_메서드는_모든_게임결과를_반환() {
        GameResult gameResult1 = new GameResult(10, Timestamp.valueOf("2023-04-19 00:00:00"));
        GameResult gameResult2 = new GameResult(15, Timestamp.valueOf("2023-04-19 00:00:00"));
        long playResult1Id = webPlayResultDao.save(gameResult1);
        long playResult2Id = webPlayResultDao.save(gameResult2);

        List<GameResult> savedGameResults = webPlayResultDao.findAll();
        assertAll(
                () -> assertThat(savedGameResults.get(0).getId()).isEqualTo(playResult1Id),
                () -> assertThat(savedGameResults.get(1).getId()).isEqualTo(playResult2Id)
        );
    }
}
