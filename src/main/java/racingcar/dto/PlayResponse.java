package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.entity.PlayerEntity;

public class PlayResponse {

    private final List<String> winners;
    private final List<CarResponse> racingCars;

    private PlayResponse(List<String> winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlayResponse from(List<PlayerEntity> playerEntities) {
        List<String> winners = playerEntities.stream()
                .filter(PlayerEntity::isWinner)
                .map(PlayerEntity::getName)
                .collect(Collectors.toList());

        List<CarResponse> racingCars = playerEntities.stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());

        return new PlayResponse(winners, racingCars);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
