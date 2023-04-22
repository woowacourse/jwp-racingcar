package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.RacingCarEntity;
import racingcar.entity.RacingGameEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class RacingCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RacingCarDao racingCarDao;
    private Long gameId;

    @BeforeEach
    void setUp() {
        final RacingGameDao racingGameDao = new RacingGameDao(jdbcTemplate);
        final RacingGameEntity racingGameEntity = RacingGameEntity.builder()
                .trialCount(10)
                .build();
        gameId = racingGameDao.save(racingGameEntity);
        racingCarDao = new RacingCarDao(jdbcTemplate);
    }

    @DisplayName("자동차 정보를 저장한다.")
    @Test
    void save() {
        final RacingCarEntity racingCarEntity = RacingCarEntity.builder()
                .gameId(gameId)
                .name("다즐")
                .position(0)
                .build();

        assertThatNoException()
                .isThrownBy(() -> racingCarDao.saveAll(List.of(racingCarEntity)));
    }

    @DisplayName("모든 자동차 정보를 조회한다.")
    @Test
    void findAllByGameId() {
        final RacingCarEntity firstRacingCarEntity = RacingCarEntity.builder()
                .gameId(gameId)
                .name("다즐")
                .position(3)
                .build();
        final RacingCarEntity secondRacingCarEntity = RacingCarEntity.builder()
                .gameId(gameId)
                .name("오즈")
                .position(5)
                .build();
        racingCarDao.saveAll(List.of(firstRacingCarEntity, secondRacingCarEntity));

        final List<RacingCarEntity> racingCarEntities = racingCarDao.findAllByGameId(gameId);

        assertAll(
                () -> assertThat(racingCarEntities).hasSize(2),
                () -> assertThat(racingCarEntities.get(0).getGameId()).isEqualTo(gameId),
                () -> assertThat(racingCarEntities.get(0).getName()).isEqualTo("다즐"),
                () -> assertThat(racingCarEntities.get(0).getPosition()).isEqualTo(3),
                () -> assertThat(racingCarEntities.get(1).getGameId()).isEqualTo(gameId),
                () -> assertThat(racingCarEntities.get(1).getName()).isEqualTo("오즈"),
                () -> assertThat(racingCarEntities.get(1).getPosition()).isEqualTo(5)
        );
    }
}
