package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.GameInsertDao;
import racingcar.dao.PlayerInsertDao;
import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.Winner;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResultResponseDto;
import racingcar.dto.WinnerFormatter;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
public class RacingGameController {

    private static final NumberGenerator numberGenerator = new RandomNumberGenerator();
    private static final CarGenerator carGenerator = new CarGenerator();
    private static final String DELIMETER = ",";

    @Autowired
    ConversionService conversionService;
    @Autowired
    private GameInsertDao gameInsertDao;
    @Autowired
    private PlayerInsertDao playerInsertDao;

    @PostMapping(value = "/plays", consumes = "application/json")
    public PlayResultResponseDto play(@Valid @RequestBody PlayRequestDto playRequestDto) {
        String names = playRequestDto.getNames();
        Integer count = playRequestDto.getCount();

        return getResponseDto(names, count);
    }

    private PlayResultResponseDto getResponseDto(String names, Integer count) {
        List<Car> cars = carGenerator.generateCars(names.split(DELIMETER));
        RacingGame racingGame = new RacingGame(cars, count, numberGenerator);
        racingGame.run();
        Winner winner = racingGame.getWinner();
        int gameId = gameInsertDao.insertGame(new WinnerFormatter().print(winner, Locale.getDefault()), count);
        playerInsertDao.insertPlayers(gameId, cars);

        return new PlayResultResponseDto(winner, cars);
    }
}
