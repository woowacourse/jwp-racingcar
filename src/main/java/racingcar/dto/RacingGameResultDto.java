package racingcar.dto;

import java.util.List;

public class RacingGameResultDto {

    private final List<String> winners;
    private final List<CarDto> cars;
    private final int moveCount;

    public RacingGameResultDto(List<String> winners, List<CarDto> cars, int moveCount) {
        this.winners = winners;
        this.cars = cars;
        this.moveCount = moveCount;
    }

    public List<String> getWinnerNames() {
        return winners;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
