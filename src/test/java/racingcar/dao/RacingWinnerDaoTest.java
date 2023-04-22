package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.RacingCarEntity;
import racingcar.entity.RacingGameEntity;
import racingcar.entity.RacingWinnerEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;

@JdbcTest
class RacingWinnerDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RacingWinnerDao racingWinnerDao;
    private Long gameId;
    private Long carId;

    @BeforeEach
    void setUp() {
        final RacingGameDao racingGameDao = new RacingGameDao(jdbcTemplate);
        final RacingCarDao racingCarDao = new RacingCarDao(jdbcTemplate);
        gameId = racingGameDao.save(
                RacingGameEntity.builder()
                        .trialCount(5)
                        .build()
        );
        racingCarDao.saveAll(List.of(
                RacingCarEntity.builder()
                        .gameId(gameId)
                        .name("오즈")
                        .position(5)
                        .build()
        ));
        final List<RacingCarEntity> racingCarEntities = racingCarDao.findAllByGameId(gameId);
        carId = racingCarEntities.get(0).getId();
        racingWinnerDao = new RacingWinnerDao(jdbcTemplate);
    }

    @DisplayName("자동차 경주 우승자를 저장한다.")
    @Test
    void save() {
        final RacingWinnerEntity racingWinnerEntity = RacingWinnerEntity.builder()
                .gameId(gameId)
                .carId(carId)
                .build();

        assertThatNoException()
                .isThrownBy(() -> racingWinnerDao.saveAll(List.of(racingWinnerEntity)));
    }
}
