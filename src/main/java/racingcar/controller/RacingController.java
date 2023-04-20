package racingcar.controller;

import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingController(InputView inputView, OutputView outputView, RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void play() {
        List<String> carNames = inputCarNames();
        int tryCount = inputTryCount();
        var requestDto = new RacingGameRequestDto(carNames, tryCount);

        ResultResponseDto responseDto = racingCarService.play(requestDto);

        outputView.printStatus(responseDto);
        outputView.printWinners(responseDto);
    }

    private List<String> inputCarNames() {
        try {
            outputView.printStartMessage();
            return inputView.inputCarName();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCarNames();
        }
    }

    private int inputTryCount() {
        outputView.printCountMessage();
        return inputView.inputCount();
    }
}
