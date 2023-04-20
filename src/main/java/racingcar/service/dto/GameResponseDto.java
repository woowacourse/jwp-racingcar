package racingcar.service.dto;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.repository.dto.GetPlayerResultQueryResponseDto;

import java.util.ArrayList;
import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResultResponseDto> racingCars;

    private GameResponseDto(final String winners, final List<PlayerResultResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponseDto createByQueryResponse(
            final String winners, final List<GetPlayerResultQueryResponseDto> queryResponses) {
        final List<PlayerResultResponseDto> racingCars = new ArrayList<>();
        for (GetPlayerResultQueryResponseDto queryResponse : queryResponses) {
            racingCars.add(PlayerResultResponseDto.createByQueryResponse(queryResponse));
        }
        return new GameResponseDto(winners, racingCars);
    }

    public static GameResponseDto createByDomain(final String winners, final Cars cars) {
        final List<PlayerResultResponseDto> racingCars = new ArrayList<>();
        for (Car car : cars.getLatestResult()) {
            racingCars.add(PlayerResultResponseDto.createByDomain(car));
        }
        return new GameResponseDto(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultResponseDto> getRacingCars() {
        return racingCars;
    }
}
