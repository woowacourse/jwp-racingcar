package racingcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.GameInsertDao;
import racingcar.dao.PlayerInsertDao;
import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.Winner;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

@RestController
public class RacingGameController {

    private static final NumberGenerator numberGenerator = new RandomNumberGenerator();
    private static final CarGenerator carGenerator = new CarGenerator();
    private static final String DELIMETER = ",";

    @Autowired
    private GameInsertDao gameInsertDao;
    @Autowired
    private PlayerInsertDao playerInsertDao;

    @PostMapping(value = "/plays", consumes = "application/json")
    public ResponseDto play(@RequestBody RequestDto requestDto) {
        String names = requestDto.getNames();
        Integer count = requestDto.getCount();

        return getResponseDto(names, count);
    }

    private ResponseDto getResponseDto(String names, Integer count) {
        List<Car> cars = carGenerator.generateCars(names.split(DELIMETER));
        RacingGame racingGame = new RacingGame(cars, count, numberGenerator);
        racingGame.run();
        String winners = getWinners(racingGame);
        int gameId = gameInsertDao.insertGame(winners, count);
        playerInsertDao.insertPlayers(gameId, cars);

        return new ResponseDto(winners, cars);
    }

    private String getWinners(RacingGame racingGame) {
        Winner winner = racingGame.getWinner();
        List<String> winnerNames = winner.getWinnerNames();
        return String.join(DELIMETER, winnerNames);
    }
}
