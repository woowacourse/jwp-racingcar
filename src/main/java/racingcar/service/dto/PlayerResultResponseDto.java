package racingcar.service.dto;

import racingcar.domain.Car;
import racingcar.entity.PlayerResult;
import racingcar.repository.dto.GetPlayerResultQueryResponseDto;

public class PlayerResultResponseDto {
    private final String name;
    private final int position;

    private PlayerResultResponseDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static PlayerResultResponseDto createByEntity(final PlayerResult playerResult) {
        return new PlayerResultResponseDto(playerResult.getName(), playerResult.getFinalPosition());
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
