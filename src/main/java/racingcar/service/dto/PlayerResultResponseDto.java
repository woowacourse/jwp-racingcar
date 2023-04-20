package racingcar.service.dto;

import racingcar.domain.Car;
import racingcar.repository.dto.GetPlayerResultQueryResponseDto;

public class PlayerResultResponseDto {
    private final String name;
    private final int position;

    private PlayerResultResponseDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static PlayerResultResponseDto createByQueryResponse(final GetPlayerResultQueryResponseDto queryResponse) {
        return new PlayerResultResponseDto(queryResponse.getName(), queryResponse.getFinalPosition());
    }

    public static PlayerResultResponseDto createByDomain(final Car car) {
        return new PlayerResultResponseDto(car.getCarName().getName(), car.getCurrentPosition().getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
