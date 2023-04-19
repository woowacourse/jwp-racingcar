package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.PlayResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class WebPlayResultDaoTest {
    private final JdbcTemplate jdbcTemplate;
    private WebPlayResultDao webPlayResultDao;

    @Autowired
    public WebPlayResultDaoTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    public void setUp() {
        this.webPlayResultDao = new WebPlayResultDao(jdbcTemplate);
    }

    @Test
    void save_메서드는_데이터베이스에_게임결과를_저장() {
        PlayResult playResult = PlayResult.of(10, "hongo", Timestamp.valueOf(LocalDateTime.now()));
        int id = webPlayResultDao.save(playResult);
        PlayResult result = webPlayResultDao.findById(id);
        assertThat(result).isNotNull();
    }

    @Test
    void findById_메서드는_특정Id의_게임결과를_반환() {
        PlayResult playResult = PlayResult.of(10, "hongo", Timestamp.valueOf("2023-04-19 00:00:00"));
        int id = webPlayResultDao.save(playResult);
        PlayResult savedResult = webPlayResultDao.findById(id);
        assertAll(
                () -> assertThat(savedResult.getId()).isEqualTo(id),
                () -> assertThat(savedResult.getWinners()).isEqualTo(playResult.getWinners()),
                () -> assertThat(savedResult.getTrialCount()).isEqualTo(playResult.getTrialCount()),
                () -> assertThat(savedResult.getCreatedAt()).isEqualTo(playResult.getCreatedAt())
        );
    }

    @Test
    void findAll_메서드는_모든_게임결과를_반환() {
        PlayResult playResult1 = PlayResult.of(10, "juno", Timestamp.valueOf("2023-04-19 00:00:00"));
        PlayResult playResult2 = PlayResult.of(15, "hongo", Timestamp.valueOf("2023-04-19 00:00:00"));
        int playResult1Id = webPlayResultDao.save(playResult1);
        int playResult2Id = webPlayResultDao.save(playResult2);

        List<PlayResult> savedPlayResults = webPlayResultDao.findAll();
        assertAll(
                () -> assertThat(savedPlayResults.get(0).getId()).isEqualTo(playResult1Id),
                () -> assertThat(savedPlayResults.get(1).getId()).isEqualTo(playResult2Id)
        );
    }
}
