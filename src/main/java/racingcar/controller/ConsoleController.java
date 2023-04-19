package racingcar.controller;

import racingcar.domain.Names;
import racingcar.domain.TryCount;
import racingcar.domain.movingstrategy.MovingStrategy;
import racingcar.dto.request.UserRequestDto;
import racingcar.dto.response.GameResultResponseDto;
import racingcar.service.RacingGameService;
import racingcar.utils.DtoMapper;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public final class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public ConsoleController(final InputView inputView, final OutputView outputView, final RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }


    public void run(final MovingStrategy movingStrategy) {
        final Names carNames = new Names(inputView.readCarNames());
        final TryCount tryCount = new TryCount(inputView.readTryCount());
        final UserRequestDto userRequestDto = DtoMapper.mapToUserRequestDto(carNames, tryCount);
        final GameResultResponseDto resultDto = racingGameService.getResult(userRequestDto);
        outputView.printTotalMovingStatus(resultDto);
    }
}
