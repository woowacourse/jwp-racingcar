package racingcar.controller.dto;

import java.util.ArrayList;
import java.util.List;
import racingcar.model.Car;

public class GamePlayResponseDto {
    private final String winners;
    private final List<CarDto> racingCars;

    public GamePlayResponseDto(String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = convert(racingCars);
    }

    private List<CarDto> convert(List<Car> cars){
        List<CarDto> carDtos = new ArrayList<>();
        for(Car car : cars){
            carDtos.add(new CarDto(car.getName(),car.getLocation()));
        }
        return carDtos;
    }
    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
