package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameInformationDto;
import racingcar.controller.dto.GameResultDto;
import racingcar.controller.dto.RacingCarDto;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.service.RacingCarService;
import racingcar.util.NumberGenerator;
import racingcar.validation.Validation;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;
    private final NumberGenerator numberGenerator;
    private final OutputView outputView;

    public RacingCarController(RacingCarService racingCarService, NumberGenerator numberGenerator, OutputView outputView) {
        this.racingCarService = racingCarService;
        this.numberGenerator = numberGenerator;
        this.outputView = outputView;
    }

    @PostMapping("plays")
    public GameResultDto createGame(@RequestBody GameInformationDto gameInformationDto) {
        Cars cars = getCars(gameInformationDto);
        int trialCount = getTrialCount(gameInformationDto);

        List<RacingCarDto> racingCars = playGame(cars, trialCount);
        racingCarService.insertGame(trialCount, cars);

        printResult(cars);

        return new GameResultDto(cars.getWinnerCars(), racingCars);
    }

    private static Cars getCars(GameInformationDto gameInformationDto) {
        String names = gameInformationDto.getNames();
        Validation.validateCarNames(names);
        Cars cars = new Cars(names);
        return cars;
    }

    private static int getTrialCount(GameInformationDto gameInformationDto) {
        int trialCount = gameInformationDto.getCount();
        Validation.validateTryCount(trialCount);
        return trialCount;
    }

    private List<RacingCarDto> playGame(Cars cars, int trialCount) {
        for (int count = 0; count < trialCount; count++) {
            cars.moveForRound(numberGenerator);
        }

        List<RacingCarDto> racingCars = new ArrayList<>();
        for (Car car : cars.getCars()) {
            RacingCarDto racingCarDto = new RacingCarDto(car.getName(), car.getLocation());
            racingCars.add(racingCarDto);
        }
        return racingCars;
    }

    private void printResult(Cars cars) {
        outputView.printWinner(cars.getWinners());
        outputView.printCarResult(cars);
    }

    @GetMapping("plays")
    public List<GameResultDto> createGameResult() {
        return racingCarService.findGameRecord();
    }
}
