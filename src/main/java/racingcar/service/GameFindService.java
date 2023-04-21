package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerResultDao;
import racingcar.dto.response.GameResultDto;
import racingcar.dto.response.GameWinnerDto;
import racingcar.dto.response.PlayerResultDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GameFindService {

    private final GameDao gameDao;
    private final PlayerResultDao playerResultDao;

    public GameFindService(GameDao gameDao, PlayerResultDao playerResultDao) {
        this.gameDao = gameDao;
        this.playerResultDao = playerResultDao;
    }

    public List<GameResultDto> findAllGames() {
        List<GameResultDto> gameResultDtos = new ArrayList<>();
        List<GameWinnerDto> allGames = gameDao.findAllGames();

        for (GameWinnerDto game : allGames) {
            List<PlayerResultDto> playerResultsDto = playerResultDao.findPlayerResultsByGameId(game.getGameId());
            gameResultDtos.add(new GameResultDto(game.getWinners(), playerResultsDto));
        }
        return gameResultDtos;
    }
}
