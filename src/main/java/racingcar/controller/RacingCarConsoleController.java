package racingcar.controller;

import java.util.List;
import racingcar.dto.PlayResponseDto;
import racingcar.repository.RacingCarRepository;
import racingcar.repository.dao.InMemoryCarsDao;
import racingcar.repository.dao.InMemoryPlayRecordsDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RacingCarService racingCarService = new RacingCarService(
            new RacingCarRepository(new InMemoryPlayRecordsDao(), new InMemoryCarsDao()));

    // TODO 여러번 플레이 가능 + 이력 조회
    public void run() {
        final List<String> carNames = inputView.askCarNames();
        final int racingCount = inputView.askRacingCount();

        final PlayResponseDto playResult = racingCarService.playGame(racingCount, carNames);

        outputView.printPlayResult(playResult);
        outputView.printPlayRecords(racingCarService.findAllPlayRecords());
    }

}
