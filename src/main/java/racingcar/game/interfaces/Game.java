package racingcar.game.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.interfaces.Car;
import racingcar.car.interfaces.NumberGenerator;
import racingcar.car.model.RacingCar;
import racingcar.game.model.RacingCarGame;

public interface Game {
    
    static GameResult run(final NumberGenerator numberGenerator, final int count, final String namesLiteral) {
        final List<Car> cars = Arrays.stream(namesLiteral.split(",")).map(name -> RacingCar.create(name, 0))
                .collect(Collectors.toList());
        final Game racingCarGame = RacingCarGame.create(numberGenerator, cars);
        final Game updatedGame = racingCarGame.race(count);
        return updatedGame.calculateResult();
    }
    
    Game moveCars();
    
    Game race(int count);
    
    GameResult calculateResult();
    
    List<Car> getCars();
}
