package racing.web.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import racing.domain.Car;
import racing.domain.Cars;
import racing.domain.repository.RacingGameRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RacingGameServiceTest {

    @Autowired
    private RacingGameRepository racingGameRepository;

    @Autowired
    private RacingGameService racingGameService;

    @DisplayName("시도 횟수와 자동차 이름으로 게임을 실행한다.")
    @Test
    void playNewGameTest() {
        String carNamesRequest = "carA,carB";

        Long newGameId = racingGameService.playNewGame(4, carNamesRequest);

        Cars gameCars = racingGameRepository.findRacingGameById(newGameId).getCars();
        List<String> carNames = gameCars.getCars().stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        assertThat(carNames).contains("carA", "carB");
    }

}
