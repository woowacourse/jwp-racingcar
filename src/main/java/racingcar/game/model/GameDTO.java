package racingcar.game.model;

import java.util.List;
import racingcar.car.model.RacingCar;

public class GameDTO {
    
    private final String winners;
    private final List<RacingCar> racingCars;
    
    public GameDTO(final String winners, final List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }
    
    public String getWinners() {
        return this.winners;
    }
    
    public List<RacingCar> getRacingCars() {
        return this.racingCars;
    }
}
