package racingcar.repository.mapper;

import racingcar.domain.Name;
import racingcar.domain.Position;

public class PlayerMapper {
    private final int id;
    private final Name name;
    private final Position position;
    private final int racingGameId;

    public PlayerMapper(final int id, final String name, final int position, final int racingGameId) {
        this.id = id;
        this.name = new Name(name);
        this.position = new Position(position);
        this.racingGameId = racingGameId;
    }

    public int getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getRacingGameId() {
        return racingGameId;
    }
}
