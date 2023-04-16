package racingcar.service;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import racingcar.dto.CarDto;
import racingcar.dto.response.GameResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Import({TestConfig.class})
@WebMvcTest
class RacingGameServiceTest {

    @Autowired
    private RacingGameService racingGameService;

    @RepeatedTest(value = 5)
    void saveGame() {
        GameResponseDto result = racingGameService.saveGamePlay("도치,쥬니", 2);
        List<Integer> resultPositions = result.getRacingCars().stream()
                .map(CarDto::getPosition)
                .collect(Collectors.toList());

        assertThat(resultPositions).isSubsetOf(0, 1, 2);
    }
}
