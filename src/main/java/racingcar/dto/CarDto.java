package racingcar.dto;

import racingcar.entity.Player;
import racingcar.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarDto {

    private final String name;
    private final int position;

    public CarDto(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static List<CarDto> convertCarToCarDto(final List<Car> cars) {
        return cars.stream().
                map(car -> new CarDto(car.getName(), car.getPosition())).
                collect(Collectors.toList());
    }

    public static List<CarDto> convertPlayerToCarDto(final List<Player> allPlayer) {
        return allPlayer.stream()
                .map(player -> new CarDto(player.getName(), player.getPosition())).
                collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
