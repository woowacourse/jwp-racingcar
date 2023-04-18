package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.RacingGameEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class RacingGameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameDao(jdbcTemplate);
    }

    @DisplayName("자동차 게임 정보를 저장한다.")
    @Test
    void save() {
        final RacingGameEntity racingGameEntity = RacingGameEntity.builder()
                .trialCount(10)
                .build();

        final Long id = racingGameDao.save(racingGameEntity);

        assertThat(id).isNotNull();
    }

    @DisplayName("모든 자동차 게임 정보를 조회한다.")
    @Test
    void findAll() {
        final RacingGameEntity firstRacingGameEntity = RacingGameEntity.builder()
                .trialCount(10)
                .build();
        final RacingGameEntity secondRacingGameEntity = RacingGameEntity.builder()
                .trialCount(7)
                .build();
        racingGameDao.save(firstRacingGameEntity);
        racingGameDao.save(secondRacingGameEntity);

        final List<RacingGameEntity> racingGameEntities = racingGameDao.findAll();

        assertAll(
                () -> assertThat(racingGameEntities).hasSize(2),
                () -> assertThat(racingGameEntities.get(0).getTrialCount()).isEqualTo(10),
                () -> assertThat(racingGameEntities.get(1).getTrialCount()).isEqualTo(7)
        );
    }
}
