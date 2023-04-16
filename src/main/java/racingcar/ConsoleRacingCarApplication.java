package racingcar;

import racingcar.controller.RacingController;
import racingcar.dao.CarInfoCollectionRepository;
import racingcar.dao.RacingCollectionRepository;
import racingcar.dto.RacingResultResponseDto;
import racingcar.service.RacingService;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;

public class ConsoleRacingCarApplication {
    public static void run() {
        RacingController controller = new RacingController(
                new RacingService(new RandomNumberGenerator(),
                        new RacingCollectionRepository(),
                        new CarInfoCollectionRepository())
        );
        RacingResultResponseDto racingResult = controller.playRacing(ConsoleInputView.getRacingInfoRequest());
        ConsoleOutputView.printResult(racingResult);
    }
}
