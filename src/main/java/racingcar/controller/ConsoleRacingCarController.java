package racingcar.controller;

import racingcar.domain.strategy.move.RandomBasedMoveStrategy;
import racingcar.dto.GameRequestDto;
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
        final GameRequestDto gameRequestDto = getGameInputDto();
        racingCarService.playGame(gameRequestDto, new RandomBasedMoveStrategy());
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
