package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameInitializeDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarGameResultDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Controller
public class RacingCarController {
    private final RacingCarService racingCarService;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public RacingCarGameResultDto run(@RequestBody GameInitializeDto gameInitializeDto) {
        Cars cars = makeCars(gameInitializeDto);
        TryCount tryCount = makeTryCount(gameInitializeDto);
        outputView.printResultMessage();
        playRound(cars, tryCount);
        outputView.printWinners(cars.getWinner());
        racingCarService.saveGameResult(cars, tryCount);
        return new RacingCarGameResultDto(String.join(",", cars.getWinner()), makeCarDtos(cars));
    }

    @GetMapping("/plays")
    @ResponseBody
    public ResponseEntity<List<RacingCarGameResultDto>> showGameResult() {
        List<RacingCarGameResultDto> gameResult = racingCarService.getGameResult();
        return ResponseEntity.ok().body(gameResult);
    }

    private Cars makeCars(GameInitializeDto gameInitializeDto) {
        try {
            outputView.printNameInput();
            return new Cars(gameInitializeDto.getNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeCars(gameInitializeDto);
        }
    }

    private TryCount makeTryCount(GameInitializeDto gameInitializeDto) {
        try {
            outputView.printCountInput();
            return new TryCount(gameInitializeDto.getCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeTryCount(gameInitializeDto);
        }
    }

    private void playRound(Cars cars, TryCount tryCount) {
        for (int i = 0; i < tryCount.getValue(); i++) {
            List<Car> roundResult = cars.runRound();
            outputView.printRoundResult(roundResult);
        }
        outputView.printRoundResult(cars.getCars());
    }

    private List<RacingCarDto> makeCarDtos(Cars cars) {
        return cars.getCars().stream()
                .map(car -> new RacingCarDto(car.getName().getValue(), car.getDistance().getValue()))
                .collect(Collectors.toUnmodifiableList());
    }
}
