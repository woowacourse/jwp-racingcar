package racingcar.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;
import racingcar.dao.game.RacingCarGameDao;
import racingcar.dao.player.PlayerDao;
import racingcar.domain.RacingCarGame;
import racingcar.dto.ResultResponseDto;

@Repository
public class RacingCarRepository {

    private final RacingCarGameDao racingCarGameDao;
    private final PlayerDao playerDao;

    public RacingCarRepository(final RacingCarGameDao racingCarGameDao, final PlayerDao playerDao) {
        this.racingCarGameDao = racingCarGameDao;
        this.playerDao = playerDao;
    }

    public void save(RacingCarGame racingCarGame) {
        Long gameId = racingCarGameDao.insertGameWithKeyHolder(Game.of(racingCarGame));
        List<Player> players = racingCarGameToPlayers(racingCarGame, gameId);
        playerDao.insertPlayer(players);
    }

    public List<ResultResponseDto> readGameResultAll() {
        return racingCarGameDao.findAll();
    }

    private static List<Player> racingCarGameToPlayers(final RacingCarGame racingCarGame, final Long gameId) {
        List<Player> players = racingCarGame.getCars().getAll().stream()
            .map(playerDto -> new Player(playerDto.getName(), playerDto.getPosition(), gameId))
            .collect(Collectors.toList());
        return players;
    }
}
