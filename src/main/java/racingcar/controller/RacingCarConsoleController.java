package racingcar.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.RacingGameResultDto;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.Winner;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.stream.Collectors;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RacingCarConsoleController() {
        inputView = new InputView(System.in);
        outputView = new OutputView();
        // TODO : 수정 필요
        this.racingCarService = new RacingCarService(new RacingCarDao(new JdbcTemplate()), new RacingGameDao(new JdbcTemplate()), new WinnersDao(new JdbcTemplate()));
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
