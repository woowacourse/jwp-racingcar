package racingcar.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.response.GameResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class RacingGameJdbcRepositoryTest {

    @Autowired
    RacingGameRepository racingGameRepository;

    @BeforeEach
    void setUp() {
        racingGameRepository.deleteAll();
    }

    @Test
    void findGame_success() {
        GameResultDto gameResultDto = new GameResultDto(5, "qwer",
                List.of(new CarDto("qwer", 4))
        );

        racingGameRepository.saveGame(gameResultDto);
        List<GameResponse> games = racingGameRepository.findAllGame();

        assertThat(games.get(0).getWinners()).isEqualTo("qwer");
    }
}
