package racingcar.controller;

import racingcar.domain.strategy.move.RandomBasedMoveStrategy;
import racingcar.dto.GameInputDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.view.repeatinput.Repeat;

public class ConsoleRacingCarController {
    private final RacingCarService racingCarService;
    private final InputView inputView;
    private final OutputView outputView;
    
    public ConsoleRacingCarController(final RacingCarService racingCarService, final InputView inputView, final OutputView outputView) {
        this.racingCarService = racingCarService;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void run() {
        Repeat.repeatJustRunnableAtException(this::playGame);
        outputView.printGameResult(racingCarService.findAllGameResult());
    }
    
    private void playGame() {
        final GameInputDto gameInputDto = getGameInputDto();
        racingCarService.playGame(gameInputDto, new RandomBasedMoveStrategy());
    }
    
    private GameInputDto getGameInputDto() {
        final String carNames = Repeat.repeatSupplierAtException(inputView::inputCarNames);
        final int tryNumber = Repeat.repeatSupplierAtException(inputView::inputTryNumber);
        return getGameInputDto(carNames, tryNumber);
    }
    
    private GameInputDto getGameInputDto(final String carNames, final int tryNumber) {
        return GameInputDto.builder()
                .carNames(carNames)
                .tryNumber(tryNumber)
                .build();
    }
}
