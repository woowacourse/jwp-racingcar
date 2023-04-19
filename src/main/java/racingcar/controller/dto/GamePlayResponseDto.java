package racingcar.controller.dto;

import java.util.ArrayList;
import java.util.List;
import racingcar.model.Car;

public class GamePlayResponseDto {
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public GamePlayResponseDto(List<String> winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = convert(racingCars);
    }

    public GamePlayResponseDto() {
        this.winners = new ArrayList<>();
        this.racingCars = new ArrayList<>();
    }

    private List<CarDto> convert(List<Car> cars){
        List<CarDto> carDtos = new ArrayList<>();
        for(Car car : cars){
            carDtos.add(new CarDto(car.getName(),car.getPosition()));
        }
        return carDtos;
    }
    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
