package racingcar.controller;

import racingcar.dto.ServiceControllerDto;
import racingcar.service.WebService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class GameController {
    public void play() {
        final String carNames = InputView.inputCarNames();
        final int trialCount = InputView.inputTrialCount();
        ServiceControllerDto serviceControllerDto = WebService.playOnConsole(trialCount, carNames);
        OutputView.noticeResult();
        OutputView.printCars(serviceControllerDto.getGameLog());
        OutputView.printWinners(serviceControllerDto.getWinners());
    }
}
