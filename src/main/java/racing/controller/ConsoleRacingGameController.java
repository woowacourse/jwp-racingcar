package racing.controller;

import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.response.GameInfoResponse;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.ui.input.InputView;
import racing.service.RacingGameService;
import racing.ui.output.OutputView;

import java.util.List;

public class ConsoleRacingGameController {

    private final RacingGameService racingGameService;

    public ConsoleRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void start() {
        boolean reGame = true;
        while (reGame) {
            showBeforeGames();
            RacingGameInfoRequest request = new RacingGameInfoRequest(InputView.inputCarsName(), Integer.parseInt(InputView.inputCount()));
            GameInfoResponse response = racingGameService.execute(request);

            racingGameService.saveCarsState(response.getGameId(), response.getCars());

            OutputView.printResult(response.getCars());
            reGame = InputView.getReGame();
        }
    }

    private void showBeforeGames() {
        String response = InputView.getShowBeforeGame();
        if (response.equals("y")) {
            List<RacingGameResultResponse> responses = racingGameService.getRacingGameResultResponse();
            OutputView.printBeforeGames(responses);
        }
        OutputView.printPhrase();
    }

}
