package racingcar.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarGameDao;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameDto;

@Repository
public class RacingCarRepository {
    private final RacingCarGameDao racingCarGameDao;

    public RacingCarRepository(RacingCarGameDao racingCarGameDao) {
        this.racingCarGameDao = racingCarGameDao;
    }

    public void save(RacingGameDto racingGameDto, List<PlayerDto> playerDtos) {
        Long gameId = racingCarGameDao.insertGameWithKeyHolder(new Game(racingGameDto));

        List<Player> players = playerDtos.stream()
                .map(playerDto -> new Player(playerDto.getName(), playerDto.getPosition(), gameId))
                .collect(Collectors.toList());

        for (Player player : players) {
            racingCarGameDao.insertPlayers(player);
        }
    }
}
