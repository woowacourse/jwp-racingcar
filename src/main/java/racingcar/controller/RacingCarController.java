package racingcar.controller;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.domain.*;
import racingcar.util.RandomNumberGenerator;
import racingcar.validation.Validation;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Configuration
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
        String names = gameInforamtionDto.getNames();
        Validation.validateCarNames(names);
        Cars cars = new Cars(names);

        int trialCount = gameInforamtionDto.getCount();
        Validation.validateTryCount(trialCount);

        for (int count = 0; count < trialCount; count++) {
            cars.moveForRound(randomNumberGenerator);
        }

        long resultId = resultDao.insert(trialCount, cars.getWinnerCars());

        List<RacingCarDto> racingCars = new ArrayList<>();
        for (Car car : cars.getCars()) {
            RacingCarDto racingCarDto = new RacingCarDto(car.getName(), car.getLocation());
            racingCars.add(racingCarDto);
            racingCarDao.insert(car, resultId);
        }

        GameResultDto gameResultDto = new GameResultDto(cars.getWinnerCars(), racingCars);
        return ResponseEntity.ok().body(gameResultDto);
    }
}
