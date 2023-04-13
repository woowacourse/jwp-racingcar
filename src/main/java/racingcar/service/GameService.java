package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.dto.GameResponseDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameSaveDto;
import racingcar.dto.PlayerResultSaveDto;
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

    public GameResponseDto playGame(GameResultDto gameResult) {
        GameSaveDto gameSaveDto = new GameSaveDto(gameResult.getWinners(), gameResult.getTrialCount());
        Game game = gameRepository.createGame(gameSaveDto);

        List<PlayerResult> playerResults = new ArrayList<>();
        for (Car car : gameResult.getCars()) {
            PlayerResult playerResult = playerResultRepository.savePlayerResult(
                    new PlayerResultSaveDto(game.getId(), car.getCarName().getName(), car.getCurrentPosition().getPosition())
            );
            playerResults.add(playerResult);
        }
        return GameResponseDto.of(game, playerResults);
    }
}
