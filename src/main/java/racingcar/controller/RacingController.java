package racingcar.controller;


import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.exception.invalidinput.InvalidInputException;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingController(final InputView inputView, final OutputView outputView, final RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void play() {
        String carNames = inputCarNames();
        int count = inputTryCount();
        RacingGameRequestDto racingGameRequestDto = new RacingGameRequestDto(carNames, count);

        ResultResponseDto result = racingCarService.play(racingGameRequestDto);

        outputView.resultMessage();
        outputView.printWinners(result.getWinners());
        outputView.printStatus(result.getRacingCars());
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
