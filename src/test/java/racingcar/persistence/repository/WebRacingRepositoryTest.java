package racingcar.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.domain.Coin;
import racingcar.domain.DeterminedNumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.persistence.dao.JdbcTemplateRacingDao;

@JdbcTest
class WebRacingRepositoryTest {

    private static final int STOP = 3;
    private static final int MOVE = 4;

    private final RacingRepository racingRepository;

    @Autowired
    public WebRacingRepositoryTest(final JdbcTemplate jdbcTemplate) {
        this.racingRepository = new WebRacingRepository(new JdbcTemplateRacingDao(jdbcTemplate));
    }

    @DisplayName("자동차 경주 게임을 저장하고 반환한다.")
    @Test
    void shouldReturnRacingGameWhenAfterSave() {
        RacingGame racingGame = new RacingGame(
                Arrays.asList(Car.createBy("브리"), Car.createBy("브라운"), Car.createBy("토미")),
                DeterminedNumberGenerator.createByNumbers(
                        //브리 브라운  토미
                        MOVE, MOVE, STOP,
                        STOP, MOVE, MOVE,
                        MOVE, STOP, STOP
                        // 2     2     1
                ),
                new Coin(3)
        );
        racingRepository.saveGameResult(racingGame, 3);

        List<RacingGame> racingGames = racingRepository.findAllRacingGames();
        RacingGame racingGameFromDb = racingGames.get(0);

        assertAll(
                () -> {
                    for (int i = 0; i < racingGame.getCars().size(); i++) {
                        assertThat(racingGame.getCars().get(i).getCarName()).isEqualTo(
                                racingGameFromDb.getCars().get(i).getCarName());
                    }
                },
                () -> {
                    for (int i = 0; i < racingGame.getWinners().size(); i++) {
                        assertThat(racingGame.getWinners().get(i).getCarName()).isEqualTo(
                                racingGameFromDb.getWinners().get(i).getCarName()
                        );
                    }
                }
        );
    }
}
