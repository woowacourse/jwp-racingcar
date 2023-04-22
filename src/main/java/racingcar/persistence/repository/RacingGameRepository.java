package racingcar.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.persistence.dao.GameResultDao;
import racingcar.persistence.dao.PlayerResultDao;
import racingcar.persistence.entity.GameResultEntity;
import racingcar.persistence.entity.PlayerResultEntity;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RacingGameRepository implements GameRepository {

    private final GameResultDao gameResultDao;
    private final PlayerResultDao playerResultDao;

    @Autowired
    public RacingGameRepository(final GameResultDao gameResultDao, final PlayerResultDao playerResultDao) {
        this.gameResultDao = gameResultDao;
        this.playerResultDao = playerResultDao;
    }

    public void saveGame(final RacingGame racingGame) {
        Integer gameResultKey = gameResultDao.save(toGameResultEntity(racingGame));
        playerResultDao.saveAll(toPlayerResultEntity(racingGame, gameResultKey));
    }

    private GameResultEntity toGameResultEntity(final RacingGame racingGame) {
        return GameResultEntity.ofInward(
                racingGame.getGameCoin().getGiven(),
                formatWinners(racingGame)
        );
    }

    private String formatWinners(final RacingGame racingGame) {
        return racingGame.getWinners().stream()
                .map(Car::getCarName)
                .reduce((o1, o2) -> o1 + "," + o2)
                .orElseThrow();
    }

    private List<PlayerResultEntity> toPlayerResultEntity(final RacingGame racingGame, final Integer gameResultId) {
        return racingGame.getCars().stream()
                .map(car -> PlayerResultEntity.ofInward(car.getCarName(), car.getPosition(), gameResultId))
                .collect(Collectors.toList());
    }

    public List<RacingGame> selectAllGames() {
        RacingGameConverter domainConverter = new RacingGameConverter();
        return domainConverter.convertAll(gameResultDao.selectAll(), playerResultDao.selectAll());
    }
}

