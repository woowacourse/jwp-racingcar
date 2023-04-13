package racingcar.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class RacingGameServiceTest {
    @Autowired
    private RacingGameService racingGameService;
    
    @Test
    void playGameWithoutPrint() {
        GameInputDto gameInputDto = new GameInputDto("스플릿,아벨,포비", "12");
        
        RacingResultDto racingResultDto = racingGameService.playGameWithoutPrint(gameInputDto, () -> 5);
        assertAll(
                () -> assertThat(racingResultDto.getWinners()).isEqualTo("스플릿,아벨,포비"),
                () -> assertThat(racingResultDto.getRacingCars())
                        .containsExactly(
                                new Car("스플릿", 0),
                                new Car("아벨", 0),
                                new Car("포비", 0)
                        )
        );
    }
}