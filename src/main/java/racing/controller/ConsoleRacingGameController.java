package racing.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;
import racing.service.RacingGameService;
import racing.util.RandomNumberGenerator;
import racing.view.OutputView;

public class ConsoleRacingGameController implements RacingGameController {

    private final RacingGameService racingGameService;

    public ConsoleRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public GameResultDto play(@RequestBody GameInputDto gameInputDto) {
        final GameResultDto gameResultDto = racingGameService.playGame(gameInputDto, new RandomNumberGenerator());
        final OutputView outputView = new OutputView();
        outputView.printFinalResult(gameResultDto.getRacingCars());
        outputView.printWinners(gameResultDto.getWinners());
        return gameResultDto;
    }

    @Override
    public List<GameResultDto> showAllGames() {
        final List<GameResultDto> gameResultDtos = racingGameService.showGames();
        return List.copyOf(gameResultDtos);
    }
}
