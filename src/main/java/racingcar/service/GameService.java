package racingcar.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.RacingGame;
import racingcar.dto.GameInputDto;
import racingcar.dto.GameResultDto;
import racingcar.repository.GameRepository;
import racingcar.view.OutputView;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameResultDto playGame(final GameInputDto inputDto) {
        final List<String> playerNames = Arrays.stream(inputDto.getNames().split(","))
                .map(String::strip)
                .collect(Collectors.toList());
        final RacingGame racingGame = new RacingGame(playerNames, inputDto.getCount());
        final GameResultDto gameResultDto = racingGame.play();
        gameRepository.save(gameResultDto);
        OutputView.printResult(gameResultDto);
        return gameResultDto;
    }

    public List<GameResultDto> fetchHistory() {
        return gameRepository.findAllResult();
    }
}
