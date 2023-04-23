package racingcar.controller;

import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.RacingGameResultDto;
import racingcar.domain.Winner;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.stream.Collectors;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController(InputView inputView, OutputView outputView, RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void run() {
        RacingGameResultDto racingGameResultDto = racingCarService.plays(generateGameRequestDto());
        printResults(racingGameResultDto);
    }

    private GameRequestDtoForPlays generateGameRequestDto() {
        String carNames = inputView.requestCarNames();
        String numberOfTimes = inputView.requestNumberOfTimes();
        return new GameRequestDtoForPlays(carNames, numberOfTimes);
    }

    private void printResults(RacingGameResultDto racingGameResultDto) {
        printWinners(racingGameResultDto);
        outputView.printResult(racingGameResultDto.getCars().getCars());
    }

    private void printWinners(RacingGameResultDto racingGameResultDto) {
        String winners = racingGameResultDto.getWinners()
                .getWinners().stream()
                .map(Winner::getName)
                .collect(Collectors.joining(", "));
        outputView.printWinners(winners);
    }

}
