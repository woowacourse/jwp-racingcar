package racingcar.controller;

import racingcar.dto.RacingGameDto;
import racingcar.dto.StartInformationDto;
import racingcar.services.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public ConsoleController(InputView inputView, OutputView outputView, RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }

    public void play() {
        try {
            StartInformationDto startInformationDto = createStartInformation();
            RacingGameDto racingGameDto = racingGameService.play(startInformationDto);
            showGameResult(racingGameDto);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
        }
    }

    private StartInformationDto createStartInformation() {
        List<String> carNames = inputView.readCarNames();
        String moveCount = inputView.readMoveCount();
        return new StartInformationDto(carNames, moveCount);
    }

    private void showGameResult(RacingGameDto racingGameDto) {
        outputView.printResultMessage();
        outputView.printWinners(racingGameDto.getWinnerNames());
        outputView.printResult(racingGameDto.getCars());
    }

}
