package racingcar.domain;

import java.util.List;
import racingcar.dto.RacingGameDto;

public class RacingGame {

    private final Cars cars;
    private final Count count;

    public RacingGame(final List<String> carsName, final int count) {
        cars = new Cars(carsName);
        this.count = new Count(count);
    }

    public void race(final NumberPicker numberPicker) {
        cars.race(count, numberPicker);
    }

    public RacingGameDto findResult() {
        return new RacingGameDto(cars.getCars(), cars.findWinner(), count);
    }

    public Count getCount() {
        return count;
    }
}
