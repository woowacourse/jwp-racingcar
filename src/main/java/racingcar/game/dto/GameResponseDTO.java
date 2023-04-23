package racingcar.game.dto;

import java.util.List;
import racingcar.car.model.Car;
import racingcar.game.model.GameResult;

public class GameResponseDTO {
    
    private final String winners;
    private final List<Car> racingCars;
    
    public GameResponseDTO(final String winners, final List<Car> cars) {
        this.winners = winners;
        this.racingCars = cars;
    }
    
    public static GameResponseDTO create(final GameResult gameResult) {
        final List<Car> cars = gameResult.getCars();
        final String winners = gameResult.getWinners();
        return new GameResponseDTO(winners, cars);
    }
    
    public String getWinners() {
        return this.winners;
    }
    
    public List<Car> getRacingCars() {
        return this.racingCars;
    }
}
