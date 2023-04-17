package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.entity.Game;
import racingcar.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final NumberGenerator numberGenerator;
    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public RacingGameService(final NumberGenerator numberGenerator, final GameDao gameDao, final PlayerDao playerDao) {
        this.numberGenerator = numberGenerator;
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    @Transactional
    public GameResponse play(final GameRequest gameRequest) {
        final RacingGame racingGame = playRacingGame(gameRequest);

        final Game game = Game.of(gameRequest.getCount());
        final int gameId = gameDao.save(game);

        final Set<String> winners = new HashSet<>(racingGame.findWinners());
        final List<Player> cars = racingGame.findCurrentCarPositions().stream()
                .map(car -> Player.of(car, winners.contains(car.getName()), gameId))
                .collect(Collectors.toList());

        playerDao.saveAll(cars);

        return GameResponse.of(racingGame.findWinners(), cars);
    }

    private RacingGame playRacingGame(final GameRequest gameRequest) {
        final RacingGame racingGame = new RacingGame(numberGenerator, gameRequest.getNames(), gameRequest.getCount());

        racingGame.play();

        return racingGame;
    }
}
