package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.*;
import racingcar.service.RacingCarService;
import racingcar.util.RandomNumberGenerator;
import racingcar.validation.Validation;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RacingCarController {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private final DataSource dataSource = DataSourceBuilder.create()
                                                           .url("jdbc:h2:mem:testdb")
                                                           .username("sa")
                                                           .password("")
                                                           .type(HikariDataSource.class)
                                                           .build();
    private final ResultDao resultDao = new ResultDao(new JdbcTemplate(dataSource));
    private final RacingCarDao racingCarDao  = new RacingCarDao(new JdbcTemplate(dataSource));

    @PostMapping("plays")
    public ResponseEntity<GameResultDto> createGame(@RequestBody GameInforamtionDto gameInforamtionDto) {
        Cars cars = getCars(gameInforamtionDto);
        int trialCount = getTrialCount(gameInforamtionDto);

        List<RacingCarDto> racingCars = playGame(cars, trialCount);

        GameResultDto gameResultDto = new GameResultDto(cars.getWinnerCars(), racingCars);
        return ResponseEntity.ok()
                             .body(gameResultDto);
    }

    private static Cars getCars(GameInforamtionDto gameInforamtionDto) {
        String names = gameInforamtionDto.getNames();
        Validation.validateCarNames(names);
        Cars cars = new Cars(names);
        return cars;
    }

    private static int getTrialCount(GameInforamtionDto gameInforamtionDto) {
        int trialCount = gameInforamtionDto.getCount();
        Validation.validateTryCount(trialCount);
        return trialCount;
    }

    private List<RacingCarDto> playGame(Cars cars, int trialCount) {
        for (int count = 0; count < trialCount; count++) {
            cars.moveForRound(randomNumberGenerator);
        }

        List<RacingCarDto> racingCars = new ArrayList<>();
        for (Car car : cars.getCars()) {
            RacingCarDto racingCarDto = new RacingCarDto(car.getName(), car.getLocation());
            racingCars.add(racingCarDto);
        }
        return racingCars;
    }
}
