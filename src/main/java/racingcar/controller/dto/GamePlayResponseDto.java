package racingcar.controller.dto;

import java.util.ArrayList;
import java.util.List;
import racingcar.dao.dto.GameFinishedCarDto;
import racingcar.model.Car;

public class GamePlayResponseDto {
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public GamePlayResponseDto(List<String> winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = convert(racingCars);
    }

    public GamePlayResponseDto(List<GameFinishedCarDto> cars) {
        this.winners = new ArrayList<>();
        this.racingCars = new ArrayList<>();

        for (GameFinishedCarDto car : cars) {
            addCarResult(car);
        }
    }

    private void addCarResult(final GameFinishedCarDto car) {
        racingCars.add(new CarDto(car.getName(), car.getPosition()));
        if (car.isWinner()) {
            winners.add(car.getName());
        }
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
