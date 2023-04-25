package racingcar.controller;

import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.controller.dto.RacingGameResultDto;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

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
        int savedGameId = racingCarService.saveGameResult(racingGameResultDto);
        GameResponseDto savedGameDto = racingCarService.getSavedGameById(savedGameId);
        printResults(savedGameDto);
    }

    private GameRequestDtoForPlays generateGameRequestDto() {
        String carNames = inputView.requestCarNames();
        String numberOfTimes = inputView.requestNumberOfTimes();
        return new GameRequestDtoForPlays(carNames, numberOfTimes);
    }

    private void printResults(GameResponseDto gameResponseDto) {
        outputView.printWinners(gameResponseDto.getWinners());
        outputView.printResult(gameResponseDto.getRacingCars());
    }

}
