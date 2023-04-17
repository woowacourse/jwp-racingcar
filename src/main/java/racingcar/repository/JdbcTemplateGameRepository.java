package racingcar.repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.GameDto;
import racingcar.dto.GameHistoryDto;
import racingcar.dto.GameResultDto;

public class JdbcTemplateGameRepository implements GameRepository {

    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public JdbcTemplateGameRepository(final GameDao gameDao, final PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    @Override
    public void save(final GameResultDto gameResultDto) {
        final GameDto gameDto = new GameDto(gameResultDto.getPlayCount());
        final long gameId = gameDao.insertGame(gameDto);
        playerDao.insertPlayers(gameResultDto.getPlayers(), gameId);
    }

    @Override
    public List<GameResultDto> findAllResult() {
        final List<GameHistoryDto> allHistory = gameDao.findAllHistory();
        final Function<GameHistoryDto, GameResultDto> longGameResultDtoFunction = history ->
                new GameResultDto(history.getPlayCount(),
                        playerDao.findAllByGameId(history.getGameId()));
        return allHistory.stream()
                .map(longGameResultDtoFunction)
                .collect(Collectors.toList());
    }
}
