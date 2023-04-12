package racingcar.dto;

import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResponse> racingCars;

    public GameResponseDto(final String winners, final List<PlayerResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    private static class PlayerResponse {
        private final String name;
        private final int position;

        public PlayerResponse(final String name, final int position) {
            this.name = name;
            this.position = position;
        }
    }
}
