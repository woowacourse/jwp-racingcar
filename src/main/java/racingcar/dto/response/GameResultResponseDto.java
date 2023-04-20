package racingcar.dto.response;

import java.util.List;

public final class GameResultResponseDto {

    private final List<String> winners;
    private final List<CarResponseDto> racingCars;

    public GameResultResponseDto(final List<String> winners, final List<CarResponseDto> carResponseDtos) {
        this.winners = winners;
        this.racingCars = carResponseDtos;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarResponseDto> getRacingCars() {
        return racingCars;
    }
}
