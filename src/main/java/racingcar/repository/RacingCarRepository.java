package racingcar.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.dao.PlayerDao;
import racingcar.dao.RacingCarGameDao;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameDto;

@Repository
public class RacingCarRepository {

    private final RacingCarGameDao racingCarGameDao;
    private final PlayerDao playerDao;

    public RacingCarRepository(final RacingCarGameDao racingCarGameDao, final PlayerDao playerDao) {
        this.racingCarGameDao = racingCarGameDao;
        this.playerDao = playerDao;
    }

    public void save(RacingGameDto racingGameDto, List<PlayerDto> playerDtos) {
        Long gameId = racingCarGameDao.insertGameWithKeyHolder(new Game(racingGameDto));
        playerDtos.forEach(
            playerDto -> playerDao.insertPlayers(
                new Player(playerDto.getName(), playerDto.getPosition(), gameId)
            )
        );
    }
}
