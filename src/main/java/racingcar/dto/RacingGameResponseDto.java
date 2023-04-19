package racingcar.dto;

import racingcar.model.Car;
import racingcar.util.NameFormatConverter;

import java.util.ArrayList;
import java.util.List;

public class RacingGameResponseDto {
    private final String winners;
    private final List<CarDto> racingCars;

    public RacingGameResponseDto(List<Car> winners, List<Car> racingCars) {
        this.winners = NameFormatConverter.joinNameWithDelimiter(convertWinnersName(winners));
        this.racingCars = convertRacingCars(racingCars);
    }

    private List<String> convertWinnersName(List<Car> winners){
        List<String> winnerDtos = new ArrayList<>();
        for(Car car : winners){
            winnerDtos.add(car.getName());
        }
        return winnerDtos;
    }

    private List<CarDto> convertRacingCars(List<Car> cars){
        List<CarDto> carDtos = new ArrayList<>();
        for(Car car : cars){
            carDtos.add(new CarDto(car.getName(),car.getPosition()));
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
