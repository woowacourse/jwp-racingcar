package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.Car;
import racingcar.domain.Winner;
import racingcar.dto.PlayResultResponseDto;
import racingcar.dto.WinnerFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Repository
public class RacingGameRepository {
    @Autowired
    private GameDao gameDao;
    @Autowired
    private PlayerDao playerDao;

    public void insert(Winner winner, int count, List<Car> cars) {
        String winnerNames = new WinnerFormatter().print(winner, Locale.getDefault());
        int gameId = gameDao.insert(winnerNames, count);
        playerDao.insert(gameId, cars);
    }

    public List<PlayResultResponseDto> get() {
        List<PlayResultResponseDto> playResultResponseDtos = new ArrayList<>();

        int lastGameId = gameDao.findLastId();
        for (int index = 1; index < lastGameId + 1; index++) {
            String winnerNames = gameDao.findWinners(index);
            Winner winner = new Winner(Arrays.asList(winnerNames.split(",")));
            List<Car> cars = playerDao.find(index);

            playResultResponseDtos.add(new PlayResultResponseDto(winner, cars));
        }
        return playResultResponseDtos;
    }
}
