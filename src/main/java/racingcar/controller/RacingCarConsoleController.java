package racingcar.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.dao.RacingCarDao;
import racingcar.entity.GameEntity;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController() {
        inputView = new InputView(System.in);
        outputView = new OutputView();
        this.racingCarService = new RacingCarService(new RacingCarDao(new JdbcTemplate()), racingGameDao);
    }

    public void run() {
        String carNames = inputView.requestCarNames();
        String numberOfTimes = inputView.requestNumberOfTimes();
        GameEntity gameResultEntity = racingCarService.getGameResultEntity(
                new GameRequestDtoForPlays(carNames, numberOfTimes)
        );
        printResults(racingCarService.generateRacingGameResponseDto(gameResultEntity));
    }

    private void printResults(GameResponseDto gameResponseDto) {
        outputView.printWinners(gameResponseDto.getWinners());
        outputView.printResult(gameResponseDto.getRacingCars());
    }

}
