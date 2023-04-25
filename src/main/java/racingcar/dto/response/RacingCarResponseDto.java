package racingcar.dto.response;

import racingcar.domain.Car;

public class RacingCarResponseDto {
    private String name;
    private Integer position;
    private Long gameId;

    public RacingCarResponseDto(final String name, final Integer position) {
        this.name = name;
        this.position = position;
    }

    public RacingCarResponseDto(final String name, final Integer position, final Long gameId) {
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public static RacingCarResponseDto from(final Car car) {
        return new RacingCarResponseDto(car.getName(), car.getPosition());
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public Long getGameId() {
        return gameId;
    }
}
