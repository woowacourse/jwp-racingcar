package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racingcar.dao.GameInsertDao;
import racingcar.dao.PlayerInsertDao;
import racingcar.domain.Car;
import racingcar.domain.Winner;
import racingcar.dto.WinnerFormatter;

import java.util.List;
import java.util.Locale;

@Repository
public class RacingGameRepository {
    @Autowired
    private GameInsertDao gameInsertDao;
    @Autowired
    private PlayerInsertDao playerInsertDao;

    public void insert(Winner winner, int count, List<Car> cars) {
        String winnerNames = new WinnerFormatter().print(winner, Locale.getDefault());
        int gameId = gameInsertDao.insertGame(winnerNames, count);
        playerInsertDao.insertPlayers(gameId, cars);
    }
}
