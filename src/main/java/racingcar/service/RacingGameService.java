package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.Winner;
import racingcar.dto.PlayResultResponseDto;
import racingcar.dto.WinnerFormatter;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class RacingGameService {
    private static final NumberGenerator numberGenerator = new RandomNumberGenerator();
    private static final CarGenerator carGenerator = new CarGenerator();
    private static final String DELIMETER = ",";

    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public RacingGameService(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public PlayResultResponseDto run(String names, Integer count) {
        RacingGame racingGame = play(names, count);
        Winner winner = racingGame.getWinner();
        List<Car> cars = racingGame.getCars();

        insert(winner, count, cars);

        return new PlayResultResponseDto(winner, cars);
    }

    public RacingGame play(String names, Integer count) {
        List<Car> cars = carGenerator.generateCars(names.split(DELIMETER));
        RacingGame racingGame = new RacingGame(cars, count, numberGenerator);
        racingGame.run();

        return racingGame;

    }

    public void insert(Winner winner, int count, List<Car> cars) {
        String winnerNames = new WinnerFormatter().print(winner, Locale.getDefault());
        int gameId = gameDao.insert(winnerNames, count);
        playerDao.insert(gameId, cars);
    }

    public List<PlayResultResponseDto> getPlayHistory() {
        List<PlayResultResponseDto> playResultResponseDtos = new ArrayList<>();

        int lastGameId = gameDao.findLastId().orElseThrow(() -> new IllegalStateException("존재하는 게임 정보가 없습니다"));
        for (int index = 1; index < lastGameId + 1; index++) {
            String winnerNames = gameDao.findWinners(index);
            Winner winner = new Winner(Arrays.asList(winnerNames.split(",")));
            List<Car> cars = playerDao.find(index);

            playResultResponseDtos.add(new PlayResultResponseDto(winner, cars));
        }
        return playResultResponseDtos;
    }
}
