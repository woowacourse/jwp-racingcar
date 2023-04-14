package racingcar.service;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dto.CarDto;
import racingcar.dto.response.GameResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RacingGameServiceTest {

    @Autowired
    RacingGameService racingGameService;

    @RepeatedTest(value = 5)
    void play() {
        GameResponseDto result = racingGameService.play("도치,쥬니", 2);
        List<Integer> resultPositions = result.getRacingCars().stream()
                .map(CarDto::getPosition)
                .collect(Collectors.toList());

        assertThat(resultPositions).isSubsetOf(0, 1, 2);
    }
}
