package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import racingcar.domain.Car;
import racingcar.dto.GameInputDto;
import racingcar.dto.RacingResultResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Sql("/init.sql")
@SpringBootTest
class RacingGameServiceTest {
    @Autowired
    private RacingGameService racingGameService;
    private RacingResultResponseDto racingResultResponseDto;
    
    @BeforeEach
    void setUp() {
        GameInputDto gameInputDto = new GameInputDto("스플릿,아벨,포비", "12");
        racingResultResponseDto = racingGameService.playGameWithoutPrint(gameInputDto, () -> 5);
    }
    
    @Test
    void playGameWithoutPrint() {
        assertAll(
                () -> assertThat(racingResultResponseDto.getWinners()).isEqualTo("스플릿,아벨,포비"),
                () -> assertThat(racingResultResponseDto.getRacingCars())
                        .containsExactly(
                                new Car("스플릿", 0),
                                new Car("아벨", 0),
                                new Car("포비", 0)
                        )
        );
    }
    
    @Test
    void findAllGameResult() {
        GameInputDto gameInputDto = new GameInputDto("aa,bb,cc", "12");
        racingResultResponseDto = racingGameService.playGameWithoutPrint(gameInputDto, () -> 5);
        
        List<RacingResultResponseDto> allGameResult = racingGameService.findAllGameResult();
        List<String> winners = allGameResult.stream()
                .map(RacingResultResponseDto::getWinners)
                .collect(Collectors.toUnmodifiableList());
        
        List<String> carNames = allGameResult.stream()
                .map(RacingResultResponseDto::getRacingCars)
                .map(this::parseCarNames)
                .collect(Collectors.toUnmodifiableList());
        
        assertAll(
                () -> assertThat(winners).containsExactly("스플릿,아벨,포비", "aa,bb,cc"),
                () -> assertThat(carNames).containsExactly("스플릿,아벨,포비", "aa,bb,cc")
        );
    }
    
    private String parseCarNames(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }
}