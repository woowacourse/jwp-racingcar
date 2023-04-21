package racing.controller;

import java.util.List;
import java.util.Scanner;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;
import racing.service.RacingGameService;
import racing.util.RandomNumberGenerator;
import racing.view.InputView;
import racing.view.OutputView;

public class ConsoleRacingGameController implements RacingGameController {

    private final RacingGameService racingGameService;

    public ConsoleRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public GameInputDto getInput() {
        try (final Scanner scanner = new Scanner(System.in)) {
            final InputView inputView = new InputView(scanner);
            final String carNamesInput = inputView.getCarNamesInput();
            final String gameCountInput = inputView.getGameCountInput();
            return new GameInputDto(carNamesInput, gameCountInput);
        }
    }

    @Override
    public GameResultDto play(final GameInputDto gameInputDto) {
        final GameResultDto gameResultDto = racingGameService.playGame(gameInputDto, new RandomNumberGenerator());
        final OutputView outputView = new OutputView();
        outputView.printFinalResult(gameResultDto.getRacingCars());
        outputView.printWinners(gameResultDto.getWinners());
        return gameResultDto;
    }

    @Override
    public List<GameResultDto> showAllGames() {
        final List<GameResultDto> gameResultDtos = racingGameService.showGames();
        return List.copyOf(gameResultDtos);
    }
}
