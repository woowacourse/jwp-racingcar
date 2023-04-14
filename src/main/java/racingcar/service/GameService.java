package racingcar.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import racingcar.domain.RacingGame;
import racingcar.dto.GameInputDto;
import racingcar.dto.GameResultDto;
import racingcar.repository.GameRepository;
import racingcar.view.OutputView;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameResultDto playGame(final GameInputDto inputDto) {
        final List<String> playerNames = Arrays.asList(inputDto.getNames().split(","));
        final RacingGame racingGame = new RacingGame(playerNames, inputDto.getPlayCount());
        final GameResultDto gameResultDto = racingGame.play();
        gameRepository.save(gameResultDto);
        OutputView.printResult(gameResultDto);
        return gameResultDto;
    }

    public List<GameResultDto> fetchHistory() {
        return gameRepository.findAllResult();
    }
}
