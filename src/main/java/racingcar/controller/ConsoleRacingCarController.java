package racingcar.controller;

import racingcar.domain.strategy.move.RandomBasedMoveStrategy;
import racingcar.dto.GameRequestDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.function.Supplier;

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
        repeatJustRunnableAtException(this::playGame);
        outputView.printGameResult(racingCarService.findAllGameResult());
    }
    
    private void playGame() {
        final GameRequestDto gameRequestDto = getGameInputDto();
        racingCarService.playGame(gameRequestDto, new RandomBasedMoveStrategy());
    }
    
    private GameRequestDto getGameInputDto() {
        final String carNames = repeatSupplierAtException(inputView::inputCarNames);
        final int count = repeatSupplierAtException(inputView::inputCount);
        return getGameInputDto(carNames, count);
    }
    
    private GameRequestDto getGameInputDto(final String carNames, final int count) {
        return GameRequestDto.builder()
                .names(carNames)
                .count(String.valueOf(count))
                .build();
    }
    
    public <T> T repeatSupplierAtException(Supplier<T> inputProcess) {
        try {
            return inputProcess.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("[ERROR] " + illegalArgumentException);
            return repeatSupplierAtException(inputProcess);
        }
    }
    
    public void repeatJustRunnableAtException(JustRunnable inputProcess) {
        try {
            inputProcess.run();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("[ERROR] " + illegalArgumentException);
            repeatJustRunnableAtException(inputProcess);
        }
    }
}
