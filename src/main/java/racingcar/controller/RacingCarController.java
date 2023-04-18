package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.controller.dto.GameInforamtionDto;
import racingcar.controller.dto.GameResultDto;
import racingcar.controller.dto.RacingCarDto;
import racingcar.service.RacingCarService;
import racingcar.util.NumberGenerator;
import racingcar.validation.Validation;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;
    private final NumberGenerator numberGenerator;

    public RacingCarController(RacingCarService racingCarService, NumberGenerator numberGenerator) {
        this.racingCarService = racingCarService;
        this.numberGenerator = numberGenerator;
    }

    @PostMapping("plays")
    public GameResultDto createGame(@RequestBody GameInforamtionDto gameInforamtionDto) {
        Cars cars = getCars(gameInforamtionDto);
        int trialCount = getTrialCount(gameInforamtionDto);

        List<RacingCarDto> racingCars = playGame(cars, trialCount);
        racingCarService.insertGame(trialCount, cars);

        return new GameResultDto(cars.getWinnerCars(), racingCars);
    }

    private static Cars getCars(GameInforamtionDto gameInforamtionDto) {
        String names = gameInforamtionDto.getNames();
        Validation.validateCarNames(names);
        Cars cars = new Cars(names);
        return cars;
    }

    private static int getTrialCount(GameInforamtionDto gameInforamtionDto) {
        int trialCount = gameInforamtionDto.getCount();
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

    @GetMapping("plays")
    public List<GameResultDto> createGameResult() {
        return racingCarService.findGameRecord();
    }
}
