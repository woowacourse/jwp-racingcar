package racingcar.controller;

import racingcar.dto.GameDto;
import racingcar.dto.RecordDto;
import racingcar.model.Cars;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public ConsoleController(InputView inputView, OutputView outputView, final RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }

    public void play(){
        GameDto gameDto = new GameDto(inputView.getCarNames(), inputView.getTryCount());
        Cars cars = racingGameService.play(gameDto);

        outputView.result(new RecordDto(cars.getWinner(), racingGameService.carsToDto(cars)));
    }
}
