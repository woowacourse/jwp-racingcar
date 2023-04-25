package racingcar.controller;

import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.service.RacingCarService;
import racingcar.ui.ConsoleView;

public class ConsoleController {
    private final ConsoleView consoleView;
    private final RacingCarService racingcarService;

    public ConsoleController(ConsoleView consoleView, RacingCarService racingcarService) {
        this.consoleView = consoleView;
        this.racingcarService = racingcarService;
    }

    public void run() {
        try {
            RacingCarRequestDto request = consoleView.getRequest();
            RacingCarResponseDto racingCarResponseDto = racingcarService.play(request);
            consoleView.printResult(racingCarResponseDto);
        } catch (IllegalArgumentException e) {
            consoleView.error(e.getMessage());
        }
    }
}
