package racingcar.controller;

import racingcar.domain.strategy.move.RandomBasedMoveStrategy;
import racingcar.dto.GameRequestDto;
import racingcar.service.ConsoleRacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.view.repeatinput.Repeat;

public class ConsoleRacingCarController {
    private final ConsoleRacingCarService consoleRacingCarService;
    private final InputView inputView;
    private final OutputView outputView;
    
    public ConsoleRacingCarController(final ConsoleRacingCarService consoleRacingCarService, final InputView inputView, final OutputView outputView) {
        this.consoleRacingCarService = consoleRacingCarService;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void run() {
        Repeat.repeatJustRunnableAtException(this::playGame);
        outputView.printGameResult(consoleRacingCarService.findAllGameResult());
    }
    
    private void playGame() {
        final GameRequestDto gameRequestDto = getGameInputDto();
        consoleRacingCarService.playGame(gameRequestDto, new RandomBasedMoveStrategy());
    }
    
    private GameRequestDto getGameInputDto() {
        final String carNames = Repeat.repeatSupplierAtException(inputView::inputCarNames);
        final int count = Repeat.repeatSupplierAtException(inputView::inputCount);
        return getGameInputDto(carNames, count);
    }
    
    private GameRequestDto getGameInputDto(final String carNames, final int count) {
        return GameRequestDto.builder()
                .names(carNames)
                .count(String.valueOf(count))
                .build();
    }
}
