package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;

public class ServiceControllerDto {
    private final List<Car> gameLog;
    private final List<Car> winners;

    public ServiceControllerDto(List<Car> gameLog, List<Car> winners) {
        this.gameLog = gameLog;
        this.winners = winners;
    }

    public List<Car> getGameLog() {
        return gameLog;
    }

    public List<Car> getWinners() {
        return winners;
    }
}
