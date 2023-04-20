package racingcar.repository.dto;

import racingcar.domain.Name;
import racingcar.domain.Position;

public class PlayerDto {
    private final Name name;
    private final Position position;

    public PlayerDto(final Name name, final Position position) {
        this.name = name;
        this.position = position;
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
