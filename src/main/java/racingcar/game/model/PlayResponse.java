package racingcar.game.model;

import java.util.List;
import racingcar.car.model.Car;

public class PlayResponse {
    
    private final String winners;
    private final List<Car> racingCars;
    
    public PlayResponse(final String winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }
    
    public String getWinners() {
        return this.winners;
    }
    
    public List<Car> getRacingCars() {
        return this.racingCars;
    }
}
