package racingcar.controller;

import racingcar.dto.RacingGameResultDto;
import racingcar.dto.RacingGameSetUpDto;
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
            RacingGameSetUpDto startInformationDto = createStartInformation();
            RacingGameResultDto racingGameDto = racingGameService.play(startInformationDto);
            showGameResult(racingGameDto);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
        }
    }

    private RacingGameSetUpDto createStartInformation() {
        List<String> carNames = inputView.readCarNames();
        String moveCount = inputView.readMoveCount();
        return new RacingGameSetUpDto(carNames, moveCount);
    }

    private void showGameResult(RacingGameResultDto racingGameResultDto) {
        outputView.printResultMessage();
        outputView.printWinners(racingGameResultDto.getWinnerNames());
        outputView.printResult(racingGameResultDto.getCars());
    }

}
