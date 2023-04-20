package racingcar.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class JdbcRacingGameRepository implements RacingGameRepository {

    private final GameDao gameDao;
    private final PlayerDao playerDao;

    public JdbcRacingGameRepository(GameDao gameDao, PlayerDao playerDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
    }

    @Override
    public Long save(final int trialCount, final List<PlayerSaveDto> playerSaveDtos) {
        final Long gameId = gameDao.save(trialCount);
        playerDao.save(gameId, playerSaveDtos);
        return gameId;
    }

    @Override
    public List<RacingGameFindDto> findAll() {
        List<GameFindDto> gameFindDtos = gameDao.findAll();
//        return gameFindDtos.stream()
//                .map(gameFindDto -> playerDao.findById(gameFindDto.getId()))
//                .map(playerFindDtos ->
//                        new OneGameHistoryDto(generateWinners(playerFindDtos), generateRacingCarDtos(playerFindDtos)))
//                .collect(Collectors.toList());
        return gameFindDtos.stream()
                .map(gameFindDto -> new RacingGameFindDto(gameFindDto, playerDao.findById(gameFindDto.getId())))
                .collect(Collectors.toList());

    }

    private String generateWinners(List<PlayerFindDto> playerFindDtos) {
        return playerFindDtos.stream()
                .filter(PlayerFindDto::getIsWinner)
                .map(PlayerFindDto::getName)
                .collect(Collectors.joining(","));
    }

    private List<RacingCarDto> generateRacingCarDtos(List<PlayerFindDto> playerFindDtos) {
        return playerFindDtos.stream()
                .map(playerFindDto -> new RacingCarDto(playerFindDto.getName(), playerFindDto.getPosition()))
                .collect(Collectors.toList());
    }

}
