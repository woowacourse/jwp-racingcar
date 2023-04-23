package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.JdbcCarDao;
import racingcar.dao.JdbcRacingGameDao;
import racingcar.domain.entity.CarEntity;
import racingcar.domain.entity.RacingGameEntity;

@JdbcTest
class WebRacingCarRepositoryTest {

    private DaoRacingCarRepository webRacingCarRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        webRacingCarRepository = new DaoRacingCarRepository(new JdbcRacingGameDao(jdbcTemplate),
                new JdbcCarDao(jdbcTemplate));
    }

    @Test
    @DisplayName("저장된 모든 entity를 찾아온다.")
    void find_all() {
        //given
        List<CarEntity> carEntities = List.of(new CarEntity("현서", 10, true),
                new CarEntity("참치", 2, false));
        RacingGameEntity racingGameEntity = new RacingGameEntity(carEntities, 10);
        webRacingCarRepository.save(racingGameEntity);
        //when
        List<RacingGameEntity> findEntity = webRacingCarRepository.findAll();
        RacingGameEntity actual = findEntity.get(0);
        //then
        assertThat(findEntity.size()).isEqualTo(1);
        assertThat(actual.getCarEntities().size()).isEqualTo(2);
        assertThat(actual.getId()).isPositive();
    }
}
