package racingcar.game.controller;

import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;
import racingcar.game.service.GameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {
    
    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;
    
    public ConsoleController(final GameService gameService) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.gameService = gameService;
    }
    
    public void play() {
        final String namesLiteral = this.inputView.readCarNames();
        final int count = this.inputView.readCount();
        
        final GameRequestDTO gameRequestDTO = new GameRequestDTO(namesLiteral, count);
        final GameResponseDTO gameResponseDTO = this.gameService.createGame(gameRequestDTO);
        
        this.outputView.printCarPositionResult(gameResponseDTO);
    }
    
}
