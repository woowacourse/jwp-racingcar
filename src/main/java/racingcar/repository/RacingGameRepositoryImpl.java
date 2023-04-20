package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarGameDao;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RacingGameRepositoryImpl implements RacingGameRepository {

    private final RacingCarGameDao racingCarGameDao;

    public RacingGameRepositoryImpl(RacingCarGameDao racingCarGameDao) {
        this.racingCarGameDao = racingCarGameDao;
    }

    @Override
    public RacingGame save(RacingGame racingGame) {
        if (racingGame.getGameId() == null) {
            Long gameId = racingCarGameDao.insertGameWithKeyHolder(RacingGameMapper.mapToGame(racingGame));
            RacingGame racingGameWithId = new RacingGame(gameId, racingGame);
            insertPlayers(RacingGameMapper.mapToPlayers(racingGameWithId));

            return racingGameWithId;
        }

        racingCarGameDao.updateGame(RacingGameMapper.mapToGame(racingGame));
        insertPlayers(RacingGameMapper.mapToPlayers(racingGame));

        return racingGame;
    }

    private void insertPlayers(List<Player> players) {
        for (Player player : players) {
            racingCarGameDao.insertPlayer(player);
        }
    }

    @Override
    public List<RacingGame> getAllRacingGames() {
        return racingCarGameDao.queryAllGames().stream()
                .map(this::getRacingGameByGame)
                .collect(Collectors.toList());
    }

    private RacingGame getRacingGameByGame(Game game) {
        List<Player> playersByGameId = racingCarGameDao.findPlayersByGameId(game.getGameId());
        Cars cars = mapToCars(playersByGameId);

        return new RacingGame(cars, game.getPlayCount());
    }

    private Cars mapToCars(List<Player> players) {
        List<Car> cars = players.stream()
                .map(this::mapToCar)
                .collect(Collectors.toList());

        return new Cars(cars);
    }

    private Car mapToCar(Player player) {
        return new Car(player.getName(), player.getPosition());
    }
}
