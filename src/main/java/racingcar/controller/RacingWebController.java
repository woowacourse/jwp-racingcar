package racingcar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.AdvanceJudgement;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingCar;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.Range;
import racingcar.domain.RoundManager;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.dto.RacingCarDto;
import racingcar.utils.Parser;
import racingcar.validator.Validator;

@RestController
public class RacingWebController {

    private static final int FIRST_CAR_INDEX = 0;

    private final Validator validator = new Validator();

    @PostMapping(path = "/plays")
    public GameResponseDto play(@RequestBody GameRequestDto gameRequestDto) {
        List<String> carNames = getValidCarNames(gameRequestDto.getNames());
        int count = getValidTryCount(gameRequestDto.getCount());

        RoundManager roundManager = initializeRoundManager(carNames);
        IntStream.range(0, count).forEach(ignored -> roundManager.runRound());
        List<RacingCarDto> racingCarDtos = roundManager.getStatus();
        List<String> winners = getWinningCarsName(roundManager.getSortedRacingCars());
        return new GameResponseDto(winners, racingCarDtos);
    }

    private List<String> getValidCarNames(String carNames) {
        List<String> parsedCarNames = Parser.parsing(carNames, ",");
        validator.validateNames(parsedCarNames);
        return parsedCarNames;
    }

    private int getValidTryCount(int tryCount) {
        validator.validateTryCount(tryCount);
        return tryCount;
    }


    private void setCars(List<String> carNames, RoundManager roundManager) {
        for (String carName : carNames) {
            roundManager.addRacingCar(new RacingCar(carName));
        }
    }

    private RoundManager initializeRoundManager(List<String> carNames) {
        Range range = new Range(4, 9);
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        AdvanceJudgement advanceJudgement = new AdvanceJudgement(range, numberGenerator);
        RoundManager roundManager = new RoundManager(advanceJudgement);
        setCars(carNames, roundManager);
        return roundManager;
    }

    private List<String> getWinningCarsName(List<RacingCarDto> sortedRacingCars) {
        RacingCarDto firstCar = sortedRacingCars.get(FIRST_CAR_INDEX);
        return sortedRacingCars.stream()
                .filter(car -> car.getPosition().equals(firstCar.getPosition()))
                .map(RacingCarDto::getName)
                .collect(Collectors.toList());
    }
}
