package racingcar.controller;


import racingcar.dto.RacingCarGameRequestDto;
import racingcar.dto.RacingCarGameResultResponseDto;
import racingcar.exception.invalidinput.InvalidInputException;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController(final RacingCarService racingCarService) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.racingCarService = racingCarService;
    }

    public void play() {
        String carNames = inputCarNames();
        int count = inputTryCount();
        RacingCarGameRequestDto racingCarGameRequestDto = new RacingCarGameRequestDto(carNames, count);
        RacingCarGameResultResponseDto result = racingCarService.play(racingCarGameRequestDto);
        outputView.printResult(result);
    }

    private String inputCarNames() {
        try {
            outputView.printStartMessage();
            return inputView.inputCarName();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return inputCarNames();
        }
    }

    private int inputTryCount() {
        try {
            outputView.printCountMessage();
            return inputView.inputCount();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return inputTryCount();
        }
    }

}
