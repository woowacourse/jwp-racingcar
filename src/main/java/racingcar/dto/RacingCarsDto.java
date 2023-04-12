package racingcar.dto;

import racingcar.domain.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class RacingCarsDto {
    private final Map<String, Integer> result;

    private RacingCarsDto(Map<String, Integer> result) {
        this.result = result;
    }

    public static RacingCarsDto of(List<Car> cars) {
        HashMap<String, Integer> resultHolder = new HashMap<>();
        for (Car car : cars) {
            resultHolder.put(car.getName(), car.getPosition());
        }
        return new RacingCarsDto(resultHolder);
    }

    public void forEach(BiConsumer<? super String, ? super Integer> action) {
        result.forEach(action);
    }
}
