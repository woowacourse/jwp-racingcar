package racingcar.game.controller;

import org.springframework.stereotype.Component;
import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;
import racingcar.game.service.RacingCarGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Component
public class ConsoleController {
    
    private final RacingCarGameService racingCarGameService;
    private final InputView inputView;
    private final OutputView outputView;
    
    public ConsoleController(final RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }
    
    public void play() {
        final String namesLiteral = this.inputView.readCarNames();
        final int count = this.inputView.readCount();
        final GameRequestDTO gameRequestDTO = GameRequestDTO.create(namesLiteral, count);
        final GameResponseDTO gameResponseDTO = this.racingCarGameService.createGame(gameRequestDTO);
        this.outputView.printCarPositionResult(gameResponseDTO);
    }
    
}
