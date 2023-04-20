package racingcar.game.controller;

import racingcar.car.model.RandomNumberGenerator;
import racingcar.game.dto.GameResponseDTO;
import racingcar.game.interfaces.Game;
import racingcar.game.interfaces.GameResult;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public ConsoleController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }
    
    public void play() {
        final String namesLiteral = this.inputView.readCarNames();
        final int count = this.inputView.readCount();
        final GameResult gameResult = Game.run(new RandomNumberGenerator(), count, namesLiteral);
        final GameResponseDTO gameResponseDTO = GameResponseDTO.create(gameResult);
        this.outputView.printCarPositionResult(gameResponseDTO);
    }
    
}
