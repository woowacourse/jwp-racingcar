package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.dto.response.GameResponseDto;
import racingcar.dto.request.GameResultDto;
import racingcar.dto.request.GameSaveDto;
import racingcar.dto.request.PlayerResultSaveDto;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.GameRepository;
import racingcar.repository.PlayerResultRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerResultRepository playerResultRepository;

    @Autowired
    public GameService(final GameRepository gameRepository, final PlayerResultRepository playerResultRepository) {
        this.gameRepository = gameRepository;
        this.playerResultRepository = playerResultRepository;
    }

    @Transactional
    public GameResponseDto playGame(GameResultDto gameResult) {
        final Game game = createGame(gameResult);
        final List<PlayerResult> playerResults = createPlayerResults(gameResult, game);
        return GameResponseDto.of(game, playerResults);
    }

    private Game createGame(final GameResultDto gameResult) {
        GameSaveDto gameSaveDto = new GameSaveDto(gameResult.getWinners(), gameResult.getTrialCount());
        Game game = gameRepository.createGame(gameSaveDto);
        return game;
    }

    private List<PlayerResult> createPlayerResults(final GameResultDto gameResult, final Game game) {
        final List<PlayerResult> playerResults = new ArrayList<>();
        for (Car car : gameResult.getCars()) {
            final PlayerResultSaveDto playerResultSaveDto = new PlayerResultSaveDto(game.getId(), car);
            final PlayerResult playerResult = playerResultRepository.savePlayerResult(playerResultSaveDto);
            playerResults.add(playerResult);
        }
        return playerResults;
    }
}
