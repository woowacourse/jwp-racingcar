package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.GameResult;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;
import racingcar.validator.Validator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingConsoleController {
    private final RacingGameService racingGameService;

    public RacingConsoleController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void run() {
        List<String> carNames = getValidCarNames();
        int count = getValidTryCount();

        List<RacingCarResultDto> results = racingGameService.run(carNames, count);

        OutputView.printResult(makePlayResponseDto(results));
    }

    private List<String> getValidCarNames() {
        try {
            String carNames = InputView.readCarName();
            List<String> parsedCarNames = Parser.parsing(carNames, ",");
            Validator.validateNames(parsedCarNames);
            return parsedCarNames;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getValidCarNames();
        }
    }

    private int getValidTryCount() {
        try {
            String tryCount = InputView.readTryCount();
            Validator.validateTryCount(tryCount);
            return Integer.parseInt(tryCount);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getValidTryCount();
        }
    }

    private static PlayResponseDto makePlayResponseDto(List<RacingCarResultDto> results) {
        List<String> winners = getWinners(results);
        List<RacingCarDto> racingCarDtos = getCarInfos(results);
        return new PlayResponseDto(winners, racingCarDtos);
    }

    private static List<RacingCarDto> getCarInfos(List<RacingCarResultDto> results) {
        return results.stream()
                .map(it -> RacingCarDto.of(it.getName(), it.getPosition()))
                .collect(Collectors.toList());
    }

    private static List<String> getWinners(List<RacingCarResultDto> results) {
        return results.stream()
                .filter(result -> result.isWin() == GameResult.WIN.getValue())
                .map(RacingCarResultDto::getName)
                .collect(Collectors.toList());
    }
}
