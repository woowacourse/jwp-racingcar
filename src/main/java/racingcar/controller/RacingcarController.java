package racingcar.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.RacingCarGameDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Controller
public class RacingcarController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    @PostMapping(path = "/plays")
    public void run(@RequestBody RacingCarGameDto racingCarGameDto) {
        Cars cars = new Cars(racingCarGameDto.getNames());
        TryCount tryCount = new TryCount(racingCarGameDto.getTryCount());
        outputView.printResultMessage();
        playRound(cars, tryCount);
        outputView.printWinners(cars.getWinner());
    }

    private void playRound(Cars cars, TryCount tryCount) {
        for (int i = 0; i < tryCount.getValue(); i++) {
            List<Car> roundResult = cars.runRound();
            outputView.printRoundResult(roundResult);
        }
        outputView.printRoundResult(cars.getCars());
    }
}
