package racingcar.repository;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.dao.WinnerDao;
import racingcar.dto.GameDto;
import racingcar.dto.GameHistoryDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.WinnerDto;

public class JdbcTemplateGameRepository implements GameRepository {
    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final WinnerDao winnerDao;

    public JdbcTemplateGameRepository(final DataSource dataSource) {
        this.gameDao = new GameDao(dataSource);
        this.playerDao = new PlayerDao(dataSource);
        this.winnerDao = new WinnerDao(dataSource);
    }

    private static List<WinnerDto> convertStringToWinnerDtos(final String winners) {
        return Arrays.stream(winners.split(",")).
                map(WinnerDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void save(final GameResultDto gameResultDto) {
        final GameDto gameDto = new GameDto(gameResultDto.getPlayCount());
        final List<WinnerDto> winners = convertStringToWinnerDtos(gameResultDto.getWinners());
        final long gameId = gameDao.insertGame(gameDto);
        playerDao.insertPlayers(gameResultDto.getPlayers(), gameId);
        winnerDao.insertWinners(winners, gameId);
    }

    @Override
    public List<GameResultDto> findAllResult() {
        final List<GameHistoryDto> allHistory = gameDao.findAllHistory();
        final Function<GameHistoryDto, GameResultDto> longGameResultDtoFunction = history ->
                new GameResultDto(history.getPlayCount(),
                        winnerDao.findAllByGameId(history.getGameId()),
                        playerDao.findAllByGameId(history.getGameId()));
        return allHistory.stream()
                .map(longGameResultDtoFunction)
                .collect(Collectors.toList());
    }
}
