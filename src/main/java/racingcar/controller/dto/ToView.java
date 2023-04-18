package racingcar.controller.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import racingcar.domain.Name;
import racingcar.domain.Position;

public class ToView {

    private final List<Name> names;
    private final Map<Name, Position> history;

    public ToView(final RacingGameResponse racingGameResponse) {
        this.names = Arrays.stream(racingGameResponse.getWinners().split(","))
                .map(Name::new)
                .collect(Collectors.toList());
        this.history = racingGameResponse.getRacingCars()
                .stream()
                .collect(Collectors.toMap(carDto -> new Name(carDto.getName()),
                        carDto -> new Position(carDto.getPosition()),
                        (a, b) -> b));
    }

    public List<Name> getNames() {
        return names;
    }

    public Map<Name, Position> getHistory() {
        return history;
    }
}
