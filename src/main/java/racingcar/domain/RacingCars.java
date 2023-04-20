package racingcar.domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import racingcar.dao.Player;
import racingcar.exception.CannotFindMaxPositionException;

public class RacingCars {

    private final List<RacingCar> racingCars;

    public RacingCars(final List<RacingCar> racingCars) {
        this.racingCars = new ArrayList<>(racingCars);
    }

    public static RacingCars create(final List<Player> players) {
        return new RacingCars(mapFrom(players));
    }

    private static List<RacingCar> mapFrom(final List<Player> players) {
        return players.stream()
                .map(RacingCar::from)
                .collect(toList());
    }

    public void moveAll() {
        for (RacingCar racingCar : this.racingCars) {
            racingCar.move();
        }
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public List<String> getWinnerNames() {
        int maxPosition = findMaxPosition();

        return racingCars.stream()
                .filter(racingCar -> racingCar.getPosition() == maxPosition)
                .map(RacingCar::getName)
                .collect(toList());
    }

    private int findMaxPosition() {
        return racingCars.stream()
                .mapToInt(RacingCar::getPosition)
                .max()
                .orElseThrow(CannotFindMaxPositionException::new);
    }
}
