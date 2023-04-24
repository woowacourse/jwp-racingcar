package racingcar.dto.response;

import java.util.List;

public class GameResponse {
    private static final String NAME_DELIMITER = ",";

    private final String winners;
    private final List<CarResponse> racingCars;

    public GameResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponse of(List<String> winnerNames, List<CarResponse> racingCars) {
        return new GameResponse(getCombinedNames(winnerNames), racingCars);
    }

    public static String getCombinedNames(List<String> winnerNames) {
        return String.join(NAME_DELIMITER, winnerNames);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
