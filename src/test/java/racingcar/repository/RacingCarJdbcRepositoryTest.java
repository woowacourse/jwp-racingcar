package racingcar.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.RacingWinnerDao;
import racingcar.domain.*;
import racingcar.utils.TestNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@Import(value = {RacingGameDao.class, RacingCarDao.class, RacingWinnerDao.class})
@JdbcTest
class RacingCarJdbcRepositoryTest {

    @Autowired
    private RacingGameDao racingGameDao;

    @Autowired
    private RacingCarDao racingCarDao;

    @Autowired
    private RacingWinnerDao racingWinnerDao;

    private RacingGame racingGame;
    private int trialCount;

    @BeforeEach
    void setUp() {
        final NumberGenerator numberGenerator = new TestNumberGenerator(
                new ArrayList<>(Arrays.asList(3, 6, 5, 6))
        );
        final Cars cars = new Cars(List.of(new Car("다즐"), new Car("허브")));
        final int count = 2;
        racingGame = new RacingGame(numberGenerator, cars, new Count(count));
        trialCount = count;
    }

    @DisplayName("자동차 경주 게임 도메인을 저장한다.")
    @Test
    void save() {
        final RacingCarJdbcRepository racingCarJdbcRepository = new RacingCarJdbcRepository(racingGameDao, racingCarDao, racingWinnerDao);

        assertThatNoException()
                .isThrownBy(() -> racingCarJdbcRepository.save(racingGame, trialCount));
    }

    @DisplayName("자동차 경주 게임 도메인 목록을 조회한다.")
    @Test
    void findAll() {
        final RacingCarJdbcRepository racingCarJdbcRepository = new RacingCarJdbcRepository(racingGameDao, racingCarDao, racingWinnerDao);
        racingCarJdbcRepository.save(racingGame, trialCount);

        final List<RacingGame> racingGames = racingCarJdbcRepository.findAll();

        assertThat(racingGames).hasSize(1);
    }
}

