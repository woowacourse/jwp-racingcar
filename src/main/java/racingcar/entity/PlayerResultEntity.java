package racingcar.entity;

import racingcar.dto.CarDto;

public class PlayerResultEntity {
    private final Integer id;
    private final int resultId;
    private final String name;
    private final int position;

    public PlayerResultEntity(final Integer id, final int resultId, final String name, final int position) {
        this.id = id;
        this.resultId = resultId;
        this.name = name;
        this.position = position;
    }

    public static PlayerResultEntity from(final CarDto carDto, final int resultId) {
        return new PlayerResultEntity(null, resultId, carDto.getName(), carDto.getPosition());
    }

    public int getId() {
        return id;
    }

    public int getResultId() {
        return resultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
