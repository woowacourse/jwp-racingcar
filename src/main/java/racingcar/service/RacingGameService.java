package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.Entity.Game;
import racingcar.Entity.Player;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private static final String DELIMITER = ",";

    private final NumberGenerator numberGenerator;
    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public RacingGameService(final NumberGenerator numberGenerator, final GameDao gameDao, final PlayerDao playerDao) {
        this.numberGenerator = numberGenerator;
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    public GameResponse play(final GameRequest gameRequest) {
        final RacingGame racingGame = playRacingGame(gameRequest);

        final Game game = Game.of(racingGame.findWinners(), gameRequest.getCount());
        final int gameId = gameDao.save(game);

        final List<Player> cars = racingGame.findCurrentCarPositions().stream()
                .map(car -> Player.of(car, gameId))
                .collect(Collectors.toList());

        playerDao.saveAll(cars);

        return GameResponse.of(game.getWinners(), cars);
    }

    private RacingGame playRacingGame(final GameRequest gameRequest) {
        final List<String> names = Arrays.stream(gameRequest.getNames().split(DELIMITER))
                .collect(Collectors.toList());
        final RacingGame racingGame = new RacingGame(numberGenerator, names, gameRequest.getCount());
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
        return racingGame;
    }
}
