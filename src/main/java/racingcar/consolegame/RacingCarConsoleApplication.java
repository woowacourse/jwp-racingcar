package racingcar.consolegame;

//import racingcar.consolegame.dao.CarInMemoryDao;


import java.util.List;
import java.util.Scanner;
import racingcar.consolegame.dao.CarInMemoryDao;
import racingcar.consolegame.dao.RacingGameInMemoryDao;
import racingcar.consolegame.ui.InputView;
import racingcar.consolegame.ui.OutputView;
import racingcar.controller.RacingResponse;
import racingcar.service.RacingcarService;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            OutputView outputView = new OutputView();
            InputView inputView = new InputView(scanner);

            outputView.startRacing();
            List<String> carNames = inputView.carNames();
            outputView.tryCount();
            int tryCount = inputView.tryCount();

            RacingcarService racingcarService = new RacingcarService(
                    new RacingGameInMemoryDao(),
                    new CarInMemoryDao()
            );

            RacingResponse movedCars = racingcarService.move(carNames, tryCount);
            outputView.winner(movedCars);
            outputView.result(movedCars);
        }
    }

}
