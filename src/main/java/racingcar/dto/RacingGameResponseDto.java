package racingcar.dto;

import racingcar.model.Car;
import racingcar.util.NameFormatConverter;

import java.util.ArrayList;
import java.util.List;

public class RacingGameResponseDto {
    private final String winners;
    private final List<CarDto> racingCars;

    public RacingGameResponseDto(List<Car> winners, List<Car> racingCars) {
        this.winners = NameFormatConverter.joinNameWithDelimiter(convertWinner(winners));
        this.racingCars = convert(racingCars);
    }

    private List<String> convertWinner(List<Car> winners){
        List<String> winnerDtos = new ArrayList<>();
        for(Car car : winners){
            winnerDtos.add(car.getName());
        }
        return winnerDtos;
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
